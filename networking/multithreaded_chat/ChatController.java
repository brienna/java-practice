package multithreaded_chat;

import javax.swing.*;

public class ChatController {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChatGUI chat = new ChatGUI();
            }
        });
    }
}