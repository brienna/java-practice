package download_progress_bar;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.FileOutputStream;  // meant for writing streams of raw bytes such as image data
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;

/**
*
*   the model
*
*/
public class Downloader {
    public static void main(String[] args) throws IOException {
        SwingWorker worker = new Worker();
        worker.execute();

        while (!work.isDone()) {
            // Show Progress
            try {
                int iProgress = worker.getProgress();
                System.out.println("Progress %" + iProgress);
                Thread.sleep(500);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        } 
    }

    // Send search request
    public static void download(String source) throws IOException {
        setProgress(0);

        // Create a URL object for a given URL
        URL website = new URL(source);
        // Open connection on the URL object
        HttpURLConnection connection = (HttpURLConnection) website.openConnection();
        
        // Check HTTP response code (do this first always)
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            System.out.println(responseCode);
        }

        // Open input stream from connection
        BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
        // Open output stream to write file
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("image.jpg"));

        int i;
        while ((i = in.read()) != -1) {
            out.write(i);
            setProgress((i / connection.getContentLength()));
        }

        // Close streams
        out.close();
        in.close();
    }

    class Worker extends SwingWorker<Boolean, Integer> {
        @Override
        protected Boolean doInBackground() {
            String source = "https://s-media-cache-ak0.pinimg.com/originals/05/69/2b/05692bd38a0e7197b410bc78bd357df6.jpg";
            download(source);
            return true;
        } 
    }

}