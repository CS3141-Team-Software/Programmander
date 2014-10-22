package display;

import gameEngine.GameEngine;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JButton;

public class GameWindow extends JFrame {
	
	//SCREEN DIMENSIONS
	private Dimension screen;
	private double dynamicWindowHeight = 0;
	private double dynamicWindowWidth = 0;
	
	//JFrame Window elements. 
	JLabel gameMapTitle = new JLabel("Game");
	
	//Drawing game window elements
	GamePanel game = new GamePanel();
	
	//JFrame window labels.
	private String mapName;
	private String AI1;
	private String AI2; 
	private String difficulty; 
	private boolean is2Player;
	
	/**
	 * Constructor for the new gamewindow.
	 * @param mapName
	 * @param firstAIName
	 * @param difficulty
	 * @param is2Player
	 */
	public GameWindow(String mapName, String firstAIName, String difficulty, boolean is2Player) {
		initializeGameInformation(mapName, firstAIName, difficulty, is2Player);
		initializeScreenSize();
		initializeGUIElements();
	}
	
	//get the gamepanel reference.
	public GamePanel getGamePanel(){
		return game;
	}

	/**
	 * Initialize the game information
	 * @param is2Player2 
	 * @param difficulty2 
	 * @param firstAIName 
	 * @param mapName2 
	 */
	private void initializeGameInformation(String mapName, String firstAIName, String difficulty, boolean is2Player){
		this.mapName = mapName;
		this.AI1 = firstAIName;
		this.is2Player = is2Player;
		if(is2Player){
			this.AI2 = difficulty;
		} else{
			this.difficulty = difficulty;
		}
	}
	
	private void initializeScreenSize(){
		//Screen dimension things.---------------------------------------
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		screen = new Dimension(this.getWidth(), this.getHeight());
		this.setDynamicWindowHeight(screen.getHeight());
		this.setDynamicWindowWidth(screen.getWidth());
		getContentPane().setSize(screen);
		getContentPane().setPreferredSize(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game WINININDOWOWOW");
		//---------------------------------------------------------------
	}
	
	private void initializeGUIElements(){
		
		gameMapTitle.setBounds(12, 9, 69, 15);
		gameMapTitle.setText(this.mapName);
		getContentPane().add(gameMapTitle);
		initiliazeGamePanel();
	}
	
	/**
	 * This initializes the actual panel where the buffered image is drawn.
	 */
	private void initiliazeGamePanel(){
		game.setWindowDimensions(screen);
		game.setPreferredSize(new Dimension(950, 950));
		game.setMinimumSize(new Dimension(950, 950));
		game.setMaximumSize(new Dimension(950, 950));
		game.setSize(new Dimension(950, 950));
		game.setBounds(12, 24, 950, 950);
		getContentPane().add(game);
		game.setBackground(Color.RED);
	}
	
	
	//Getters and setters ------------------------------------------------------------
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

