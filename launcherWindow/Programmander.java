package launcherWindow;

import java.util.ArrayList;

import javax.swing.JFrame;

import tutorial.TutorialWindow;
import editorWindow.EditorWindow;


public class Programmander {
	
	private static ArrayList<JFrame> frames = new ArrayList<JFrame>();

	public static void main(String[] args) throws Exception {
		initializeWindows();
	}
	
	/** 
	 * This method is used to set up all the frames that we will need.
	 * Passes them to the mainwindow so that we can choose which frames
	 * are going to be visable based on what button is pressed. 
	 */
	private static void initializeWindows(){
		
		LauncherWindow launcher = new LauncherWindow(null);
		launcher.pack();
		launcher.setVisible(false);
		
		EditorWindow editor = new EditorWindow();
		editor.pack();
		editor.setVisible(false);
		
		TutorialWindow tutorial = new TutorialWindow();
		tutorial.pack();
		tutorial.setVisible(false);
		
		frames.add(launcher);
		frames.add(editor);
		frames.add(tutorial);
		
		MainWindow main = new MainWindow(frames);
		main.pack();
		main.setVisible(true);
		
		launcher.setMainFrame(main);

	}
	
	/** 
	 * Method to get all of the windows that are initialized in the program.
	 * @return frames - ArrayList<JFrame>'s
	 */
	public ArrayList<JFrame> getFrames(){
		return this.frames;
	}
}

