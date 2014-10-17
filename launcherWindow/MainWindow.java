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
import java.awt.Toolkit;
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
	private JButton playButton = new JButton("PLAY!");
	private JButton editButton = new JButton("EDIT");
	private JButton tutorialButton = new JButton("TUTORIAL");
	private Integer buttonWidth = 350;
	private Integer buttonHeight = 80;
	private Toolkit kit;
	private double dynamicWindowHeight = 0;
	private double dynamicWindowWidth = 0;
	
	//Constructor creating the frame
	public MainWindow(ArrayList<JFrame> f) throws IOException{
		
		this.frames = f;
		//Screen dimension things.
		kit = this.getToolkit();
		Dimension dim = kit.getScreenSize();
		this.setDynamicWindowHeight(dim.getHeight());
		this.setDynamicWindowWidth(dim.getWidth());
		
		getContentPane().setSize(dim);
		getContentPane().setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		getContentPane().setMinimumSize(dim);
		getContentPane().setMaximumSize(dim);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Window");
		initializeButtons(dim);
		getContentPane().add(new MainWindowPanel(f));
	}

	public void initializeButtons(Dimension dim){
		Dimension buttonDim = new Dimension(buttonWidth, buttonHeight);
		double localWidth = dim.getWidth();
		double localHeight = dim.getHeight();
		
		//Initialization of the play button dimensions and placement on the contentpane.
		playButton.setSize(buttonDim);
		playButton.setPreferredSize(buttonDim);
		playButton.setMinimumSize(buttonDim);
		playButton.setMaximumSize(buttonDim);
		playButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		playButton.setLocation( (int)((localWidth/2) - (buttonWidth/2)), (int)((localHeight - 420)));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(0).setVisible(true);
				setVisible(false);
			}
		});
		add(playButton);
		
		//Initialization of the edit button dimensions and placement.
		editButton.setSize(buttonDim);
		editButton.setPreferredSize(buttonDim);
		editButton.setMinimumSize(buttonDim);
		editButton.setMaximumSize(buttonDim);
		editButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		editButton.setLocation((int)((localWidth/2) - (buttonWidth/2)), (int)((localHeight - 315)));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(1).setVisible(true);
				setVisible(false);
			}
		});
		add(editButton);
		
		//Tutorial button initialization
		tutorialButton.setSize(buttonDim);
		tutorialButton.setPreferredSize(buttonDim);
		tutorialButton.setMinimumSize(buttonDim);
		tutorialButton.setMaximumSize(buttonDim);
		tutorialButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		tutorialButton.setLocation((int)((localWidth/2) - (buttonWidth/2)), (int)((localHeight - 210)));
		tutorialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(2).setVisible(true);
				setVisible(false);
			}
		});
		add(tutorialButton);
	}

	
	//Getters and setters.
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
