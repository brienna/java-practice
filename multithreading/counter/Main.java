package counter;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CounterView gui = new CounterView();
                gui.activate();
            }
        });
    }
}