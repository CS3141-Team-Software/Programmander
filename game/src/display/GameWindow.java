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
	private String mapName,AI1,AI2,difficulty; 
	private boolean is2Player;
	

	public GameWindow(String mapName, String firstAIName, String difficulty,	boolean is2Player) {
		this.mapName = mapName;
		this.AI1 = firstAIName;
		this.is2Player = is2Player;
		if(is2Player){
			this.AI2 = difficulty;
		}else{
			this.difficulty = difficulty;
		}
		setSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setMinimumSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setMaximumSize(new Dimension(frameWidth, frameHeight));
		getContentPane().setLayout(null);
		initializeGUIElements();
	}
	public GamePanel getGamePanel(){
		return game;
	}

	private void initializeGUIElements(){
		
		gameMapTitle.setBounds(12, 9, 69, 15);
		gameMapTitle.setText(this.mapName);
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
		
		actualPlayer1AIName.setText(this.AI1);
		actualPlayer1AIName.setBounds(1066, 56, 116, 15);
		getContentPane().add(actualPlayer1AIName);
		if(this.is2Player){
			actualPlayer2AIName.setText(this.AI2);
			actualPlayer2AIName.setBounds(1066, 71, 116, 15);
			getContentPane().add(actualPlayer2AIName);
		}else{
			actualDifficulty.setText(this.difficulty);
			actualDifficulty.setBounds(1066, 85, 116, 15);
			getContentPane().add(actualDifficulty);
		}
		setExtendedState(MAXIMIZED_BOTH);	
	}
}

