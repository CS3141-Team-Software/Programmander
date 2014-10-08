package gameWindow;

import gameEngine.GameEngine;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;

public class GameWindow extends JFrame {
	
	public GameWindow(GameEngine gameEngine){
		
		System.out.println();
		setSize(new Dimension(1720, 880));
		getContentPane().setPreferredSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		getContentPane().setMaximumSize(new Dimension(1720, 880));
		getContentPane().setLayout(null);
		
		JLabel lblGame = new JLabel("Game");
		lblGame.setBounds(12, 9, 69, 15);
		getContentPane().add(lblGame);
		
		JButton btnDummyButton = new JButton("Dummy button");
		btnDummyButton.setBounds(1529, 24, 175, 25);
		getContentPane().add(btnDummyButton);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 800));
		panel.setMinimumSize(new Dimension(800, 800));
		panel.setMaximumSize(new Dimension(800, 800));
		panel.setSize(new Dimension(800, 800));
		panel.setBounds(12, 24, 800, 800);
		getContentPane().add(panel);
		panel.setBackground(Color.RED);
		
	}
	public void Stuff() {
		
	}
}
