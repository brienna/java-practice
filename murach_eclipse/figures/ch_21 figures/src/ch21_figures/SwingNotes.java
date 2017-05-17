package ch21_figures;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Notes on creating the main window of a GUI application.
 */

public class SwingNotes {
	public static JFrame frame;  // the main window of the app
	
	public static void main(String[] args) {
		createAndDisplayFrame();
		workWithButtons();
		demonstrateBorderLayout();
		//changeLayoutManager();
		
		// Make the frame visible
		// NOTE: Add all of the panels & components to frame before calling setVisible
		frame.setVisible(true);
		
		displayDialogBox();
		confirmOperation();
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
	// NOTE: Usually the easiest way to build a GUI application is to use an invisible
	// container known as a panel to group components.
	public static void workWithButtons() {
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
		
		// Handle click events with an anonymous inner class
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button 1 clicked!");
			}
		});
		
		// Handle click events with a lambda expression (in Java 8 or later)
		button2.addActionListener((ActionEvent e) -> {
			System.out.println("Button 2 clicked!");
		});
	}
	
	// Figure 21-6, A summary of layout managers
	// NOTE: A layout manager determines how your components are placed in the container
	// and how they behave if the container is resized or if the font size changes.
	// By default, a JFrame widget uses the BorderLayout manager, and a JPanel widget
	// uses the FlowLayout manager. Those are the two most commonly used layout managers.
	public static void changeLayoutManager() {
		// NOTE: The constructor argument sets the horizontal alignment of the manager.
		// You can specify LEFT, RIGHT, or CENTER (default). 
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	// Figure 21-8, How to work with the BorderLayout manager
	// NOTE: A BorderLayout has 5 areas: NORTH, SOUTH, EAST, WEST, and CENTER (default).
	// Each area of a BorderLayout can only hold one component. If you need to add multiple
	// components to an area, add them to a panel and then add the panel to the area.
	// If you don't provide a component for an area in the BorderLayout, that area has a size
	// of zero and the components in other areas resize to fill any remaining space.
	public static void demonstrateBorderLayout() {
		frame.add(new JButton("North"), BorderLayout.NORTH);
		frame.add(new JButton("South"), BorderLayout.SOUTH);
		frame.add(new JButton("East"), BorderLayout.EAST);
		frame.add(new JButton("West"), BorderLayout.WEST);
		frame.add(new JButton("Center"), BorderLayout.CENTER);
	}
	
	// Figure 21-13, How to display a message
	// NOTE: The dialog box is modal, meaning it's always on top of the application, and users
	// can't continue until they have responded to the dialog.
	public static void displayDialogBox() {
		// Display an information message
		// NOTE: showMessageDialog() has 4 parameters: (1) the parent container, (2) the message,
		// (3) the title, and (4) the type of dialog
		JOptionPane.showMessageDialog(frame, "The software has been updated.",
				"Updated", JOptionPane.INFORMATION_MESSAGE);
		
		// Display an error message
		JOptionPane.showMessageDialog(frame, "The internet could not be accessed because it"
				+ " doesn't exist.", "Resource doesn't exist", JOptionPane.ERROR_MESSAGE);
	}
	
	// Figure 21-14, How to confirm an operation
	public static void confirmOperation() {
		// Display a question dialog with Yes and No buttons
		int option = JOptionPane.showConfirmDialog(frame, "Do you want to create a new file?",
				"New file", JOptionPane.YES_NO_OPTION);
		
		// Display a question dialog with a warning icon
		int option2 = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete"
				+ " the Internet?\nThis operation cannot be undone.", "Are you sure?", 
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// NOTE: By default, showConfirmDialog() displays a question icon. 
		// You can specify another type of icon by supplying the fifth parameter.
		
		// NOTE: To determine the option selected by the user, it's common to use a switch
		// statement, but it's also possible to use an if/else statement.
		switch (option2) {
			case JOptionPane.YES_OPTION:
				System.out.println("You clicked the Yes button.");
				break;
			case JOptionPane.NO_OPTION:
				System.out.println("You clicked the No button.");
				break;
			case JOptionPane.CLOSED_OPTION:
				System.out.println("You closed the dialog.");
				break;
		}
	}
}
