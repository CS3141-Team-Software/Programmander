package launcher;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;

public class MainWindow extends JFrame {
	
	ArrayList<JFrame> frames; 
	
	//SCREEN DIMENSIONS
	private Dimension screen;
	
	//Buttons
	private Dimension buttonDim = new Dimension(350, 80);
	private JButton playButton = new JButton("PLAY!");
	private JButton editButton = new JButton("EDIT");
	private JButton tutorialButton = new JButton("TUTORIAL");
	
	//JLabels
	private Dimension titleDim = new Dimension(600, 50);
	private JLabel title = new JLabel("PROGRAMMANDER");
	private Color titleColor = new Color(255, 255, 255);
	private Point titleLocation = new Point(100, 200);
	
	private Toolkit kit = this.getToolkit();
	private double dynamicWindowHeight = 0;
	private double dynamicWindowWidth = 0;
	//Cursor cursor = kit.createCustomCursor(ImageIO.read(new File(System.getProperty("user.dir") + "/assets/art/cursor.png")), new Point(0,0), "cursor");
	
	//Constructor creating the frame
	public MainWindow(ArrayList<JFrame> f) throws IOException{
		this.frames = f;
		initializeScreenSize();
		initializeButtons(screen);
		initializeTitle();
		getContentPane().add(new MainWindowPanel(f));
	}

	/**
	 * This method initialize all the screen dimension
	 */
	private void initializeScreenSize() {
		//Screen dimension things.---------------------------------------
		//this.setCursor(cursor);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDynamicWindowHeight(screen.getHeight());
		this.setDynamicWindowWidth(screen.getWidth());
		getContentPane().setSize(screen);
		getContentPane().setPreferredSize(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Window");
		//---------------------------------------------------------------
	}

	private void initializeTitle() {
		titleLocation = new Point((int)(dynamicWindowWidth/2-titleDim.getWidth()/2), (int)(dynamicWindowHeight/2-titleDim.getHeight()/2 - 300));
		title.setFont(new Font("Dialog", Font.BOLD, 50));
		title.setBackground(titleColor);
		title.setForeground(titleColor);
		title.setLocation(titleLocation);
		title.setBounds((int)titleLocation.getX(), (int)titleLocation.getY(), (int)titleDim.getWidth(), (int)titleDim.getHeight());
		add(title);
	}

	public void initializeButtons(Dimension dim){
		int buttonX = (int)(dim.getWidth()/2 - (buttonDim.getWidth()/2));
		int buttonY = (int) (dynamicWindowHeight/2);
		
		//Initialization of the play button dimensions and placement on the contentpane.
		playButton.setSize(buttonDim);
		playButton.setPreferredSize(buttonDim);
		playButton.setMinimumSize(buttonDim);
		playButton.setMaximumSize(buttonDim);
		playButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		playButton.setLocation(buttonX, buttonY);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(0).setVisible(true);
				setVisible(false);
			}
		});
		add(playButton);
		
		//Initialization of the edit button dimensions and placement.
		buttonY += 105;
		editButton.setSize(buttonDim);
		editButton.setPreferredSize(buttonDim);
		editButton.setMinimumSize(buttonDim);
		editButton.setMaximumSize(buttonDim);
		editButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		editButton.setLocation(buttonX, buttonY);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(0).setVisible(true);
				setVisible(false);
			}
		});
		add(editButton);
				
		//Tutorial button initialization
		buttonY += 105;
		tutorialButton.setSize(buttonDim);
		tutorialButton.setPreferredSize(buttonDim);
		tutorialButton.setMinimumSize(buttonDim);
		tutorialButton.setMaximumSize(buttonDim);
		tutorialButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		tutorialButton.setLocation(buttonX, buttonY);
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
