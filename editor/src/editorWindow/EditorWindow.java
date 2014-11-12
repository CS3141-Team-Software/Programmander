package editorWindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
	
	private File currFile;
	private String currUnitType;
	private String currFileName;
	
	private RSyntaxTextArea textArea = new RSyntaxTextArea(10, 10);
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	//Comboboxes
	private JComboBox<String> comboBoxFileSelector;
	
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
		comboBoxFileSelector = new JComboBox<String>();
		//Set up the file select combo box
		comboBoxFileSelector.setBounds(20, 5, 300, 35);
		for(String s : getAIList()){
			comboBoxFileSelector.addItem(s);
		}
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
	
	//Open their selected file. 
	private String openFile (String fileName) {
		String unitType = null;
		
		String fileText = null;
		try {
			fileText = readFile(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error reading user file.");
			System.exit(-1);
		}
		
		if (fileText != null) {
			//Parse out the first line to get the unit type
			if (fileText.startsWith("//Scout")) {
				unitType = "Scout";
			} else if (fileText.startsWith("//Knight")) {
				unitType = "Knight";
			} else if (fileText.startsWith("//Spawner")) {
				unitType = "Spawner";
			} else {
				System.err.println("Invalid file heading. First line needs to be \"//(UnitType)\"");
				System.exit(-1);
			}
		}
		createFile(fileName, unitType); //rewrite the unit header into the file
		
		
		//TODO: Parse out the update method
		return null;
	}
	
	//Create a NEW file with their specified file name and unit type.
	private void createFile(String fileName, String unitType) {
		
		currFile = new File(fileName + ".java");
		
		File unitHeader = null;
		
		if (unitType == "Scout") {
			unitHeader = new File("scout.head");
		} else if (unitType == "Knight") {
			unitHeader = new File("knight.head");
		} else if (unitType == "Spawner") {
			unitHeader = new File("spawner.head");
		} else {
			System.err.println("Problem loading header file.");
			System.exit(-1);
		}
		
		//Get string info from unitHeader file
		String headerText = null;
		
		try {
			headerText = readFile(unitHeader);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.err.println("Problem reading header file.");
			System.exit(-1);
		}
		
		
		try {
			//Write info from unitHeader into the current file.
			BufferedWriter writer = new BufferedWriter(new FileWriter(currFile));
			
			//Write the FULL text of the header to the file.
			//Also write a comment that tells us what unit type they have.
			writer.write("//" + unitType);
			writer.write(headerText);			
			writer.write("\n}");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error writing to a class file");
			System.exit(-1);
		} 		
	}
	
	//Save their written code 
	private void saveFile (String textAreaText) {
		try {
			//Will append to end of our half-created file.
			BufferedWriter writer = new BufferedWriter(new FileWriter(currFile, true));
			
			writer.write("\n");
			//Write the FULL text of the update() method to the file
			writer.write(textAreaText);			
			
			writer.write("\n}");
			
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error writing to a class file");
			System.exit(-1);
		}
	}

	
	public ArrayList<String> getAIList(){
		ArrayList<String> AINames = new ArrayList<String>();
		String str;
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader("ais/aiNames"));
		}catch(FileNotFoundException e) {
			System.err.println("Could not load aiNames file");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			while((str = in.readLine()) != null){
				AINames.add(str);
			}
			
			in.close();
		}catch(IOException e) {
			System.err.println("Could not read aiNames");
			e.printStackTrace();
			System.exit(1);
		}
		
		return AINames;
	}
	
	private String readFile(File fileName) throws IOException {

	    StringBuilder fileContents = new StringBuilder((int)fileName.length());
	    Scanner scanner = new Scanner(fileName);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
}