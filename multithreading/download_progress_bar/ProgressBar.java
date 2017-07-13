package download_progress_bar;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import java.awt.event.*;

public class ProgressBar {
    private JFrame frame;
    private JPanel gui;
    private JProgressBar progressBar;
    private JButton button;

    public ProgressBar() {
        customizeFrame();
        createMainPanel();
        createProgressBar();
        createButton();
        addComponentsToFrame();
    }

    private void customizeFrame() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createMainPanel() {
        // Create the main panel which by default covers the entire frame
        gui = new JPanel();
        gui.setLayout(new BorderLayout());
    }

    private void createProgressBar() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
    }

    private void createButton() {
        button = new JButton("Download");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startWorker();
            }
        });
    }

    private void addComponentsToFrame() {
        gui.add(progressBar, BorderLayout.CENTER);
        gui.add(button, BorderLayout.SOUTH);
        frame.add(gui);
        frame.pack();
    }

    public void activate() {
        frame.setVisible(true);
    }

    private void startWorker() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                // Simulate doing something useful
                for (int i = 0; i < 30; i++) {
                    Thread.sleep(100);
                    System.out.println("Hello: " + i);
                }

                return null;
            }
        };

        worker.execute();
    }
}