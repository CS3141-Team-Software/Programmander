package launcher;

import gameEngine.GameEngine;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * This class displays the launcher window.
 * The purpose of this class is to launch the game based on the
 * information that the user has put into the forms.
 *
 * @author Kyle Derosha
 *
 */
public class LauncherWindow extends JFrame {

	//SCREEN DIMENSIONS
	private Dimension screen;

	//JFrame elements
	private JFrame mainFrameWindow;
	private JButton backToMainButton = new JButton("Back To Main Window");
	private JButton runAI = new JButton("Run AI");

	//Toolkit variables
	private Toolkit kit = this.getToolkit();
	Cursor cursor;
	private double dynamicWindowHeight = 0;
	private double dynamicWindowWidth = 0;

	//Comboboxes
	private JComboBox<String> comboBoxMaps = new JComboBox<String>();
	private JComboBox<String> comboBoxPlayer1AI = new JComboBox<String>();
	private JComboBox<String> comboBoxPlayer2AI = new JComboBox<String>();
	private JComboBox<String> comboBoxDifficulty = new JComboBox<String>();
	//Combo box bounds variables
	private int comboBoxStaticXCoord = 0;
	private int comboBoxHeight = 50;
	private int comboBoxWidth = 0;
	private int startBoxYs = 0;

	//Labels
	private JLabel player1AILabel = new JLabel("Player 1 AI");
	private JLabel player2AILabel = new JLabel("Player 2 AI");
	private JLabel difficultyLabel = new JLabel("Difficulty");
	private JLabel mapsLabel = new JLabel("Maps");
	private Boolean isThereAPlayer2 = false;
	private String player2NameOrDifficulty;

	//label bounds varialbes
	private int mapLabelWidth = 150;
	private int ai1LabelWidth = 308;
	private int ai2LabelWidth = 308;
	private int diffLabelWidth = 263;

	//Flags so it loads correctly every time

	//Main constructor
	public LauncherWindow() throws IOException {

		initializeScreenSize();
		startBoxYs = (int)((dynamicWindowHeight / 2) - 250);
		comboBoxWidth = (int) (dynamicWindowWidth / 2 - 200);
		comboBoxStaticXCoord = (int)((dynamicWindowWidth / 2) - (comboBoxWidth/2));
		//Initialize window elements
		initializeComboBoxes();
		initializeLabels();
		initializeButtons();
		getContentPane().add(new LauncherPanel());

		BufferedImage cursorFile = null;

		try {
			cursorFile = ImageIO.read(getClass().getResource("/assets/art/cursor.png").openStream());
		} catch (Exception e){
			e.printStackTrace();
			System.err.println("Error: Could not fetch file names");
			System.exit(1);
		}

		if (cursorFile == null) {
			System.err.println("Error: Could not find custom cursor");
			System.exit(1);
		}

		//custom cursor image that comes from cursor.png in art assets
		cursor = kit.createCustomCursor(cursorFile, new Point(0,0), "cursor");

	}

	/**
	 * This method initialize all the screen dimension
	 */
	private void initializeScreenSize() {
		//Screen dimension things.---------------------------------------
		this.setCursor(cursor);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDynamicWindowHeight(screen.getHeight());
		this.setDynamicWindowWidth(screen.getWidth());
		getContentPane().setSize(screen);
		getContentPane().setPreferredSize(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Launcher");
		//---------------------------------------------------------------
	}

	/**
	 * method to initialize the comboboxes on the frame
	 */
	private void initializeComboBoxes(){

		//This is the maps combo box.
		//Maps are read in through files that are used to populate the comboBox for the user to choose from.

		comboBoxMaps.setBounds(comboBoxStaticXCoord, (startBoxYs), comboBoxWidth, comboBoxHeight);
		for(String s : getMapList()){
			comboBoxMaps.addItem(s);
		}
		getContentPane().add(comboBoxMaps);

		// Initialize the difficulty combo box
		startBoxYs += 75;
		comboBoxDifficulty.setBounds(comboBoxStaticXCoord, (startBoxYs ), comboBoxWidth, comboBoxHeight);
		comboBoxDifficulty.addItem("");
		comboBoxDifficulty.addItem("Easy");
		comboBoxDifficulty.addItem("Medium");
		comboBoxDifficulty.addItem("Hard");

		comboBoxDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = (String)comboBoxDifficulty.getSelectedItem();
				if(!(s.equalsIgnoreCase(""))){
					comboBoxPlayer2AI.setVisible(false);
					player2AILabel.setVisible(false);
					isThereAPlayer2 = false;
				} else if((s.equalsIgnoreCase(""))){
					comboBoxPlayer2AI.setVisible(true);
					player2AILabel.setVisible(true);
					isThereAPlayer2 = true;
				}
			}
		});
		getContentPane().add(comboBoxDifficulty);

		//Player 1 AI combobox
		startBoxYs += 75;
		comboBoxPlayer1AI.setBounds(comboBoxStaticXCoord, (startBoxYs), comboBoxWidth, comboBoxHeight);
		for(String s : getAIList()){
			comboBoxPlayer1AI.addItem(s);
		}
		getContentPane().add(comboBoxPlayer1AI);

		//Player 2 AI combobox
		startBoxYs += 75;
		comboBoxPlayer2AI.setBounds(comboBoxStaticXCoord, (startBoxYs), comboBoxWidth, comboBoxHeight);
		for(String s : getAIList()){
			comboBoxPlayer2AI.addItem(s);
		}
		getContentPane().add(comboBoxPlayer2AI);
	}

	/**
	 * method to initialize the buttons on the frame
	 */
	private void initializeButtons() {

		Color runButtonColor = new Color(233, 233, 233);
		//Run AI Button
		runAI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isThereAPlayer2){
					player2NameOrDifficulty = (String)comboBoxPlayer2AI.getSelectedItem();
				}else {
					player2NameOrDifficulty = (String)comboBoxDifficulty.getSelectedItem();
				}
				GameEngine engine = new GameEngine((String)comboBoxMaps.getSelectedItem(), (String)comboBoxPlayer1AI.getSelectedItem(), player2NameOrDifficulty, isThereAPlayer2);
				setVisible(false);
				engine.run();
			}
		});
		runAI.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		runAI.setBounds((int) (dynamicWindowWidth/2 - (250/2)), startBoxYs + 75, 250, 90);
		getContentPane().add(runAI);
		runAI.setBackground(runButtonColor);
	}

	/**47
	 * Method to initialize tsetDynamicWindowHeighthe labels in the frame
	 * Status: DONE
	 */
	private void initializeLabels(){
		Color labelColors = new Color(255, 255, 255);
		int startY = (int)((dynamicWindowHeight / 2) - 250);
		//Map Label formatting
		mapsLabel.setFont(new Font("Dialog", Font.BOLD, 50));
		mapsLabel.setBounds(comboBoxStaticXCoord - mapLabelWidth - 15, startY, mapLabelWidth, 50);
		mapsLabel.setBackground(labelColors);
		mapsLabel.setForeground(new Color(255,255,255));
		getContentPane().add(mapsLabel);

		//Difficulty Label formatting
		startY += 75;
		difficultyLabel.setFont(new Font("Dialog", Font.BOLD, 50));
		difficultyLabel.setBounds(comboBoxStaticXCoord - diffLabelWidth - 15, startY, diffLabelWidth, 55);
		difficultyLabel.setBackground(labelColors);
		difficultyLabel.setForeground(new Color(255,255,255));
		getContentPane().add(difficultyLabel);

		//Player 1 AI Label formatting
		startY += 75;
		player1AILabel.setFont(new Font("Dialog", Font.BOLD, 50));
		player1AILabel.setBounds(comboBoxStaticXCoord - ai1LabelWidth - 15, startY, ai1LabelWidth, 57);
		player1AILabel.setBackground(labelColors);
		player1AILabel.setForeground(new Color(255,255,255));
		getContentPane().add(player1AILabel);

		//Player 2 AI Label formatting
		startY += 75;
		player2AILabel.setFont(new Font("Dialog", Font.BOLD, 50));
		player2AILabel.setBounds(comboBoxStaticXCoord - ai2LabelWidth - 15, startY, ai2LabelWidth, 57);
		player2AILabel.setBackground(labelColors);
		player2AILabel.setForeground(new Color(255,255,255));
		getContentPane().add(player2AILabel);

	}


	/*
	 * Get Map List
	 * returns a list of map names in string format
	 * will return null in error
	 */
	public ArrayList<String> getMapList(){


		try {
			CodeSource src = LauncherWindow.class.getProtectionDomain().getCodeSource();
			if (src != null) {
				URL jar = src.getLocation();
				ZipInputStream zip = new ZipInputStream(jar.openStream());

				ArrayList<String> mapNames = new ArrayList<String>();

				while(true) {
					//Loop through all directories and look for stuff in assets/maps
					ZipEntry e = zip.getNextEntry();
					if (e == null) {
						break;
					}
					String name = e.getName();
					if (name.startsWith("assets/maps")) {
						if (!name.equals("assets/maps/")) {
							mapNames.add(name);
						}
					}
				} //end of while loop

				return mapNames;

			} else {
				System.err.println("getMapList: error with fetching maps. Bad location");
				System.exit(1);
				return null;
			}
		} catch (Exception e){
			System.err.println("Error: Could not fetch file names.");
			System.exit(1);
		}

		return null;

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
		}catch(IOException e) {
			System.err.println("Could not read aiNames");
			e.printStackTrace();
			System.exit(1);
		}

		return AINames;
	}

	public double getDynamicWindowHeight() {
		return dynamicWindowHeight;
	}

	public void setDynamicWindowHeight(double dynamicWindowHeight) {
		this.dynamicWindowHeight = dynamicWindowHeight;
	}

	public double getDynamicWindowWidth() {
		return dynamicWindowWidth;
	}

	public void setDynamicWindowWidth(double dynamicWindowWidth) {
		this.dynamicWindowWidth = dynamicWindowWidth;
	}
}
