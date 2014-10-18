package launcherWindow;

import gameEngine.GameEngine;
import gameWindow.GameWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

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
	Cursor cursor = kit.createCustomCursor(ImageIO.read(new File(System.getProperty("user.dir") + "/assets/art/cursor.png")), new Point(0,0), "cursor");		//custom cursor image that comes from cursor.png in art assets
	
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
	private int ai1LabelWidth = 280;
	private int ai2LabelWidth = 280;
	private int diffLabelWidth = 240;
	
	//Main constructor
	public LauncherWindow(JFrame main) throws IOException {
		initializeScreenSize();
		startBoxYs = (int)((dynamicWindowHeight / 2) - 250);
		this.mainFrameWindow = main;
		comboBoxWidth = (int) (dynamicWindowWidth / 2 - 200);
		comboBoxStaticXCoord = (int)((dynamicWindowWidth / 2) - (comboBoxWidth/2));
		//Initialize window elements
		initializeLabels();
		initializeComboBoxes();
		initializeButtons();
		getContentPane().add(new LauncherPanel());
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
		screen = new Dimension(this.getWidth(), this.getHeight());
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
		for(String s : getActorList()){
			comboBoxPlayer1AI.addItem(s);
		}
		getContentPane().add(comboBoxPlayer1AI);
		
		//Player 2 AI combobox
		startBoxYs += 75;
		comboBoxPlayer2AI.setBounds(comboBoxStaticXCoord, (startBoxYs), comboBoxWidth, comboBoxHeight);
		for(String s : getActorList()){
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
				GameWindow gameFrame;
				try {
					GameEngine engine = new GameEngine((String)comboBoxMaps.getSelectedItem(), (String)comboBoxPlayer1AI.getSelectedItem(), player2NameOrDifficulty, isThereAPlayer2);
					gameFrame = new GameWindow(engine);
					setVisible(false);
					gameFrame.setVisible(true);
					engine.run();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		runAI.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		runAI.setBounds((int) (dynamicWindowWidth/2 - (250/2)), startBoxYs + 75, 250, 90);
		getContentPane().add(runAI);
		runAI.setBackground(runButtonColor);
		backToMainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mainFrameWindow.setVisible(true);
			}
		});
		backToMainButton.setBounds(10, 11, 141, 33);
		getContentPane().add(backToMainButton); 
	}

	/**47
	 * Method to initialize the labels in the frame
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
	
	/**
	 * Set the reference to the main frame so you can go back to click on edit or tutorial
	 * @param main
	 */
	public void setMainFrame(JFrame main){
		this.mainFrameWindow = main;
	}
	
	/*
	 * Get Map List
	 * returns a list of map names in string format
	 * will return null in error
	 */
	public String[] getMapList(){
		String dir = System.getProperty("user.dir") + "/maps";
		File mapParent = new File(dir);
		
		if(mapParent.isDirectory()){
			File[] fileList = mapParent.listFiles();
			
			int mapNum = 0;
			for(int i = 0; i < fileList.length; i++){
				if(fileList[i].getName().endsWith(".txt")) {
					mapNum++;
				}
			}
			
			String[] ret = new String[mapNum]; 
			int j = 0;
			for(int i = 0; i < fileList.length; i++){
				if(fileList[i].getName().endsWith(".txt")) {
					ret[j] = fileList[i].getName();
					j++;
				}
			}
			 return ret;
		}
		else{
			System.out.println("getMapList: error with fetching maps. Unacceptable directory path. Returning null.");
			return null;
		}
	}

	/*
	 * get Actor List
	 * returns an array of actor jars in string.
	 * will return null in error.
	 */
	public String[] getActorList(){
		String dir = System.getProperty("user.dir") + "/actors";
		File actParent = new File(dir);
		
		if(actParent.isDirectory()){
			File[] fileList = actParent.listFiles();
			
			int actNum = 0;
			for(int i = 0; i < fileList.length; i++){
				if(fileList[i].getName().endsWith(".txt")) {
					actNum++;
				}
			}
			
			String[] ret = new String[actNum]; 
			int j = 0;
			for(int i = 0; i < fileList.length; i++){
				if(fileList[i].getName().endsWith(".txt")) {
					ret[j] = fileList[i].getName();
					j++;
				}
			}
			 return ret;
		}
		else{
			System.out.println("getActorList: error with fetching actors. Unacceptable directory path. Returning null.");
			return null;
		}
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
