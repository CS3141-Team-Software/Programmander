package gameEngine;


import java.util.ArrayList;
import java.util.Dictionary;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JPanel;

import display.Display;
import display.GamePanel;

import api.Actor;
import api.GameState;
import api.Spawner;


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
		//Spawner playerSpawner = null;
		System.out.println("Loading ais/" + firstAIName + ".jar");
		File f = new File("ais/" + firstAIName + ".jar");
	    URLClassLoader urlCl;
		try {
			urlCl = new URLClassLoader(new URL[] { f.toURI().toURL()},Spawner.class.getClassLoader());
		    //Class<?> testAI = urlCl.loadClass("thing");
		    //Class<? extends Spawner> myAIClass = testAI.asSubclass(Spawner.class);
		    //playerSpawner = myAIClass.newInstance();

		} catch (Exception e) {
			System.err.println("Loading their code");
			e.printStackTrace();
			System.exit(1);
		}

		//playerSpawner.printSomething();

		if (is2Player) {
			//Initialize 2nd player's AI
		} else {
			//Initialize our AI based on which difficulty was selected
		}
	}

	//Run the game!
	public void run() {

		//while(!gameOver) {

			//Do spawner stuff
			//Call gamelogic, does moves

			//Render moves
			//Go through actors and update them
			//for (Actor a : actors) {
			//	//generate gamestate
			//	a.update(gameState);
			//}

			//Generate a new buffered image
			display.render(new GameState(this.map,this.actors));
			//Check endgame, set gameOver
		//}

	}

}

