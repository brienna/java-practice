package download_progress_bar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;

public class ProgressBar implements ActionListener, PropertyChangeListener {
    private JFrame frame;
    private JPanel gui;
    private JButton button;
    private JProgressBar progressBar;
    private SwingWorker<Void, Void> worker;

    public ProgressBar() {
        customizeFrame();
        createMainPanel();
        createProgressBar();
        createButton();
        addComponentsToFrame();
    }

    private void customizeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        button = new JButton("Start progress");
        button.addActionListener(this);
    }

    /**
     * Invoked when user clicks the button.
     */
    public void actionPerformed(ActionEvent evt) {
        // NOTE: Instances of javax.swing.SwingWorker are not reusable, 
        // so we create new instances as needed
        worker = new Worker();
        worker.addPropertyChangeListener(this);
        worker.execute();
    }

    class Worker extends SwingWorker<Void, Void> {
        /* 
         * Main task. Executed in worker thread.
         */
        @Override
        protected Void doInBackground() throws Exception {
            int progress = 0;
            this.setProgress(0);
            while (progress < 100) {
                // Sleep for up to a tenth of a second, then make progress
                Thread.sleep(100);
                progress++;
                this.setProgress(progress);
            }
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        protected void done() {
            System.out.println("Done!");
        }
    }

    /**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        // NOTE: By default two property states exist: "state" and "progress"
        if ("progress" == evt.getPropertyName()) {
            int progress = worker.getProgress();
            progressBar.setValue(progress);
            System.out.println(String.format(
                    "Completed %d%% of task.\n", progress));
        } 
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
}