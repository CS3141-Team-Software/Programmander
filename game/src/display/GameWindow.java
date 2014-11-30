package display;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

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

	//Game Panel Variables
	private int gameMapNumberOfRows;
	private int gameMapNumberOfCols;
	private int mapHeight;
	private int mapWidth;
	private Rectangle gamePanelBounds;
	private Dimension gamePanelDim;
	private Point gamePanelLocation;

	//	JButton closeButton = new JButton("X");
	/**
	 * Constructor for the new gamewindow.
	 * @param mapName
	 * @param firstAIName
	 * @param difficulty
	 * @param is2Player
	 * @param j
	 * @param i
	 */
	public GameWindow(String mapName, String firstAIName, String difficulty, int mapHeightInRows, int mapWidthInCols) {
		initializeGameInformation(mapName, firstAIName, difficulty, mapHeightInRows, mapWidthInCols);
		initializeScreenSize();
		initializeGUIElements();
		this.setBackground(new Color(31, 41, 48));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//This won't work...
		setUpCloseOperation(this);
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
	private void initializeGameInformation(String mapName, String firstAIName, String difficulty, int mapRows, int mapCols){
		this.mapName = mapName;
		this.AI1 = firstAIName;
		this.AI2 = difficulty;
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
		this.gamePanelLocation = new Point((int) (((screen.getWidth())/2) - (mapWidth/2)), (int) ((screen.getHeight()/2) - (mapHeight/2) - 40));
		this.gamePanelBounds = new Rectangle(gamePanelLocation, gamePanelDim);
	}

	/**
	 * Initializes the window size
	 */
	private void initializeScreenSize(){
		//Screen dimension things.---------------------------------------
		//this.setExtendedState(MAXIMIZED_BOTH);
		Dimension d = new Dimension(1920, 1080);
		this.setSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		this.setPreferredSize(d);

		//this.setUndecorated(true);
		this.setDynamicWindowHeight(screen.getHeight());
		this.setDynamicWindowWidth(screen.getWidth());
		getContentPane().setSize(screen);
		getContentPane().setPreferredSize(screen);
		this.setVisible(true);
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
		getContentPane().add(game);
		game.setPreferredSize(new Dimension(1920, 1080));
		Dimension d = new Dimension(1920, 1080);
		game.setSize(d);
		game.setMinimumSize(d);
		game.setMaximumSize(d);
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

	public GamePanel changePanel() {
		getContentPane().remove(game);
		initializeGamePanel();
		game.revalidate();
		return game;
	}

	public void setUpCloseOperation(JFrame f) {
		WindowListener w = new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Fuvvs");
				System.exit(0);
			}
		};
		f.addWindowListener(w);
	}

	public void createGameOverWindow(boolean redTeamWon) {
		GameOverWindow gameOver = new GameOverWindow(redTeamWon);

	}
}

