package download_progress_bar;

import javax.swing.SwingUtilities;

/**
 * Runs the download progress bar application.
 */
public class Main {
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create view
                ProgressBar view = new ProgressBar();
                // NOTE: Should model/controller be created outside invokeLater?
                // Create model
                Downloader model = new Downloader();
                // Create controller
                Controller controller = new Controller(view, model);
                // Show the view on the screen
                view.activate();
            }
        });
    }
}