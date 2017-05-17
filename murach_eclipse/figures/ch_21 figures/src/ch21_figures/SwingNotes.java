package ch21_figures;

import javax.swing.*;

public class SwingNotes {
	public static JFrame frame;  // the main window of the app
	
	public static void main(String[] args) {
		createAndDisplayFrame();
		addButtons();
		
		// Make the frame visible
		// NOTE: Add all of the panels & components to frame before calling setVisible
		frame.setVisible(true);
	}
	
	// Figure 21-3, How to work with frames
	public static void createAndDisplayFrame() {
		frame = new JFrame("Site-Directed Mutagenesis Verifier");
		frame.setSize(600, 400);
		// Let the OS set the location
		frame.setLocationByPlatform(true);
		// Exit application when user selects close button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set look and feel to use default platform look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
			System.err.println("Unsupported look and feel.");
		}
	}
	
	// Figure 21-4, How to work with buttons
	public static void addButtons() {
		JPanel panel = new JPanel();
		frame.add(panel);
		
		JButton button1 = new JButton("Click me!");
		JButton button2 = new JButton();
		JButton button3 = new JButton("I'm deactivated");
		
		// Disable and gray out
		button3.setEnabled(false);
		// Set text
		button2.setText("No, click me!");
		// Display tooltip on user hover
		button2.setToolTipText("Because I have more effects and am cooler.");
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
	}
}
