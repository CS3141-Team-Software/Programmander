package launcherWindow;

import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Canvas;
import java.util.ArrayList;

public class MainWindow extends JFrame {
	
	public MainWindow(ArrayList<JFrame> frames){
		
		setMinimumSize(new Dimension(1720, 880));
		setMaximumSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		getContentPane().setMaximumSize(new Dimension(1720, 880));
		getContentPane().setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);
		
		JButton btnLauncher = new JButton("PLAY!");
		btnLauncher.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnLauncher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(0).setVisible(true);
				setVisible(false);
			}
		});
		btnLauncher.setBounds(10, 273, 538, 120);
		getContentPane().add(btnLauncher);
		
		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(1).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnNewButton.setBounds(10, 142, 538, 120);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("TUTORIAL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionCommand) {
				frames.get(2).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnNewButton_1.setBounds(10, 11, 538, 120);
		getContentPane().add(btnNewButton_1);
		setTitle("This is the main window, insert clever title here.");
	}
	
}
