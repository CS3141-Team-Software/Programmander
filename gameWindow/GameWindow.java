package gameWindow;

import gameEngine.GameEngine;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JButton;

public class GameWindow extends JFrame {
	
	GameEngine gameEngine;
	JLabel gameMapTitle = new JLabel("Game");
	JButton btnDummyButton = new JButton("Dummy button");
	GamePanel game = new GamePanel();
	
	JLabel gameInformationTitleLabel = new JLabel("Game Information");
	JLabel player1AINameTitleLabel = new JLabel("Player 1 AI:");
	JLabel player2AITitleLabel = new JLabel("Player 2 AI:");
	JLabel difficultyLabel = new JLabel("Difficulty:");
	private JLabel actualPlayer1AIName = new JLabel("New label");
	private JLabel actualPlayer2AIName = new JLabel("New label");
	private JLabel actualDifficulty = new JLabel("New label");
	private Integer frameWidth = 1920;
	private Integer frameHeight = 1080;
	
	/**
	 * Method to initialize the game frame
	 * @param gameEngine
	 * @throws IOException
	 */
	public GameWindow(GameEngine gameEngine) throws IOException{
		this.gameEngine = gameEngine;
		
		setSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setMinimumSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setMaximumSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setLayout(null);
		initializeGUIElements();
	}
	
	private void initializeGUIElements(){
		String mapName = gameEngine.getMapName();
		
		gameMapTitle.setBounds(12, 9, 69, 15);
		gameMapTitle.setText(gameEngine.getMapName());
		getContentPane().add(gameMapTitle);
		
		btnDummyButton.setBounds(1529, 24, 175, 25);
		getContentPane().add(btnDummyButton);
		
		game.setPreferredSize(new Dimension(950, 950));
		game.setMinimumSize(new Dimension(950, 950));
		game.setMaximumSize(new Dimension(950, 950));
		game.setSize(new Dimension(950, 950));
		game.setBounds(12, 24, 950, 950);
		getContentPane().add(game);
		game.setBackground(Color.RED);
		
		gameInformationTitleLabel.setBounds(980, 29, 137, 15);
		getContentPane().add(gameInformationTitleLabel);
		
		player1AINameTitleLabel.setBounds(980, 56, 86, 15);
		getContentPane().add(player1AINameTitleLabel);
		
		player2AITitleLabel.setBounds(980, 71, 86, 15);
		getContentPane().add(player2AITitleLabel);
		
		difficultyLabel.setBounds(980, 85, 86, 15);
		getContentPane().add(difficultyLabel);
		
		actualPlayer1AIName.setText(gameEngine.getPlayer1AIName());
		actualPlayer1AIName.setBounds(1066, 56, 116, 15);
		getContentPane().add(actualPlayer1AIName);
		
		actualPlayer2AIName.setText(gameEngine.getPlayer2AIName());
		actualPlayer2AIName.setBounds(1066, 71, 116, 15);
		getContentPane().add(actualPlayer2AIName);
		
		actualDifficulty.setText(gameEngine.getDifficulty());
		actualDifficulty.setBounds(1066, 85, 116, 15);
		getContentPane().add(actualDifficulty);
		
		setExtendedState(MAXIMIZED_BOTH);	
	}
}
