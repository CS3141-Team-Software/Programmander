package tutorial;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.BorderLayout;

public class TutorialWindow extends JFrame {
	public TutorialWindow() {
		setMinimumSize(new Dimension(1720, 880));
		setTitle("Tutorial, have fun learning!");
		getContentPane().setBounds(new Rectangle(0, 0, 1720, 880));
		getContentPane().setPreferredSize(new Dimension(1720, 880));
		getContentPane().setSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(500, 500));
		panel.setPreferredSize(new Dimension(500, 500));
		panel.setBounds(new Rectangle(0, 0, 500, 500));
		panel.setMaximumSize(new Dimension(1000, 1000));
		panel.setMinimumSize(new Dimension(500, 500));
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);
	}
}
