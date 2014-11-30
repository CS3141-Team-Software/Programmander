package editorWindow;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import launcher.EditorFilePopup;
import launcher.MainWindow;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorWindow extends JFrame {
	
	private MainWindow mainReference;
	private JFrame saveBeforeMenu;

	private File currFile;
	private String currFileName;
	private String currUnitType;

	private RSyntaxTextArea textArea = new RSyntaxTextArea(10, 10);
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	//Comboboxes
	private JComboBox<String> comboBoxFileSelector = new JComboBox<String>();

	//Buttons
	private JButton loadFileButton = new JButton("Load File");
	private JButton mainMenuButton = new JButton("Main Menu");
	private JButton saveFileButton = new JButton("Save File");

	private int innerWidth;
	private int innerHeight;

	private boolean wasOpened = false; //Flag to tell if the file was opened or not

	//textareas for points
	private int totalPoints = 40;
	private int remainingPoints = 40;
	private JTextField agilityPoints = new JTextField("0", 7);
	private JTextField attackPoints = new JTextField("0", 7);
	private JTextField defensePoints = new JTextField("0", 7);
	private JTextField healthPoints = new JTextField("0", 7);

	//Labels 
	private JLabel agilityLabel = new JLabel("Agility:");
	private JLabel attackLabel = new JLabel("Attack:");
	private JLabel defenseLabel = new JLabel("Defense:");
	private JLabel healthLabel = new JLabel("Health:");
	private JLabel totalPointsLabel = new JLabel("Total Points:");
	private JLabel pointsLeftLabel = new JLabel(totalPoints + "");

	public EditorWindow(String unitType) {

		//Sets the dimensions of the JFrame.
		this.setSize(screenSize);
		this.setMinimumSize(screenSize);
		this.setMaximumSize(screenSize);
		this.setPreferredSize(screenSize);

		getContentPane().setLayout(null); //Set absolute layout.

		//Initialize the text area that gives you syntax highlighting.
		innerWidth = (int)screenSize.getWidth() - getInsets().left - getInsets().right;
		innerHeight = (int)screenSize.getHeight() - getInsets().bottom - 135;	 //-135 for reasons

		//Initialize windows.
		initializeTextArea(innerWidth, innerHeight, null);
		//initializeComboBoxes();
		initializeButtons();
		//Initialize the labels for point distribution
		initializeLabels();
		initializePointsTextArea();
		setTitle("Text Editor Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	//======================================Save and load file operation methods========================================
	//Open their selected file. 
	public void openFile (String fileName, String unitType) {

		wasOpened = true;
		currFileName = fileName;
		currUnitType = unitType;

		String theirCode = "";
		String fileLine;
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader(new File("./src/playerCode/" + fileName)));
		}catch(FileNotFoundException e) {
			System.err.println("Could not load aiNames file");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			Pattern p = Pattern.compile(".*update\\s*\\(\\s*GameState.*", Pattern.CASE_INSENSITIVE);


			while((fileLine = in.readLine()) != null){

				//Is it the update() line?
				if (p.matcher(fileLine).matches()) {
					theirCode = fileLine;
					break; //we found the update method!
				} 
			}

			if (fileLine == null) {
				//They don't have a properly formatted update method.
				//Throw an error window. 
				JOptionPane.showMessageDialog(this, "Cannot open file. \nMake sure the update() method signature is properly formatted.");
				return;
			}

			fileLine = in.readLine();
			String nextLine = in.readLine();

			while(fileLine != null) {

				if (nextLine == null) { //if we're at the last line of our file
					break;
				} else {
					theirCode = theirCode + "\n" + fileLine;
					fileLine = nextLine;
					nextLine = in.readLine();

				}
			}

			in.close();

		}catch(IOException e) {
			System.err.println("Could not read aiNames");
			e.printStackTrace();
			System.exit(1);
		}

		getTextArea().setText(theirCode);
	}

	//Create a NEW file with their specified file name and unit type.
	public void saveFile(String fileName, String textAreaText, String unitType) {

		File dir = new File("./src/playerCode"); 

		if (!dir.exists()) {
			dir.mkdirs();
		}


		if (wasOpened) {
			//If we already opened the file (it exists in the combo box.)
			currFile = new File("./src/playerCode/" + currFileName);
			fileName = currFileName;

		} else {
			if (fileName.endsWith(".java")) {
				currFile = new File("./src/playerCode/" + fileName);

				boolean exists = false;
				for (int i = 0; i < comboBoxFileSelector.getItemCount(); i++) {
					if (comboBoxFileSelector.getItemAt(i) == fileName) {
						exists = true;
						break;
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
						break;
					}
				}
				if (!exists) {
					comboBoxFileSelector.addItem(fileName + ".java");
				}
			}
		}

		File unitHeader = null;

		//Set the correct header file

		if (unitType == "Scout") {
			unitHeader = new File("./headers/Scout.head");
		} else if (unitType == "Knight") {
			unitHeader = new File("./headers/Knight.head");
		} else if (unitType == "Spawner") {
			unitHeader = new File("./headers/Spawner.head");
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
			headerText = headerText.replace("**x**", fileName.substring(0, fileName.length()-5)); 
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

			writer.write("\n}");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error writing to a class file");
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
				fileContents.append(scanner.nextLine() + "\n");
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
		setUpCloseOperation(saveFrame);
		saveFrame.setVisible(true);
	}

	private void createSaveFileThenMenuPopup() {
		SaveFileThenMenuPopupFrame saveFrame = new SaveFileThenMenuPopupFrame(textArea, this);
		saveFrame.setLocation(new Point((int)screenSize.getWidth() / 2 - (saveFrame.getWidth()/2), (int)screenSize.getHeight() / 2 - (saveFrame.getHeight()/2)));
		setUpCloseOperation(saveFrame);
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

		Object[] aiArray = AINames.toArray();
		AINames.clear();
		//Sort the list alphabetically.
		Arrays.sort(aiArray);

		for (Object s : aiArray) {
			AINames.add((String) s);
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
		textArea.setAlignmentY(Component.TOP_ALIGNMENT);
		textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);

		RTextScrollPane sp = new RTextScrollPane(textArea);

		int textWidth = (int)(innerWidth *.80);
		sp.setBounds(10, 10, textWidth, innerHeight);
		textArea.setText("public int update(Gamestate g) { \n\n}");
		sp.setVisible(true);
		textArea.setVisible(true);
		getContentPane().add(sp);
	}

	/**
	 * Method to initialize all the labels inside the editor window GUI
	 */
	private void initializeLabels(){
		int pointsLabelX = (int) (screenSize.getWidth() * .85);
		int pointsLabelY = (int) (screenSize.getHeight() * .33);

//		agilityLabel.setBounds(pointsLabelX, pointsLabelY, 300, 100);
//		getContentPane().add(agilityLabel);
//		agilityLabel.setVisible(true);
//
//		pointsLabelY += 25;
//		attackLabel.setBounds(pointsLabelX, pointsLabelY, 300, 100);
//		getContentPane().add(attackLabel);
//		attackLabel.setVisible(true);
//
//		pointsLabelY += 25;
//		defenseLabel.setBounds(pointsLabelX, pointsLabelY, 300, 100);
//		getContentPane().add(defenseLabel);
//		defenseLabel.setVisible(true);
//
//		pointsLabelY += 25;
//		healthLabel.setBounds(pointsLabelX, pointsLabelY, 300, 100);
//		getContentPane().add(healthLabel);
//		healthLabel.setVisible(true);
//
//		pointsLabelY += 25;
//		totalPointsLabel.setBounds(pointsLabelX , pointsLabelY, 300, 100);
//		getContentPane().add(totalPointsLabel);
//		totalPointsLabel.setVisible(true);
//
//		pointsLeftLabel.setBounds(pointsLabelX + 100, pointsLabelY, 300, 100);
//		getContentPane().add(pointsLeftLabel);
//		pointsLeftLabel.setVisible(true);		
	}

	/**
	 * Method to give text areas to enter in points for each area of the actor.
	 */
	private void initializePointsTextArea(){
		//Bounds variables
		int pointsTextFieldX = (int)(screenSize.getWidth() * .92);		//X coordinate
		int pointsTextFieldY = (int)(screenSize.getHeight() * .37);		//Y coordinate
		int textFieldWidth = 30;										//Textfield width
		int textFeildHeight = 15;										//Textfield height

//		agilityPoints.setBounds(pointsTextFieldX, pointsTextFieldY, textFieldWidth, textFeildHeight);
//		getContentPane().add(agilityPoints);
//		agilityPoints.setVisible(true);
//
//		pointsTextFieldY += 25;
//		attackPoints.setBounds(pointsTextFieldX, pointsTextFieldY, textFieldWidth, textFeildHeight);
//		getContentPane().add(attackPoints);
//		attackPoints.setVisible(true);
//
//		pointsTextFieldY += 25;
//		defensePoints.setBounds(pointsTextFieldX, pointsTextFieldY, textFieldWidth, textFeildHeight);
//		getContentPane().add(defensePoints);
//		defensePoints.setVisible(true);
//
//		pointsTextFieldY += 25;
//		healthPoints.setBounds(pointsTextFieldX, pointsTextFieldY, textFieldWidth, textFeildHeight);
//		getContentPane().add(healthPoints);
//		healthPoints.setVisible(true);
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
	private void initializeButtons(){

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
				if (wasOpened) {
					saveFile(currFileName, textArea.getText(), currUnitType);
					JOptionPane.showMessageDialog(null,"File Saved");
				} else {
					createSaveFilePopup();
					disableWindow();
				}
				
			}
		});
		saveFileButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		int saveButtonX = (int)((innerWidth *.80) + 25); //20 pixels past the end of the textArea
		saveFileButton.setBounds(saveButtonX, 15, 160, 35);
		getContentPane().add(saveFileButton);
		saveFileButton.setBackground(buttonColor);

		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableWindow();

				Pattern p = Pattern.compile("\\s*", Pattern.CASE_INSENSITIVE);

				if (!p.matcher(textArea.getText()).matches()) { //if there's actually non-whitespace in the file
					saveBeforeMenu = new JFrame();
					saveBeforeMenu.setLayout(null);
					saveBeforeMenu.setSize(260,150);
					saveBeforeMenu.setLocation(new Point((int)((innerWidth/2) - 130),(int)((innerHeight/2) - 5)));
					setUpCloseOperation(saveBeforeMenu);

					JLabel text = new JLabel();
					text.setText("<html><center>Would you like to save<br>your changes?</center></html>");
					text.setBounds(52, 20, 220, 40);

					saveBeforeMenu.add(text);

					JButton save = new JButton("Yes");
					save.setBounds(41,80,75,25);
					save.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (wasOpened) {
								saveFile(currFileName, textArea.getText(), currUnitType);
								reEnable();
								clearFields(); //clear all values.
								mainReference.setVisible(true);
								setVisible(false);
							} else {
								createSaveFileThenMenuPopup();
							}
							saveBeforeMenu.dispose();
						}
					}); //end of addListener

					saveBeforeMenu.add(save);

					JButton no = new JButton("No");
					no.setBounds(141,80,75,25);
					no.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							reEnable();
							clearFields(); //clear all values.
							mainReference.setVisible(true);
							setVisible(false);
							saveBeforeMenu.dispose();
						}
					}); //end of cancelListener

					saveBeforeMenu.add(no);

					saveBeforeMenu.setAlwaysOnTop(true);
					saveBeforeMenu.setVisible(true);

				} else {
					reEnable();
					mainReference.reEnable();
					clearFields();
					mainReference.setVisible(true);
					setVisible(false);				
				}
			}

		}); //end of mainListener

		mainMenuButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		int menuButtonX = (int) innerWidth - 180; //20 pixels between window's right edge and the edge of the button
		mainMenuButton.setBounds(menuButtonX, 15, 160, 35);
		getContentPane().add(mainMenuButton);
		mainMenuButton.setBackground(buttonColor);
	}

	private void clearFields() {
		agilityPoints.setText(0 + "");
		attackPoints.setText(0 + "");
		defensePoints.setText(0 + "");
		healthPoints.setText(0 + "");
		textArea.setText("");
	}

	public void setMainReference(MainWindow main) {
		mainReference = main;
	}

	public void openMain() {
		clearFields();
		mainReference.setVisible(true);
		setVisible(false);
	}

	private void disableWindow() {
		for (Component c : getContentPane().getComponents()) {
			c.setEnabled(false);
		}
		textArea.setEnabled(false);
		getContentPane().setEnabled(false);
	}

	public void reEnable() {
		for (Component c : getContentPane().getComponents()) {
			c.setEnabled(true);
		}
		textArea.setEnabled(true);
		getContentPane().setEnabled(true);
	}

	public void setUpCloseOperation(JFrame f) {
		WindowListener w = new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				reEnable();
			}
		};
		f.addWindowListener(w);
	}


	public EditorFilePopup getPopup() {
		return (EditorFilePopup) mainReference.getFrameList().get(0);
	}
}