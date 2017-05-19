package ch22_figures;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

// Figures 22-7 & 22-8
public class NameDialog extends JDialog {
	private JTextField dialogNameField;
	private static JFrame frame;
	
	public NameDialog(java.awt.Frame parent) {
		// Create an instance of JDialog
		// NOTE: 1st parameter specifies the parent of the dialog, 2nd parameter specifies
		// title of dialog, and 3rd parameter determines whether dialog is modal
		super(parent, "Name Dialog", true);
		// Close dialog & release its resources when user closes it
		// NOTE: Without this code, Java would hide the dialog but not free its resources
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		dialogNameField = new JTextField(20);
		
		JButton okayButton = new JButton("Okay");
		okayButton.addActionListener((ActionEvent) -> {
			// TODO: Add code here
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener((ActionEvent) -> {
			dispose();
		});
		
		this.add(new JLabel("Name:"));
		this.add(dialogNameField);
		this.add(okayButton);
		this.add(cancelButton);
		this.pack();
		
		this.setVisible(true);
	}
	
	private void createFrame() {
		frame = new JFrame("Chapter 22: How to develop a GUI with Swing (part 2)");
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

	public static void main(String[] args) {
		NameDialog nd = new NameDialog(frame);
	}
}

// NOTE: Differences between a JFrame component and a JDialog component: 
// (1) A dialog is designed to have a parent window. As a result, it is displayed on top of its
// parent window, even if the dialog is not the active window.
// (2) On most OS, a dialog has fewer window controls.
// (3) On most OS, a dialog doesn't get an entry in the taskbar or window list. As a result,
// you can't switch directly to the dialog using window switch commands. Instead, you need to 
// switch to the window that owns the dialog. 
// (4) A dialog can be modal. 