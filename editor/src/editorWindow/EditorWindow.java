package editorWindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorWindow extends JFrame {

	
	private RSyntaxTextArea textArea = new RSyntaxTextArea(10, 10);
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	//Comboboxes
	private JComboBox<String> comboBoxFileSelector = new JComboBox<String>();
	
	//Buttons
	private JButton loadFileButton = new JButton("Load File");

	private int innerWidth;
	private int innerHeight;
	
	public EditorWindow() {
		
		//Sets the dimensions of the JFrame.
		this.setSize(screenSize);
		this.setMinimumSize(screenSize);
		this.setMaximumSize(screenSize);
		this.setPreferredSize(screenSize);
		
		getContentPane().setLayout(null);

		//Initialize the text area that gives you syntax highlighting.
		innerWidth = (int)screenSize.getWidth() - getInsets().left - getInsets().right;
		innerHeight = (int)screenSize.getHeight() - getInsets().top - getInsets().bottom - 130;	 //-130 for reasons
		initializeTextArea(innerWidth, innerHeight, null);
		initializeComboBoxes();
		initializeButton();
		setTitle("Text Editor Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Method to initializeButton
	 */
	private void initializeButton(){
		Color runButtonColor = new Color(233, 233, 233);
		//Run AI Button
		loadFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadFile("string");
			}
		});
		loadFileButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		loadFileButton.setBounds(350, 5, 150, 35);
		getContentPane().add(loadFileButton);
		loadFileButton.setBackground(runButtonColor);
	}
	
	/**
	 * Method to initialize the 
	 */
	private void initializeComboBoxes(){
		//Set up the file select combo box
		comboBoxFileSelector.setBounds(20, 5, 300, 35);
//		for(String s : getMapList()){
//			comboBoxFileSelector.addItem(s);
//		}
		getContentPane().add(comboBoxFileSelector);
	}
	
	/**
	 * Method to load up a file that was selected by the combo box.
	 */
	private void loadFile(String fileStringBasedOnComboBox){
		//TODO: load the file into the text area.
		//These variables are our available screensize, including borders
		innerWidth = (int)screenSize.getWidth() - getInsets().left - getInsets().right;
		innerHeight = (int)screenSize.getHeight() - getInsets().top - getInsets().bottom - 130;	 //-130 for reasons
		initializeTextArea(innerWidth, innerHeight, fileStringBasedOnComboBox);
	}
	
	/**
	 * 
	 * @param innerWidth2
	 * @param innerHeight2
	 * @param fileStringBasedOnComboBox
	 */
	private void initializeTextArea(int innerWidth2, int innerHeight2, String fileStringBasedOnComboBox) {
		if(fileStringBasedOnComboBox == null){
			textArea.setAlignmentY(Component.TOP_ALIGNMENT);
			textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
			textArea.setCodeFoldingEnabled(true);
	
			RTextScrollPane sp = new RTextScrollPane(textArea);
			
			int textWidth = (int)(innerWidth *.66);
			sp.setBounds(10, 50, textWidth, innerHeight);
			sp.setVisible(true);
			textArea.setVisible(true);
			getContentPane().add(sp);
		} else {
			RSyntaxTextArea newTextArea = new RSyntaxTextArea("string to add");
			getContentPane().remove(textArea);
			textArea.setAlignmentY(Component.TOP_ALIGNMENT);
			textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
			textArea.setCodeFoldingEnabled(true);
	
			RTextScrollPane sp = new RTextScrollPane(newTextArea);
			
			int textWidth = (int)(innerWidth *.66);
			sp.setBounds(10, 50, textWidth, innerHeight);
			sp.setVisible(true);
			textArea.setVisible(true);
			getContentPane().add(sp);
		}
		
	}

	
}