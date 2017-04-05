package ch13_figures;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch13_figures.TestFrame.ClickListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that contains and uses an anonymous inner class.
 * The anonymous class is not assigned to a variable.
 * Do this when you only want to use the anonymous class once.
 */
public class AnonTestFrame2 extends JFrame {
	
	public AnonTestFrame2() {
		// Set up the frame
		this.setTitle("Test Frame");
		this.setSize(400, 100);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.add(panel);
		
		// Create the button and add the listener
		JButton button1 = new JButton("Test Button");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button was clicked!");
			}
		});
		
		// Display the frame
		panel.add(button1);
		this.setVisible(true);
	}
	
	// Run the frame
	public static void main(String[] args) {
		TestFrame frame = new TestFrame();
	}
}
