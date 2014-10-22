package gameEngine;


import java.util.ArrayList;
import java.util.Dictionary;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;

import javax.swing.JPanel;

import display.Display;
import display.GamePanel;

import api.Actor;
import api.GameState;


/**
 * @author Frederick Nolte
 *
 */
public class GameEngine {

	private boolean gameOver = false;
	private GraphImplementation map;
	private ArrayList<Actor> actors;
	
	private Display display;


	public GameEngine(String mapName, String firstAIName, String difficulty, boolean is2Player) {

		map = new GraphImplementation(mapName);
		display = new Display(mapName,firstAIName,difficulty,is2Player,map);

		actors = map.getActors();	

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
		
		//while(!gameOver) {
			
			//Go through actors and update them
			//for (Actor a : actors) {
			//	//generate gamestate
			//	a.update(gameState);
			//}
			//Generate a new buffered image
			display.render(new GameState(this.map,this.actors));
			
			
			//Paint nextFrame to the screen
			//window.repaint();
		//}

	}

}

