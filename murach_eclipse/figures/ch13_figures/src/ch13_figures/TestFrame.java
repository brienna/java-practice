package ch13_figures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A class that contains and uses an inner class.
 */
public class TestFrame extends JFrame {
	
	public TestFrame() {
		// Set up the frame
		this.setTitle("Test Frame");
		this.setSize(400, 100);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.add(panel);
		
		// Create the button and add the listener
		JButton button1 = new JButton("Test Button");
		ActionListener listener = new ClickListener();  
		button1.addActionListener(listener);
		
		// Display the frame
		panel.add(button1);
		this.setVisible(true);
	}
	
	// the inner class that implements the listener
	class ClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("The button was clicked!");
		}
	}
	
	// Run the frame
	public static void main(String[] args) {
		TestFrame frame = new TestFrame();
	}
}
