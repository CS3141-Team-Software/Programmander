package launcher;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import editorWindow.EditorWindow;


public class MainLauncher {
	
	private static ArrayList<JFrame> frames = new ArrayList<JFrame>();

	public static void main(String[] args) throws Exception {
		initializeWindows();
	}
	
	/** 
	 * This method is used to set up all the frames that we will need.
	 * Passes them to the mainwindow so that we can choose which frames
	 * are going to be visible based on what button is pressed. 
	 * @throws IOException 
	 */
	private static void initializeWindows() throws IOException{
		
		EditorWindow editor = new EditorWindow();
		editor.pack();
		
		frames.add(editor);
		
		MainWindow main = new MainWindow(frames);
		main.pack();
		
		//This might be important? 
		//launcher.setMainFrame(main);
		

	}
	
	/** 
	 * Method to get all of the windows that are initialized in the program.
	 * @return frames - ArrayList<JFrame>'s
	 */
	public ArrayList<JFrame> getFrames(){
		return MainLauncher.frames;
	}
}

