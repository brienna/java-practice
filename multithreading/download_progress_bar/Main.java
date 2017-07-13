package download_progress_bar;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProgressBar bar = new ProgressBar();
                bar.activate();
            }
        });
    }
}