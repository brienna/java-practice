package download_progress_bar;

import java.util.concurrent.ExecutionException;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.net.*;
import java.io.*;
import java.beans.*;

public class Controller {
    private ProgressBar view;
    private Downloader model;
    private JProgressBar progressBar;
    private SwingWorker<Void, Integer> worker;

    public Controller(ProgressBar theView, Downloader theModel) {
        view = theView;
        model = theModel;
        progressBar = view.getProgressBar();

        // Add button listener to the "Start Download" button
        view.addButtonListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {
        /**
         * Invoked when user clicks the button.
         */
        public void actionPerformed(ActionEvent evt) {
            view.turnOnButton(false);
            progressBar.setIndeterminate(true);
            // NOTE: Instances of javax.swing.SwingWorker are not reusable, 
            // so we create new instances as needed
            worker = new Worker();
            worker.execute();
        }
    }

    class Worker extends SwingWorker<Void, Integer> implements PropertyChangeListener {
        /* 
         * Download task. Executed in worker thread.
         */
        @Override
        protected Void doInBackground() throws MalformedURLException {
            model.addListener(this);
            try {
                String src = "https://lh3.googleusercontent.com/l6JAkhvfxbP61_FWN92j4ulDMXJNH3HT1DR6xrE7MtwW-2AxpZl_WLnBzTpWhCuYkbHihgBQ=s640-h400-e365";
                model.setURL(src);
                model.download();
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

            view.turnOnButton(true);
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

        /**
         * Invoked when a progress property of "downloading" is received.
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("downloading")) {
                publish((Integer) evt.getNewValue());
            }
        }
    }
}