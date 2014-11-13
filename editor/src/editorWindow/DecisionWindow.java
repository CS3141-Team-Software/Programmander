package editorWindow;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DecisionWindow extends JFrame{
	//question which you decide on
	private String question = "";
	
	//Size of pop up window
	private Dimension screenSize = new Dimension(260,150);
	
	//Jbuttons
	private JButton yesButton = new JButton("YES");
	private JButton noButton = new JButton("NO");
	
	public DecisionWindow(String question, EditorWindow editorReference, String fileName, String[] unitTypes){
		this.question = question;
		initializeScreenSize();
		
	}
	
	private void initializeButtons() {
		Rectangle saveButtonBounds = new Rectangle(175,90,75,25);
		yesButton.setBounds(saveButtonBounds);
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		getContentPane().add(yesButton);
		yesButton.setVisible(true);
		
	}
	//==============================================Initialize methods
	private void initializeScreenSize() {
		//Sets the dimensions of the JFrame.
		this.setSize(screenSize);
		this.setMinimumSize(screenSize);
		this.setMaximumSize(screenSize);
		this.setPreferredSize(screenSize);
		getContentPane().setLayout(null);
	}
}
