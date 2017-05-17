package ch21_figures;

import javax.swing.*;

public class SwingNotes {
	public static void main(String[] args) {
		createAndDisplayFrame(); 
	}
	
	// Figure 21-3, How to work with frames
	public static void createAndDisplayFrame() {
		JFrame frame = new JFrame("Site-Directed Mutagenesis Verifier");
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
		// Make the frame visible
		frame.setVisible(true);
	}
}
