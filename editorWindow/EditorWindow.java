package editorWindow;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

public class EditorWindow extends JFrame {
	public EditorWindow() {
		setMaximumSize(new Dimension(1720, 880));
		getContentPane().setSize(new Dimension(1720, 880));
		getContentPane().setPreferredSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		getContentPane().setMaximumSize(new Dimension(1720, 880));
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 500, 500);
		getContentPane().add(panel);
	}
}
