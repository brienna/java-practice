package download_progress_bar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.net.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProgressBar implements ActionListener {
    private JFrame frame;
    private JPanel gui;
    private JButton button;
    private JProgressBar progressBar;
    private SwingWorker<Void, Integer> worker;

    public ProgressBar() {
        customizeFrame();
        createMainPanel();
        createProgressBar();
        createButton();
        addComponentsToFrame();
    }

    private void customizeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the look and feel to the cross-platform look and feel,
        // otherwise mac os will have quirks like gaps between jbuttons
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Unsupported look and feel.");
            e.printStackTrace();
        }
    }

    private void createMainPanel() {
        gui = new JPanel();
        gui.setLayout(new BorderLayout());
    }

    private void createProgressBar() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);  // renders a progress string
    }

    private void createButton()  {
        button = new JButton("Start download");
        button.addActionListener(this);
    }

    /**
     * Invoked when user clicks the button.
     */
    public void actionPerformed(ActionEvent evt) {
        button.setEnabled(false);
        progressBar.setIndeterminate(true);
        // NOTE: Instances of javax.swing.SwingWorker are not reusable, 
        // so we create new instances as needed
        worker = new Worker();
        worker.execute();
    }

    class Worker extends SwingWorker<Void, Integer> {
        /* 
         * Main task. Executed in worker thread.
         */
        @Override
        protected Void doInBackground() throws MalformedURLException {            
            // Create a URL object for a given URL
            String src = "https://lh3.googleusercontent.com/l6JAkhvfxbP61_FWN92j4ulDMXJNH3HT1DR6xrE7MtwW-2AxpZl_WLnBzTpWhCuYkbHihgBQ=s640-h400-e365";
            URL url = new URL(src);
            // Open connection on the URL object

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
                // Always check response code first
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    System.out.println(responseCode);

                    // Open input stream from connection
                    BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                    // Open output stream for file writing
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("cat.jpg"));

                    int totalBytesRead = 0;
                    int percentCompleted = 0;
                    int i = -1;
                    while ((i = in.read()) != -1) {
                        out.write(i);
                        totalBytesRead++;
                        percentCompleted = totalBytesRead * 100 / connection.getContentLength();

                        System.out.println(percentCompleted);  // makes download a bit slower, comment out for speed
                        publish(percentCompleted);
                    }

                    // Close streams
                    out.close();
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println(ex);
                cancel(true);
            }
            
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        protected void done() {
            try {
                if (!isCancelled()) {
                    get();  // throws an exception if doInBackground throws one
                    System.out.println("File has been downloaded successfully!");
                }
            } catch (InterruptedException x) {
                x.printStackTrace();
                System.out.println("There was an error in downloading the file.");
            } catch (ExecutionException x) {
                x.printStackTrace();
                System.out.println("There was an error in downloading the file.");
            }

            button.setEnabled(true);
        }

        @Override
        protected void process(List<Integer> chunks) {
            int percentCompleted = chunks.get(chunks.size() - 1); // only interested in the last value reported each time
            progressBar.setValue(percentCompleted);  

            if (percentCompleted > 0) {
                progressBar.setIndeterminate(false);
                progressBar.setString(null);
            } 
            System.out.println("..." + percentCompleted + "% completed");
        }
    }

    private void addComponentsToFrame() {
        gui.add(progressBar, BorderLayout.CENTER);
        gui.add(button, BorderLayout.SOUTH);
        frame.add(gui);
        frame.pack();
    }

    public void activate() {
        frame.setVisible(true);
    }
}