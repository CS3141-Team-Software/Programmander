package launcherWindow;

import gameEngine.GameEngine;
import gameWindow.GameWindow;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * This class displays the launcher window. 
 * The purpose of this class is to launch the game based on the 
 * information that the user has put into the forms. 
 * 
 * @author Nighthunter
 *
 */
public class LauncherWindow extends JFrame {
	
	JFrame mainFrameWindow;
	JButton runAI = new JButton("Run AI");
	JButton btnBackToMain = new JButton("Back To Main Window");
	JComboBox<String> comboBoxMaps = new JComboBox<String>();
	JComboBox<String> comboBoxPlayer1AI = new JComboBox<String>();
	JComboBox<String> comboBoxPlayer2AI = new JComboBox<String>();
	JComboBox<String> comboBoxDifficulty = new JComboBox<String>();
	JLabel mapsLabel = new JLabel("Maps");
	JLabel player1AILabel = new JLabel("Player 1 AI");
	JLabel player2AILabel = new JLabel("Player 2 AI");
	JLabel difficultyLabel = new JLabel("Difficulty");
	
	public LauncherWindow(JFrame main) {
		
		this.mainFrameWindow = main;
		initializeWindowElements();
		
		//Set up the content pane definition.
		setMinimumSize(new Dimension(1720, 880));
		setMaximumSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		getContentPane().setMaximumSize(new Dimension(1720, 880));
		getContentPane().setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);		//Used to set to full width and height of the current screen
		
	}
	
	private void initializeWindowElements() {
		
		runAI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameWindow gameFrame = new GameWindow(new GameEngine());
				setVisible(false);
				gameFrame.setVisible(true);
			}
		});
		runAI.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		runAI.setBounds(777, 561, 518, 87);
		getContentPane().add(runAI);
		
		//This is the maps combo box. 
		//Maps are read in through files that are used to populate the comboBox for the user to choose from.
		comboBoxMaps.setBounds(631, 261, 787, 45);
		getContentPane().add(comboBoxMaps);
		
		comboBoxPlayer1AI.setBounds(631, 380, 787, 50);
		getContentPane().add(comboBoxPlayer1AI);
		
		comboBoxDifficulty.setBounds(631, 318, 787, 50);
		getContentPane().add(comboBoxDifficulty);
		
		mapsLabel.setFont(new Font("Arial Black", Font.BOLD, 50));
		mapsLabel.setBounds(452, 261, 156, 50);
		getContentPane().add(mapsLabel);
		
		player1AILabel.setFont(new Font("Arial Black", Font.BOLD, 50));
		player1AILabel.setBounds(300, 385, 308, 50);
		getContentPane().add(player1AILabel);
		
		difficultyLabel.setFont(new Font("Arial Black", Font.BOLD, 50));
		difficultyLabel.setBounds(331, 323, 277, 50);
		getContentPane().add(difficultyLabel);
		
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mainFrameWindow.setVisible(true);
			}
		});
		btnBackToMain.setBounds(10, 11, 141, 33);
		getContentPane().add(btnBackToMain);
		
		player2AILabel.setFont(new Font("Dialog", Font.BOLD, 50));
		player2AILabel.setBounds(300, 447, 312, 57);
		getContentPane().add(player2AILabel);
		
		comboBoxPlayer2AI.setBounds(631, 439, 787, 53);
		getContentPane().add(comboBoxPlayer2AI);
		
	}

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
				if(fileList[i].getName().endsWith(".jar")) {
					actNum++;
				}
			}
			
			String[] ret = new String[actNum]; 
			int j = 0;
			for(int i = 0; i < fileList.length; i++){
				if(fileList[i].getName().endsWith(".jar")) {
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
