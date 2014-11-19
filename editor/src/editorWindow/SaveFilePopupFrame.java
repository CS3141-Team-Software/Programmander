package editorWindow;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class SaveFilePopupFrame extends JFrame{

	//Size of pop up window
	private Dimension windowSize = new Dimension(260,150);
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	//String array of unit types
	private String[] unitTypes = {"Scout", "Knight", "Spawner"};

	//file name to be saved.
	private String fileName;
	private RSyntaxTextArea textAreaText;
	
	//Combo boxes
	JComboBox<String> unitTypesComboBox = new JComboBox<String>();

	//JLabels
	private JLabel unitTypeLabel = new JLabel("Unit Type Selector");
	private JLabel saveFileNameLabel = new JLabel("File Name");

	//TextFields
	private JTextField saveFileTextField = new JTextField(20);

	private EditorWindow editorReference;
	private JButton saveFileButton = new JButton("SAVE");
	private JButton cancelButton = new JButton("CANCEL");
	
	private int innerWidth;
	private int innerHeight;
	
	/**
	 * Constructor
	 */
	public SaveFilePopupFrame(RSyntaxTextArea textAreaText, EditorWindow editor){
		editorReference = editor;
		this.textAreaText = textAreaText;
		//Initialize the text area that gives you syntax highlighting.
		innerWidth = (int)screenSize.getWidth() - getInsets().left - getInsets().right;
		innerHeight = (int)screenSize.getHeight() - getInsets().bottom - 135;	 //-135 for reasons
		
		initializeScreenSize();
		initializeComboBoxes();
		initializeJLabels();
		initializeTextFields();
		initializeButtons();
	}

	/**
	 * This method checks to see if the filename they want to use to save their ai file already exists.
	 * @return
	 */
	private boolean fileExistsAlready(){
		for(String s : editorReference.getAIList()){

			if (fileName.contains(".java")) {
				if(s.equalsIgnoreCase(fileName)){
					return true;
				}
			} else {
				if(s.equalsIgnoreCase(fileName + ".java")) {
					return true;
				}
			}
		}
		return false;
	}

	//============================================Initialize Methods Below=====================================================
	private void initializeTextFields() {
		Rectangle saveFileTextFieldBounds = new Rectangle(96,40,150,20);
		saveFileTextField.setBounds(saveFileTextFieldBounds);
		getContentPane().add(saveFileTextField);
		saveFileTextField.setVisible(true);
	}

	private void initializeButtons() {
		Rectangle saveButtonBounds = new Rectangle(175,90,75,25);
		final SaveFilePopupFrame thisWindow = this;
		saveFileButton.setBounds(saveButtonBounds);
		saveFileButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				fileName = saveFileTextField.getText();
				//CHECK THE FILE NAME HERE
				if(fileName.length() == 0){
					JOptionPane.showMessageDialog(null,"Please give a file name for your AI");

				} else if (fileExistsAlready()){ //TODO: Method to check if the file is already existing.
					DecisionWindow fileExists = new DecisionWindow("Do you want to overwrite " + fileName, editorReference, fileName, (String)unitTypesComboBox.getSelectedItem(), thisWindow);
					fileExists.setLocation(new Point((int)((innerWidth/2) - windowSize.getWidth()/2),(int)((innerHeight/2) - windowSize.getHeight()/2) + 70));
				} else if (!fileExistsAlready()){
					JOptionPane.showMessageDialog(null,"File Saved");
					editorReference.saveFile(fileName, textAreaText.getText(), (String) unitTypesComboBox.getSelectedItem());
					dispose();
				}	else {
					JOptionPane.showMessageDialog(null,"Congratzs you did something logically impossible.");
				}

			}
		});
		getContentPane().add(saveFileButton);
		saveFileButton.setVisible(true);

		//Set cancel button bounds
		Rectangle cancelButtonBounds = new Rectangle(10,90,100,25);
		cancelButton.setBounds(cancelButtonBounds);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		getContentPane().add(cancelButton);
		cancelButton.setVisible(true);
	}

	private void initializeJLabels() {
		//Set unityTypeLabel bounds
		Rectangle unitTypeLabelBounds = new Rectangle(10,10,150,20);
		unitTypeLabel.setBounds(unitTypeLabelBounds);
		getContentPane().add(unitTypeLabel);
		unitTypeLabel.setVisible(true);

		//Set the saveFileLabelbounds
		Rectangle saveFileNameRectangleBounds = new Rectangle(10,40,150,20);
		saveFileNameLabel.setBounds(saveFileNameRectangleBounds);
		getContentPane().add(saveFileNameLabel);
		saveFileNameLabel.setVisible(true);
	}

	private void initializeComboBoxes() {
		for(String s : unitTypes){
			unitTypesComboBox.addItem(s);
		}
		Rectangle comboBoxBounds = new Rectangle(145,10,100,20);
		unitTypesComboBox.setBounds(comboBoxBounds);
		getContentPane().add(unitTypesComboBox);
		unitTypesComboBox.setVisible(true);
	}

	private void initializeScreenSize() {
		//Sets the dimensions of the JFrame.
		this.setSize(windowSize);
		this.setMinimumSize(windowSize);
		this.setMaximumSize(windowSize);
		this.setPreferredSize(windowSize);
		getContentPane().setLayout(null);
	}

}
