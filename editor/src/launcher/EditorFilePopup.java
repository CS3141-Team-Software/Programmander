package launcher;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import editorWindow.DecisionWindow;
import editorWindow.EditorWindow;

public class EditorFilePopup extends JFrame {

	//Size of pop up window
	private Dimension screenSize = new Dimension(310,100);

	private ArrayList<String> aiNames = null;
	
	//Combo boxes
	JComboBox<String> filesComboBox = new JComboBox<String>();

	//JLabels
	private JLabel fileNameLabel = new JLabel("File Name:");

	private EditorWindow editorReference;
	private MainWindow mainReference;
	private EditorFilePopup thisWindow = this;
	
	//Buttons
	private JButton openButton = new JButton("OPEN");
	private JButton cancelButton = new JButton("CANCEL");
	private JButton newButton = new JButton("NEW");

	public EditorFilePopup(EditorWindow editor) {
		this.setLayout(null);
		editorReference = editor;
		aiNames = editor.getAIList();
		
		initializeScreenSize();
		initializeJLabels();
		initializeButtons();
		initializeComboBoxes();

	}

	//============================================Initialize Methods Below=====================================================
	/*private void initializeTextFields() {
		Rectangle saveFileTextFieldBounds = new Rectangle(96,40,150,20);
		saveFileTextField.setBounds(saveFileTextFieldBounds);
		getContentPane().add(saveFileTextField);
		saveFileTextField.setVisible(true);
	}*/

	private void initializeButtons() {
		Rectangle openButtonBounds = new Rectangle(5,40,95,25);
		openButton.setBounds(openButtonBounds);
		openButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String fileName = (String) filesComboBox.getSelectedItem();
				
				editorReference.openFile(fileName);
				editorReference.setVisible(true);
				
				thisWindow.dispose();
			}
		});
		
		getContentPane().add(openButton);
		openButton.setVisible(true);
		
		Rectangle newButtonBounds = new Rectangle(105, 40, 95, 25);
		newButton.setBounds(newButtonBounds);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Launch editor window with a blank update method.
			}
		});
		getContentPane().add(newButton);
		newButton.setVisible(true);
		
		//Set cancel button bounds
		Rectangle cancelButtonBounds = new Rectangle(205,40,95,25);
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
		
		//Set the fileLabelbounds
		Rectangle fileNameRectangleBounds = new Rectangle(10,10,150,20);
		fileNameLabel.setBounds(fileNameRectangleBounds);
		getContentPane().add(fileNameLabel);
		fileNameLabel.setVisible(true);
	}

	private void initializeComboBoxes() {
		for(String s : aiNames){
			filesComboBox.addItem(s);
		}
		Rectangle comboBoxBounds = new Rectangle(95,10,200,20);
		filesComboBox.setBounds(comboBoxBounds);
		getContentPane().add(filesComboBox);
		filesComboBox.setVisible(true);
	}

	private void initializeScreenSize() {
		//Sets the dimensions of the JFrame.
		this.setSize(screenSize);// TODO Auto-generated method stub
		
		this.setMinimumSize(screenSize);
		this.setMaximumSize(screenSize);
		this.setPreferredSize(screenSize);
		getContentPane().setLayout(null);
	}

	public void setMainReference(MainWindow main) {
		mainReference = main;
	}

}
