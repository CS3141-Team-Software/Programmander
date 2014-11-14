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
	
	//Class Variables
	private EditorWindow editorReference;
	private String fileName;
	private String unitType;
	
	public DecisionWindow(String question, EditorWindow editorReference, String fileName, String unitType){
		this.question = question;
		this.editorReference = editorReference;
		this.fileName = fileName;
		this.unitType = unitType;
		initializeScreenSize();
		initializeButtons();
		
	}
	
	private void initializeButtons() {
		Rectangle yesButtonBounds = new Rectangle(0,0,75,25);
		yesButton.setBounds(yesButtonBounds);
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editorReference.saveFile(fileName, editorReference.getTextArea().getText(), unitType);
			}
		});
		getContentPane().add(yesButton);
		yesButton.setVisible(true);
		
		Rectangle noButtonBounds = new Rectangle(0,30,75,25);
		noButton.setBounds(noButtonBounds);
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		getContentPane().add(noButton);
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
		this.setVisible(true);
	}
}
