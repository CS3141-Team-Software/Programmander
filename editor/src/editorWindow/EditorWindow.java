package editorWindow;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

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
	private JComboBox<String> comboBoxFileSelector = new JComboBox<String>();

	//Buttons
	private JButton loadFileButton = new JButton("Load File");
	private JButton saveFileButton = new JButton("Save File");

	private int innerWidth;
	private int innerHeight;

	public EditorWindow() {

		//Sets the dimensions of the JFrame.
		this.setSize(screenSize);
		this.setMinimumSize(screenSize);
		this.setMaximumSize(screenSize);
		this.setPreferredSize(screenSize);

		getContentPane().setLayout(null);		//Set absolute layout.

		//Initialize the text area that gives you syntax highlighting.
		innerWidth = (int)screenSize.getWidth() - getInsets().left - getInsets().right;
		innerHeight = (int)screenSize.getHeight() - getInsets().bottom - 135;	 //-135 for reasons

		//Initialize windows.
		initializeTextArea(innerWidth, innerHeight, null);
		//initializeComboBoxes();
		initializeButton();
		setTitle("Text Editor Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	//======================================Save and load file operation methods========================================
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

	//Open their selected file. 
	private String openFile (String fileName) {
		String fileText = null;
		try {
			fileText = readFile(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error reading user file.");
			System.exit(-1);
		}

		//TODO: Parse out the update method
		String theirCode = "";
		String fileLine;
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader(fileText));
		}catch(FileNotFoundException e) {
			System.err.println("Could not load aiNames file");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			while((fileLine = in.readLine()) != null){
				//Is it the update() line?
				if (fileLine.contains("update(Gamestate")) {
					theirCode = fileLine;
					break; //we found the update method!
				}
			}

			while((fileLine = in.readLine()) != null){
				theirCode = theirCode + fileLine;
			}


			in.close();
		}catch(IOException e) {
			System.err.println("Could not read aiNames");
			e.printStackTrace();
			System.exit(1);
		}

		return theirCode;
	}

	//Create a NEW file with their specified file name and unit type.
	public void saveFile(String fileName, String textAreaText, String unitType) {

		File dir = new File("./src/playerCode"); 

		if (!dir.exists()) {
			dir.mkdirs();
		}

		if (fileName.endsWith(".java")) {
			currFile = new File("./src/playerCode/" + fileName);

			boolean exists = false;
			for (int i = 0; i < comboBoxFileSelector.getItemCount(); i++) {
				if (comboBoxFileSelector.getItemAt(i) == fileName) {
					exists = true;
				}
			}
			if (!exists) {
				comboBoxFileSelector.addItem(fileName);
			}

		} else {
			currFile = new File("./src/playerCode/" + fileName + ".java");

			boolean exists = false;
			for (int i = 0; i < comboBoxFileSelector.getItemCount(); i++) {
				if (comboBoxFileSelector.getItemAt(i) == (fileName + ".java")) {
					exists = true;
				}
			}
			if (!exists) {
				comboBoxFileSelector.addItem(fileName + ".java");
			}
		}

		File unitHeader = null;

		//Set the correct header file

		if (unitType == "Scout") {
			unitHeader = new File("./includes/Scout.head");
		} else if (unitType == "Knight") {
			unitHeader = new File("./includes/Knight.head");
		} else if (unitType == "Spawner") {
			unitHeader = new File("./includes/Spawner.head");
		} else {
			System.err.println("Error reading header files/unit types.");
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

		if (fileName.endsWith(".java")) {
			//replace the fileName with the fileName - .java
			headerText = headerText.replace("**x**", fileName.substring(0, fileName.length()-6)); 
		} else {
			headerText = headerText.replace("**x**", fileName);
		}

		try {
			//Write info from unitHeader into the current file.
			BufferedWriter writer = new BufferedWriter(new FileWriter(currFile));

			//Write the FULL text of the header to the file.
			writer.write(headerText);			
			writer.write("\n");

			//Write the FULL text of the update() method to the file
			writer.write(textAreaText);

			writer.write("\n\n}");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println
			
("Error writing to a class file");
			System.exit(-1);
		}
	}

	/**
	 * This method reads a file name to load things into the text area in the editor window.
	 * @param fileName
	 * @return fileContents - a string with the file contents for the text area to parse
	 * @throws IOException
	 */
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

	/**
	 * Method to create the save file frame that chooses options.
	 */
	private void createSaveFilePopup() {
		SaveFilePopupFrame saveFrame = new SaveFilePopupFrame(textArea, this);
		saveFrame.setLocation(new Point((int)screenSize.getWidth() / 2 - (saveFrame.getWidth()/2), (int)screenSize.getHeight() / 2 - (saveFrame.getHeight()/2)));
		saveFrame.setVisible(true);
	}


	//==============================================Getters and setters=================================================
	/**
	 * This method gets the list of playercoded AI's from the directory.
	 * @return
	 */
	public ArrayList<String> getAIList(){

		ArrayList<String> AINames = new ArrayList<String>();
		File directory = null;

		try{
			directory = new File("./src/playerCode");
		}catch(Exception e) {
			System.err.println("Could not find playerCode folder");
			e.printStackTrace();
			System.exit(1);
		}

		//If empty directory, don't do anything
		if (directory.listFiles() ==  null) {
			return AINames;
		}

		//If there's stuff to list, list it
		File[] fileList = directory.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i] != null && fileList[i].getName().endsWith(".java")) {
				AINames.add(fileList[i].getName());
			}
		}

		return AINames;
	}

	/**
	 * Method to get the textArea
	 * @return
	 */
	public RSyntaxTextArea getTextArea(){
		return textArea;
	}

	//==============================================Initialize Methods==================================================
	/**
	 * 
	 * @param innerWidth2
	 * @param innerHeight2
	 * @param fileStringBasedOnComboBox
	 */
	private void initializeTextArea(int innerWidth2, int innerHeight2, String fileStringBasedOnComboBox) {
		//TODO: change this method to load up the text from a file that is already there if the file name from the combo box is not null
		RSyntaxTextArea newTextArea = new RSyntaxTextArea("string to add");
		getContentPane().remove(textArea);
		textArea.setAlignmentY(Component.TOP_ALIGNMENT);
		textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);

		RTextScrollPane sp = new RTextScrollPane(newTextArea);

		int textWidth = (int)(innerWidth *.80);
		sp.setBounds(10, 0, textWidth, innerHeight);
		sp.setVisible(true);
		textArea.setVisible(true);
		getContentPane().add(sp);
	}

	/**
	 * Method to initialize the 
	 */
	private void initializeComboBoxes(){
		//Set up the file select combo box
		comboBoxFileSelector.setBounds(20, 5, 300, 35);
		for(String s : getAIList()) {
			comboBoxFileSelector.addItem(s);
		}
		getContentPane().add(comboBoxFileSelector);
	}

	/**
	 * Method to initializeButton
	 */
	private void initializeButton(){

		Color buttonColor = new Color(233, 233, 233);
//		//loadFile Button
//		loadFileButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				loadFile("string");
//			}
//		});
//		loadFileButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
//		loadFileButton.setBounds(350, 5, 150, 35);
//		getContentPane().add(loadFileButton);
//		loadFileButton.setBackground(runButtonColor);

		//saveFile Button
		saveFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createSaveFilePopup();
			}
		});
		saveFileButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		saveFileButton.setBounds(1400, 5, 150, 35);
		getContentPane().add(saveFileButton);
		saveFileButton.setBackground(buttonColor);
	}

}