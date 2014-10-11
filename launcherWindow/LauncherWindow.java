package launcherWindow;

import gameEngine.GameEngine;
import gameWindow.GameWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
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
	
	private JFrame mainFrameWindow;
	private JButton backToMainButton = new JButton("Back To Main Window");
	private JButton runAI = new JButton("Run AI");
	private JComboBox<String> comboBoxMaps = new JComboBox<String>();
	private JComboBox<String> comboBoxPlayer1AI = new JComboBox<String>();
	private JComboBox<String> comboBoxPlayer2AI = new JComboBox<String>();
	private JComboBox<String> comboBoxDifficulty = new JComboBox<String>();
	private JLabel player1AILabel = new JLabel("Player 1 AI");
	private JLabel player2AILabel = new JLabel("Player 2 AI");
	private JLabel difficultyLabel = new JLabel("Difficulty");
	private JLabel mapsLabel = new JLabel("Maps");
	private Boolean isThereAPlayer2 = false;
	private String player2NameOrDifficulty;
	
	//Frame dimensions
	private Integer frameWidth = 1920;
	private Integer frameHeight = 1080;
	
	public LauncherWindow(JFrame main) throws IOException {
		
		this.mainFrameWindow = main;
		initializeButtons();
		initializeLabels();
		initializeComboBoxes();
		
		//Set up the content pane definition.
		setMinimumSize(new Dimension(frameWidth, frameHeight));
		setMaximumSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setMinimumSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setMaximumSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);		//Used to set to full width and height of the current screen
		getContentPane().add(new LauncherPanel());
	}
	
	/**
	 * method to initialize the comboboxes on the frame
	 */
	private void initializeComboBoxes(){
		//This is the maps combo box. 
		//Maps are read in through files that are used to populate the comboBox for the user to choose from.
		comboBoxMaps.setBounds(631, 261, 787, 45);
		for(String s : getMapList()){
			comboBoxMaps.addItem(s);
		}
		getContentPane().add(comboBoxMaps);
		
		//Player 1 AI combobox
		comboBoxPlayer1AI.setBounds(631, 380, 787, 50);
		for(String s : getActorList()){
			comboBoxPlayer1AI.addItem(s);
		}
		getContentPane().add(comboBoxPlayer1AI);
		
		//Player 2 AI combobox
		comboBoxPlayer2AI.setBounds(631, 439, 787, 53);
		for(String s : getActorList()){
			comboBoxPlayer2AI.addItem(s);
		}
		getContentPane().add(comboBoxPlayer2AI);
		
		// Initialize the difficulty combo box
		comboBoxDifficulty.setBounds(631, 318, 787, 53);
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
					gameFrame = new GameWindow(new GameEngine((String)comboBoxMaps.getSelectedItem(), (String)comboBoxPlayer1AI.getSelectedItem(), player2NameOrDifficulty, isThereAPlayer2));
					setVisible(false);
					gameFrame.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		runAI.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		runAI.setBounds(777, 561, 518, 87);
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

	/**
	 * Method to initialize the labels in the frame
	 * Status: DONE
	 */
	private void initializeLabels(){
		Color labelColors = new Color(255, 255, 255);
		//Map Label formatting
		mapsLabel.setFont(new Font("Dialog", Font.BOLD, 50));
		mapsLabel.setBounds(475, 261, 156, 50);
		mapsLabel.setBackground(labelColors);
		mapsLabel.setForeground(new Color(255,255,255));
		getContentPane().add(mapsLabel);
		//Player 1 AI Label formatting
		player1AILabel.setFont(new Font("Dialog", Font.BOLD, 50));
		player1AILabel.setBounds(345, 382, 312, 57);
		player1AILabel.setBackground(labelColors);
		player1AILabel.setForeground(new Color(255,255,255));
		getContentPane().add(player1AILabel);
		//Player 2 AI Label formatting
		player2AILabel.setFont(new Font("Dialog", Font.BOLD, 50));
		player2AILabel.setBounds(345, 444, 312, 57);
		player2AILabel.setBackground(labelColors);
		player2AILabel.setForeground(new Color(255,255,255));
		getContentPane().add(player2AILabel);
		//Difficulty Label formatting
		difficultyLabel.setFont(new Font("Dialog", Font.BOLD, 50));
		difficultyLabel.setBounds(385, 320, 277, 57);
		difficultyLabel.setBackground(labelColors);
		difficultyLabel.setForeground(new Color(255,255,255));
		getContentPane().add(difficultyLabel);
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
		String dir = System.getProperty("user.dir") + "/src/maps";
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
		String dir = System.getProperty("user.dir") + "/src/actors";
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
}
