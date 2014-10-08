package gameEngine;

import java.util.ArrayList;
import java.util.Dictionary;
import java.awt.Image;
import java.awt.image.*;

import javax.swing.JPanel;


/**
 * @author Frederick Nolte
 *
 */
public class GameEngine {

	private boolean gameOver = false;
	private Graph map;
	private GraphNode[] nodes;
	private JPanel window;
	private ArrayList<Actor> actors;
	//Used to store image data
	private Dictionary<String, Image> cache;
	//Used to paint our image to the frame.
	private BufferedImage currFrame;
	
	public GameEngine(String mapName, String firstAIName, String difficulty, boolean is2Player) {

		//Make our map using data passed in by LauncherWindow
		map = new Graph(mapName);
		nodes = map.getNodesSing();
		//TODO: Need this to actually happen
		actors = map.getActors();

		//Initialize 1st player's AI by calling Mark's magic code.
		//TODO: Implement Mark's magic code

		if (is2Player) {
			//Initialize 2nd playertring, image's AI
		} else {
			//Initialize our AI based on which difficulty was selected
		}
		
		this.run();

	}

	//Run the game!
	private void run() {
		while(!gameOver) {
			
			//Go through actors and update them
			for (Actor a : actors) {
				a.update();
			}
			
			//Paint to buffered image
			
		}
		
	}

	//Sets reference to our drawing area so we can draw in it.
	public void setGamePanel(JPanel w) {
		window = w;
	}
}

