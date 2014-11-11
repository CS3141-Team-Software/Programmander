package editorWindow;

import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorWindow extends JFrame {
	
	private File currFile;
	private String currUnitType;
	private String currFileName;
	
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

	private String openFile(String fileName, String classType) {
		currFileName = fileName + ".java";
		currFile = new File(fileName);
		return null;
	}

	private void saveClass (String textAreaText) {
		try {
			//Will append to end of our half-created file.
			BufferedWriter writer = new BufferedWriter(new FileWriter(currFile, true));
			
			writer.write("\n");
			//Write the FULL text of the update() method to the file
			writer.write(textAreaText);			
			
			writer.write("\n}");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error writing to a class file");
			System.exit(-1);
		}
	}

	private String saveSpawner(String fileName, String spawnerName) {

		return null;
	}



}