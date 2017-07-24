package download_progress_bar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgressBar {
    private JFrame frame;
    private JPanel gui;
    private JButton button;
    private JProgressBar progressBar;

    public ProgressBar() {
        customizeFrame();
        createMainPanel();
        createProgressBar();
        createButton();
        addComponentsToFrame();
    }

    private void customizeFrame() {
        // Set the look and feel to the cross-platform look and feel,
        // otherwise mac os will have quirks like gaps between jbuttons
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Unsupported look and feel.");
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void createMainPanel() {
        gui = new JPanel();
        gui.setLayout(new BorderLayout());
    }

    private void createProgressBar() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);  // renders a progress string
    }

    private void createButton()  {
        button = new JButton("Start download");
    }

    private void addComponentsToFrame() {
        gui.add(progressBar, BorderLayout.CENTER);
        gui.add(button, BorderLayout.SOUTH);
        frame.add(gui);
        frame.pack();
    }

    // Add passed ActionListener to the button
    void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    // Get progress bar
    public JProgressBar getProgressBar() {
        return progressBar;
    }

    // Enable or disable button
    public void turnOnButton(boolean flip) {
        button.setEnabled(flip);
    }

    // Show view on screen
    public void activate() {
        frame.setVisible(true);
    }
}