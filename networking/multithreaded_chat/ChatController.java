package multithreaded_chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatController {
    private ChatGUI view;

    public ChatController(ChatGUI view) {
        this.view = view;

        // Add a listener to the "Send" button
        view.addSendButtonListener(new SendButtonListener());
        startClient();
    }

    // Define listener for "Send" button
    class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // Get input text
            String input = view.getInputText();
            // Append input text to chat area
            view.addTextToChat("username: " + input + "\n");
            view.clearInput();
        }
    }

    public void startClient() {
        // Create the worker
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws InterruptedException {
                // Start client
                ChatClient client = new ChatClient("localhost", 4444);
                return null;
            }
        };

        worker.execute();
    }
}