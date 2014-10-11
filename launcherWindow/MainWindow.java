package launcherWindow;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;

public class MainWindow extends JFrame {
	
	ArrayList<JFrame> frames; 
	
	//Buttons
	JButton playButton = new JButton("PLAY!");
	JButton editButton = new JButton("EDIT");
	JButton tutorialButton = new JButton("TUTORIAL");
	private Integer buttonWidth = 350;
	private Integer buttonHeight = 80;
	
	public MainWindow(ArrayList<JFrame> f) throws IOException{
		this.frames = f;
		
		getContentPane().setSize(new Dimension(1920, 1080));
		getContentPane().setPreferredSize(new Dimension(1920, 1080));
		setMinimumSize(new Dimension(1920, 1080));
		setMaximumSize(new Dimension(1920, 1080));
		getContentPane().setMinimumSize(new Dimension(1920, 1080));
		getContentPane().setMaximumSize(new Dimension(1920, 1080));
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Window");
		
		//Initialization of the play button dimensions and placement on the contentpane.
		playButton.setSize(new Dimension(buttonWidth, buttonHeight));
		playButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		playButton.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		playButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		playButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		playButton.setLocation(900, 300);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(0).setVisible(true);
				setVisible(false);
			}
		});
		add(playButton);
		
		//Initialization of the edit button dimensions and placement.
		editButton.setSize(new Dimension(buttonWidth, buttonHeight));
		editButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		editButton.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		editButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		editButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		editButton.setLocation(900, 400);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(1).setVisible(true);
				setVisible(false);
			}
		});
		add(editButton);
		
		//Tutorial button initialization
		tutorialButton.setSize(new Dimension(buttonWidth, buttonHeight));
		tutorialButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		tutorialButton.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		tutorialButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		tutorialButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		tutorialButton.setLocation(900, 500);
		tutorialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(2).setVisible(true);
				setVisible(false);
			}
		});
		add(tutorialButton);
		getContentPane().add(new MainWindowPanel(f));
	}


}
