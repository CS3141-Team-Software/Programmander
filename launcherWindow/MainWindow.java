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
	
	public MainWindow(ArrayList<JFrame> f){
		getContentPane().setSize(new Dimension(300, 80));
		getContentPane().setPreferredSize(new Dimension(300, 80));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frames = f;
		setMinimumSize(new Dimension(1720, 880));
		setMaximumSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		getContentPane().setMaximumSize(new Dimension(1720, 880));
		setExtendedState(MAXIMIZED_BOTH);
		
		JButton btnLauncher = new JButton("PLAY!");
		btnLauncher.setSize(new Dimension(300, 80));
		btnLauncher.setPreferredSize(new Dimension(300, 80));
		btnLauncher.setMinimumSize(new Dimension(104, 100));
		btnLauncher.setMaximumSize(new Dimension(104, 100));
		btnLauncher.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLauncher.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnLauncher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				JFrame frame = frames.get(0);
				frames.get(0).setVisible(true);
				setVisible(false);
			}
		});
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel lblProgrammander = new JLabel("Programmander");
		lblProgrammander.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblProgrammander.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 72));
		getContentPane().add(lblProgrammander);
		
		Component verticalStrut = Box.createVerticalStrut(50);
		getContentPane().add(verticalStrut);
		getContentPane().add(btnLauncher);
		
		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.setSize(new Dimension(104, 25));
		btnNewButton.setPreferredSize(new Dimension(104, 25));
		btnNewButton.setMinimumSize(new Dimension(104, 25));
		btnNewButton.setMaximumSize(new Dimension(104, 25));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(1).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("TUTORIAL");
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(2).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		getContentPane().add(btnNewButton_1);
		setTitle("This is the main window, insert clever title here.");
	}
}
