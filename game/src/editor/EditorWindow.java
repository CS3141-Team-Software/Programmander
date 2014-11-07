package editor;

import java.awt.Dimension;
import java.awt.Toolkit;

//Imports!
import javax.swing.JFrame;

/**
 * Class to display the editor window so that the players can edit their AI code and such.
 * @author kpderosh
 *
 */
public class EditorWindow extends JFrame {

	private Dimension screen;
	
	//Screen dimension variables
	private double dynamicWindowHeight;
	private double dynamicWindowWidth;
	
	/**
	 * default constructor
	 */
	public EditorWindow(){
		//TODO: need to add arguments needed to run this class.
		initializeWindow();			//Method to initialize the editor window.
		initializeGUIElements();
	}
	
	/**
	 * method to initialize the editor window screen size and different JFrame variables. 
	 */
	private void initializeWindow(){
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDynamicWindowHeight(screen.getHeight());
		this.setDynamicWindowWidth(screen.getWidth());
		getContentPane().setSize(screen);
		getContentPane().setPreferredSize(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Launcher");
	}
	
	/**
	 * Method To initialize labels, buttons, jpanels, ect...
	 */
	private void initializeGUIElements(){
		initializeButtons();		//Method to initialize all the button components
		initializeLabels();			//Method to initialize all the label elements. 
	}
	
	/**
	 * initialize the button components here.
	 */
	private void initializeButtons(){
		
	}
	
	/**
	 * initialize label components here. 
	 */
	private void initializeLabels(){
		
	}
	
	//Setter and getter methods below this point-----------------------------------------------
	public double getDynamicWindowHeight() {
		return dynamicWindowHeight;
	}

	public void setDynamicWindowHeight(double dynamicWindowHeight) {
		this.dynamicWindowHeight = dynamicWindowHeight;
	}

	public double getDynamicWindowWidth() {
		return dynamicWindowWidth;
	}

	public void setDynamicWindowWidth(double dynamicWindowWidth) {
		this.dynamicWindowWidth = dynamicWindowWidth;
	}
}
