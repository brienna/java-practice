package counter;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.SwingConstants;
import java.awt.event.*;

public class CounterView {
    private JFrame frame;
    private JPanel gui;
    private JButton button;
    private JLabel count;

    public CounterView() {
        customizeFrame();
        createMainPanel();
        createText();
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

    private void createCount() {
        count = new JLabel("0");
        count.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void createButton()  {
        button = new JButton("Start counter");
        button.addActionListener(new ActionListener() {
            /* 
             * This long-running task doesn't freeze the GUI because it is 
             * happening on a worker thread, not the event dispatch thread
             */
            public void actionPerformed(ActionEvent e) {
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 50; i++) {
                            Thread.sleep(100);
                            System.out.println(i);
                            text.setText(Integer.toString(i));
                        }

                        return null;
                    }
                };

                worker.execute();
            }
        });
    }

    private void addComponentsToFrame() {
        gui.add(text, BorderLayout.CENTER);
        gui.add(button, BorderLayout.SOUTH);
        frame.add(gui);
        frame.pack();
    }

    public void activate() {
        frame.setVisible(true);
    }
}