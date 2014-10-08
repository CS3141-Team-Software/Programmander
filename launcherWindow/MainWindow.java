package launcherWindow;

import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Canvas;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

public class MainWindow extends JFrame {
	
	ArrayList<JFrame> frames; 
	
	//Buttons
	JButton playButton = new JButton("PLAY!");
	JButton editButton = new JButton("EDIT");
	JButton tutorialButton = new JButton("TUTORIAL");
	
	//Titles and labels
	JLabel titleProgrammander = new JLabel("Programmander");
	
	//Spacing struts
	Component verticalStrut = Box.createVerticalStrut(50);
	Component verticalStrut_1 = Box.createVerticalStrut(20);
	Component verticalStrut_2 = Box.createVerticalStrut(20);
	Component verticalStrut_3 = Box.createVerticalStrut(20);
	
	public MainWindow(ArrayList<JFrame> f){
		initializeGUIElements();
		getContentPane().setSize(new Dimension(300, 80));
		getContentPane().setPreferredSize(new Dimension(300, 80));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frames = f;
		setMinimumSize(new Dimension(1720, 880));
		setMaximumSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		getContentPane().setMaximumSize(new Dimension(1720, 880));
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("This is the main window, insert clever title here.");
	}

	private void initializeGUIElements() {
		
		//Initialization of the play button dimensions and placement on the contentpane.
		playButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		playButton.setSize(new Dimension(300, 80));
		playButton.setPreferredSize(new Dimension(300, 80));
		playButton.setMinimumSize(new Dimension(104, 100));
		playButton.setMaximumSize(new Dimension(400, 100));
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				JFrame frame = frames.get(0);
				frames.get(0).setVisible(true);
				setVisible(false);
			}
		});
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//Space between top and programmander text
		verticalStrut_3.setMaximumSize(new Dimension(32767, 100));
		getContentPane().add(verticalStrut_3);
		
		// TITLE "Programmander"
		titleProgrammander.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleProgrammander.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 72));
		getContentPane().add(titleProgrammander);
		
		//Strut between TITLE and play button.
		verticalStrut.setMaximumSize(new Dimension(32767, 200));
		getContentPane().add(verticalStrut);
		getContentPane().add(playButton);
		
		//Initialization of the edit button dimensions and placement.
		editButton.setSize(new Dimension(104, 25));
		editButton.setPreferredSize(new Dimension(104, 25));
		editButton.setMinimumSize(new Dimension(104, 100));
		editButton.setMaximumSize(new Dimension(400, 100));
		editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(1).setVisible(true);
				setVisible(false);
			}
		});
		
		//Strut to space the buttons 
		getContentPane().add(verticalStrut_1);
		editButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		getContentPane().add(editButton);
		
		//Tutorial button initialization
		tutorialButton.setMaximumSize(new Dimension(400, 100));
		tutorialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		tutorialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(2).setVisible(true);
				setVisible(false);
			}
		});
		
		//Space buttons out.
		getContentPane().add(verticalStrut_2);
		tutorialButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		getContentPane().add(tutorialButton);
	}
}
