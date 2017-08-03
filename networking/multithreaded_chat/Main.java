package multithreaded_chat;

import javax.swing.*;

/**
 * Launches the application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChatGUI chatView = new ChatGUI();
                ChatController controller = new ChatController(chatView);
            }
        });
    }
}