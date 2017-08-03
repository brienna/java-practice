package multithreaded_chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createGUI() {
        gui = new JPanel(new BorderLayout());
    }

    private void createChatArea() {
        chatArea = new JTextArea(20, 20);
        chatArea.setLineWrap(true); // enable line wrap
        chatArea.setWrapStyleWord(true); // line wrap at word boundaries
    }

    private void createInput() {
        input = new JTextField(10);
    }

    private void createSendButton() {
        sendButton = new JButton("Send");
        frame.getRootPane().setDefaultButton(sendButton);  // set to listen to "Enter" key
    }

    private void addComponentsToFrame() {
        gui.add(chatArea, BorderLayout.NORTH);
        gui.add(input, BorderLayout.CENTER);
        gui.add(sendButton, BorderLayout.SOUTH);
        frame.add(gui);
        frame.pack();
    }

    // Return text from input field
    public String getInputText() {
        return input.getText();
    }

    // Add text to chat text area
    public void addTextToChat(String text) {
        chatArea.append(text);
    }

    // Attach passed listener to "Send" button
    void addSendButtonListener(ActionListener actl) {
        sendButton.addActionListener(actl);
    }
}