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
	
	private Renderer renderer;

	private String mapName;
	private String player1AIName;
	private String secondPlayerOrDifficulty;
	private boolean is2Player;
	private BufferedImage currFrame;


	public GameEngine(String mapName, String firstAIName, String difficulty, boolean is2Player) {
		this.mapName = mapName;
		this.player1AIName = firstAIName;
		this.secondPlayerOrDifficulty = difficulty;
		this.is2Player = is2Player;

		//Make our map using data passed in by LauncherWindow
		map = new Graph(mapName);
		nodes = map.getNodesSing();

		actors = map.getActors();
		renderer = new Renderer(actors, map, nodes);

		//Initialize 1st player's AI by calling Mark's magic code.
		//TODO: Implement Mark's magic code

		if (is2Player) {
			//Initialize 2nd player's AI
		} else {
			//Initialize our AI based on which difficulty was selected
		}
		
	}

	

	//Run the game!
	public void run() {
				
		while(!gameOver) {


			//Go through actors and update them
			//for (Actor a : actors) {
			//	a.update();
			//}
			gameOver = false;
			//Generate a new buffered image
			
			
			//Paint nextFrame to the screen

		
					

		}

	}

	//TODO: IS this needed?
	//Sets reference to our drawing area so we can draw in it.
	public void setGamePanel(JPanel w) {
		window = w;
	}

	public String getMapName(){
		return this.mapName;
	}
	public void setMapName(String name){
		this.mapName = name;
	}
	public String getPlayer1AIName(){
		return this.mapName;
	}
	public void setPlayer1AIName(String name){
		this.player1AIName = name;
	}
	public String getPlayer2AIName(){
		if(is2Player){
			return this.secondPlayerOrDifficulty;
		} else {
			return "";
		}
	}
	public void setPlayer2AIName(String name){
		this.secondPlayerOrDifficulty = name;
	}
	public String getDifficulty(){
		if(!is2Player){
			return this.secondPlayerOrDifficulty;
		} else {
			return "";
		}
	}
	public void setDifficulty(String name){
		this.secondPlayerOrDifficulty = name;
	}


}

