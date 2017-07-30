package multithreaded_chat;

import javax.swing.*;
import java.awt.*;

public class ChatGUI {
    private JFrame frame;
    private JPanel gui;
    private JTextArea chatArea;
    private JTextField input;
    private JButton sendButton;

    public ChatGUI() {
        createFrame();
        createGUI();
        createChatArea();
        createInput();
        createSendButton();
        addComponentsToFrame();
        frame.setVisible(true);
    }

    private void createFrame() {
        frame = new JFrame();
        frame.setResizable(false);
    }

    private void createGUI() {
        gui = new JPanel(new BorderLayout());
    }

    private void createChatArea() {
        chatArea = new JTextArea(10, 10);
    }

    private void createInput() {
        input = new JTextField(10);
    }

    private void createSendButton() {
        sendButton = new JButton("Send");
    }

    private void addComponentsToFrame() {
        gui.add(chatArea, BorderLayout.NORTH);
        gui.add(input, BorderLayout.CENTER);
        gui.add(sendButton, BorderLayout.SOUTH);
        frame.add(gui);
        frame.pack();
    }
}