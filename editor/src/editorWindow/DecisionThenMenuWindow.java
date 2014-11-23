package editorWindow;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DecisionThenMenuWindow extends JFrame{
	//question which you decide on
	private String question = "";
	
	//Size of pop up window
	private Dimension screenSize = new Dimension(260,150);
	
	private SaveFileThenMenuPopupFrame popupRef;
	
	//Jbuttons
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	
	//Labels
	private JLabel questionLabel;
	
	//Class Variables
	private EditorWindow editorReference;
	private String fileName;
	private String unitType;
	
	public DecisionThenMenuWindow(String question, String title, EditorWindow editorReference, String fileName, String unitType, SaveFileThenMenuPopupFrame popupRef){
		this.popupRef = popupRef;
		this.question = question;
		this.editorReference = editorReference;
		this.fileName = fileName;
		this.unitType = unitType;
		this.setTitle(title);
		this.editorReference.setUpCloseOperation(this);
		initializeScreenSize();
		initializeButtons();
		questionLabel = new JLabel(question);
		questionLabel.setBounds(41, 20, 240, 40);
		getContentPane().add(questionLabel);
		questionLabel.setVisible(true);
	}
	
	private void initializeButtons() {
		Rectangle yesButtonBounds = new Rectangle(41,80,75,25);
		yesButton.setBounds(yesButtonBounds);
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editorReference.saveFile(fileName, editorReference.getTextArea().getText(), unitType);
				editorReference.reEnable();
				editorReference.openMain();
				dispose();		//Close this window when you save overwrite
			}
		});
		getContentPane().add(yesButton);
		yesButton.setVisible(true);
		
		Rectangle noButtonBounds = new Rectangle(141,80,75,25);
		noButton.setBounds(noButtonBounds);
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editorReference.reEnable();
				dispose();
			}
		});
		getContentPane().add(noButton);
		yesButton.setVisible(true);
		
	}
	
	//==============================================Initialize methods======================================
	private void initializeScreenSize() {
		//Sets the dimensions of the JFrame.
		this.setSize(screenSize);
		this.setMinimumSize(screenSize);
		this.setMaximumSize(screenSize);
		this.setPreferredSize(screenSize);
		getContentPane().setLayout(null);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
}
