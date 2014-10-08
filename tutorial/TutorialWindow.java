package tutorial;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.BorderLayout;

public class TutorialWindow extends JFrame {
	
	JPanel mainPanel = new JPanel();
	
	public TutorialWindow() {
		
		initializeGUIElements();
		
		setMinimumSize(new Dimension(1720, 880));
		setTitle("Tutorial, have fun learning!");
		getContentPane().setBounds(new Rectangle(0, 0, 1720, 880));
		getContentPane().setPreferredSize(new Dimension(1720, 880));
		getContentPane().setSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
	}

	private void initializeGUIElements() {
		
		mainPanel.setSize(new Dimension(500, 500));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBounds(new Rectangle(0, 0, 500, 500));
		mainPanel.setMaximumSize(new Dimension(1000, 1000));
		mainPanel.setMinimumSize(new Dimension(500, 500));
		getContentPane().add(mainPanel, BorderLayout.WEST);
		mainPanel.setLayout(null);
		
	}
}
