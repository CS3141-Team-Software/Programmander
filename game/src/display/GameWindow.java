package display;


import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

public class GameWindow extends JFrame {
	
	//SCREEN DIMENSIONS
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private double dynamicWindowHeight = 0;
	private double dynamicWindowWidth = 0;
	
	//JFrame Window elements. 
	JLabel gameMapTitle = new JLabel("Game");
	
	//Drawing game window elements
	GamePanel game;
	
	//JFrame window labels.
	private String mapName;
	private String AI1;
	private String AI2; 
	private String difficulty; 
	private boolean is2Player;
	
	//Game Panel Variables
	private int gameMapNumberOfRows;
	private int gameMapNumberOfCols;
	private int mapHeight;
	private int mapWidth;
	private Rectangle gamePanelBounds;
	private Dimension gamePanelDim;
	private Point gamePanelLocation;
	
	/**
	 * Constructor for the new gamewindow.
	 * @param mapName
	 * @param firstAIName
	 * @param difficulty
	 * @param is2Player
	 * @param j 
	 * @param i 
	 */
	public GameWindow(String mapName, String firstAIName, String difficulty, boolean is2Player, int mapHeightInRows, int mapWidthInCols) {
		initializeGameInformation(mapName, firstAIName, difficulty, is2Player, mapHeightInRows, mapWidthInCols);
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
	private void initializeGameInformation(String mapName, String firstAIName, String difficulty, boolean is2Player, int mapRows, int mapCols){
		this.mapName = mapName;
		this.AI1 = firstAIName;
		this.is2Player = is2Player;
		if(is2Player){
			this.AI2 = difficulty;
		} else{
			this.difficulty = difficulty;
		}
		this.gameMapNumberOfRows = mapRows;
		this.gameMapNumberOfCols = mapCols;
		this.mapHeight = gameMapNumberOfRows * 50;
		this.mapWidth = gameMapNumberOfCols * 50;
		intitializeGamePanelBounds();
	}
	
	/**
	 * initialize the location and bounds of the game panel.
	 */
	private void intitializeGamePanelBounds() {
		this.gamePanelDim = new Dimension(mapWidth, mapHeight);
		this.gamePanelLocation = new Point((int) (((screen.getWidth())/2) - (mapWidth/2)), (int) ((screen.getHeight()/2) - (mapHeight/2)));
		this.gamePanelBounds = new Rectangle(gamePanelLocation, gamePanelDim);
	}

	/**
	 * Initializes the window size
	 */
	private void initializeScreenSize(){
		//Screen dimension things.---------------------------------------
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDynamicWindowHeight(screen.getHeight());
		this.setDynamicWindowWidth(screen.getWidth());
		getContentPane().setSize(screen);
		getContentPane().setPreferredSize(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Programmander");
		//---------------------------------------------------------------
	}
	
	private void initializeGUIElements(){
		initializeGamePanel();
	}
	
	/**
	 * This initializes the actual panel where the buffered image is drawn.
	 */
	private void initializeGamePanel(){
		game = new GamePanel(gamePanelDim, gamePanelLocation, gamePanelBounds);
		game.setLocation(gamePanelLocation);
		getContentPane().add(game);
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

