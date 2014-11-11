package editorWindow;

import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorWindow extends JFrame {

	public EditorWindow() {

		Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		this.setPreferredSize(d);
		
		//These variables are our available screensize, including borders
		int innerWidth = (int)d.getWidth() - getInsets().left - getInsets().right;
		int innerHeight = (int)d.getHeight() - getInsets().top - getInsets().bottom - 130; //-130 for reasons

		getContentPane().setLayout(null);

		RSyntaxTextArea textArea = new RSyntaxTextArea(10, 10);
		textArea.setAlignmentY(Component.TOP_ALIGNMENT);
		textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);

		RTextScrollPane sp = new RTextScrollPane(textArea);
		
		int textWidth = (int)(innerWidth *.66);
		sp.setBounds(10, 50, textWidth, innerHeight);
		sp.setVisible(true);
		textArea.setVisible(true);
		getContentPane().add(sp);
		
		setTitle("Text Editor Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	private String openFile(String fileName) {
		
		return null;
	}
	
	private String saveClass (String fileName, String className) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error writing to a class file");
			System.exit(-1);
		}
		return null;
	}
	
	private String saveSpawner(String fileName, String spawnerName) {
		
		return null;
	}
	
	

}