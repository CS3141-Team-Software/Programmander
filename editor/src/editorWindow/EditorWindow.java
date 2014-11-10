package editorWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class EditorWindow extends JFrame {

   public EditorWindow() {
	   
	  Dimension d = new Dimension(1920, 1080);
	  this.setSize(d);
	  this.setMinimumSize(d);
	  this.setMaximumSize(d);
	  this.setPreferredSize(d);
	  
      JPanel cp = new JPanel(new BorderLayout());
     
      RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
  
      textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
      textArea.setCodeFoldingEnabled(true);
      RTextScrollPane sp = new RTextScrollPane(textArea);
      cp.add(sp);

      setContentPane(cp);
      setTitle("Text Editor Demo");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);

   }


}