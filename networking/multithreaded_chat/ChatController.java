package multithreaded_chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatController {
    private ChatGUI chatView;

    public ChatController(ChatGUI view) {
        chatView = view;

        // Add a listener to the "Send" button
        chatView.addSendButtonListener(new SendButtonListener());
    }

    // Define listener for "Send" button
    class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // Get input text
            String input = chatView.getInputText();
            // Append input text to chat area
            chatView.addTextToChat("username: " + input + "\n");
        }
    }
}