package ch22_figures;

import java.awt.*;
import javax.swing.*;

public class SwingNotes2 {
	public static JFrame frame;  // the main window of the app
	public static JPanel panel = new JPanel();

	public static void main(String[] args) {
		createFrame();
		useGridBagLayoutManager();
		frame.add(panel);
		//createAndAddJLabel();
		//workWithTextFields();
		
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
	
	// Figure 22-1, How to work with labels
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
	
	// Figure 22-2, How to work with text fields
	public static void workWithTextFields() {
		// Create a text box for approx. 20 characters
		JTextField codeField = new JTextField(20);
		panel.add(codeField);
		// Get text from the text box
		String code = codeField.getText();
		System.out.println(code);
		// Set text in the text box
		codeField.setText("sample text");
		// Create a read-only text box (can copy but can't paste)
		codeField.setEditable(false);
		// Disable a text box (can't copy or paste)
		codeField.setEnabled(false);
	}
	
	// Figure 22-3, An introduction to the GridBagLayout manager
	public static void useGridBagLayoutManager() {
		// NOTE: The GridBagLayout is the most powerful and flexible layout manager in Swing.
		
		panel.setLayout(new GridBagLayout());
		
		// Create a GridBagConstraints object to control the layout of components
		// inside a container that uses the GridBagLayout manager.
		// NOTE: You can use the same GridBagConstraints object for multiple widgets,
		// but you must reset any values you don't want to use in the next widget.
		GridBagConstraints c = new GridBagConstraints();
		
		// Position component on the grid
		// NOTE: Coordinates represent cells, not pixels
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;  // right align
		// NOTE: The anchor field (LINE_END) controls where the layout manager places the component
		// within the cell if the cell is larger than the component. Default is center.
		panel.add(new JLabel("Code:"), c);
		
		JTextField codeField = new JTextField(20);
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(codeField, c);
		
		c.gridx = 0; 
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		panel.add(new JLabel("Description:"), c);
		
		JTextField descriptionField = new JTextField(20);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(descriptionField, c);
		
		c.gridx = 0;
		c.gridy = 2; 
		c.anchor = GridBagConstraints.LINE_END;
		panel.add(new JLabel("Price:"), c);
		
		JTextField priceField = new JTextField(20);
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(priceField, c);
		
		c.gridx = 0;
		c.gridy = 3; 
		c.anchor = GridBagConstraints.LINE_START;
		c.gridwidth = 2;  // how many cells the component takes up
		panel.add(new JLabel("This label spans both columns."), c);
	}
}
