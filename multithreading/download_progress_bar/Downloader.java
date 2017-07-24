package download_progress_bar;

import java.net.*;
import java.io.*;
import java.beans.*;

public class Downloader {
    private URL url;
    private int percentCompleted;
    private PropertyChangeSupport pcs;

    public Downloader() {
        pcs = new PropertyChangeSupport(this);
    }

    // Set URL object
    public void setURL(String src) throws MalformedURLException {
        url = new URL(src);
    }

    // Add passed PropertyChangeListener to pcs
    public void addListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void download() throws IOException {
        // Open connection on URL object
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Check response code (always do this first)
        int responseCode = connection.getResponseCode();
        System.out.println("response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
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

                int x = totalBytesRead * 100 / connection.getContentLength();
                pcs.firePropertyChange("downloading", this.percentCompleted, this.percentCompleted = x);

                System.out.println(x);  // makes download a bit slower, comment out for speed
            }

            // Close streams
            out.close();
            in.close();
        }
    }
}