package download_progress_bar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutionException;

public class ProgressBar implements ActionListener, PropertyChangeListener {
    private JFrame frame;
    private JPanel gui;
    private JButton button;
    private JProgressBar progressBar;
    private SwingWorker<Void, Void> worker;
    private boolean done;

    public ProgressBar() {
        done = false;
        customizeFrame();
        createMainPanel();
        createProgressBar();
        createButton();
        addComponentsToFrame();
    }

    private void customizeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        // NOTE: Instances of javax.swing.SwingWorker are not reusable, 
        // so we create new instances as needed
        worker = new Worker();
        worker.addPropertyChangeListener(this);
        worker.execute();
    }

    class Worker extends SwingWorker<Void, Void> {
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

                    int bytesRead = - 1;
                    int totalBytesRead = 0;
                    int percentCompleted = 0;

                    while ((bytesRead = in.read()) != -1) {
                        out.write(bytesRead);
                        totalBytesRead += bytesRead;
                        percentCompleted = totalBytesRead * 100 / connection.getContentLength();

                        System.out.println("..." + percentCompleted);
                        this.setProgress(percentCompleted);
                    }

                    // Close streams
                    out.close();
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println(ex);
                this.setProgress(0);
                cancel(true);
            }
            
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        protected void done() {
            button.setEnabled(true);
            try {
                if (!isCancelled()) {
                    get();  // throws an exception if doInBackground throws one
                    System.out.println("File has been downloaded successfully!");
                }
            } catch (InterruptedException x) {
                x.printStackTrace();
            } catch (ExecutionException x) {
                x.printStackTrace();
            }
            System.out.println("There was an error in downloading the file.");
        }
    }

    /**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt);
        // NOTE: By default two property states exist: "state" and "progress"
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            System.out.println(String.format(
                    "Completed %d%% of task.\n", progress));
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