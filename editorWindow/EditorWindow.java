package editorWindow;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

/**
 * This is the editor window.
 * We take this window and make a text entry system for the users "code"
 * 
 * @author Kyle D.
 */
public class EditorWindow extends JFrame {
	
	JPanel mainPanel = new JPanel();
	
	public EditorWindow() {
		
		initializeGUIElements();
		
		//Initialize the frame dimensions.
		setMaximumSize(new Dimension(1720, 880));
		getContentPane().setSize(new Dimension(1720, 880));
		getContentPane().setPreferredSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		getContentPane().setMaximumSize(new Dimension(1720, 880));
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
	}
	
	/**
	 * Method to initalize all of the gui elements that are on this frame
	 */
	private void initializeGUIElements() {
		
		//Main panel.
		mainPanel.setBounds(0, 0, 500, 500);
		getContentPane().add(mainPanel);
	}
}
