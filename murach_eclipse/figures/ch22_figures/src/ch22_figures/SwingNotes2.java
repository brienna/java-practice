package ch22_figures;

import javax.swing.*;

public class SwingNotes2 {
	public static JFrame frame;  // the main window of the app
	public static JPanel panel = new JPanel();

	public static void main(String[] args) {
		createFrame();
		frame.add(panel);
		createAndAddJLabel();
		
		// Make the frame visible
		// NOTE: Add all of the panels & components to frame before calling setVisible
		frame.setVisible(true);
	}
	
	public static void createFrame() {
		frame = new JFrame("Chapter 22: How to develop a GUI with Swing (part 2)");
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
	
	public static void createAndAddJLabel() {
		// How to create a JLabel object and add it to a container
		JLabel codeLabel = new JLabel("Code:");
		panel.add(codeLabel);
		
		// Another way of adding a JLabel to a container
		// NOTE: This is a common practice, since you rarely need to call 
		// any methods from a JLabel object
		panel.add(new JLabel("Code:"));
		
		// NOTE: A JLabel component defines a label, which is a non-editable widget that 
		// typically displays text (or an image) that labels other components such as text fields
	}
}
