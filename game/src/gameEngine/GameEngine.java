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
import api.Spawner;


/**
 * @author Frederick Nolte
 *
 */
public class GameEngine {

	private boolean gameOver = false;
	private Graph map;
	private GameLogic logic;
	private GameState current;

	private ArrayList<Actor> actors;
	private ArrayList<Spawner> spawners;

	private Display display;


	public GameEngine(String mapName, String firstAIName, String difficulty, boolean is2Player) {

		map = new Graph(mapName);
		display = new Display(mapName, firstAIName, difficulty, is2Player, map);

		actors = map.getActors();	
		//TODO: Need to place the spawners as actors

		//Initialize 1st player's AI by calling Mark's magic code.

		if (is2Player) {
			//Initialize 2nd player's AI
		} else {
			//Initialize our AI based on which difficulty was selected
		}
	}

	//Run the game!
	public void run() {

		//while(!gameOver) {

		current = updateGameState(new GameState(this.map,this.actors));

		//Generates and paints a bufferedImage of the current frame
		display.render(current);


		//Paint nextFrame to the screen
		//window.repaint();
		//}

	}

	public GameState updateGameState(GameState state) {
		//Go through actors and update them

		//TODO after gamestate is created
		for (Actor a : actors) {
			int aMove = a.update(new GameState(a.getX(), a.getY(), a.getVision()));
			int action = aMove/10;
			int dir = aMove%10;
			//If there is a movement
			if (action == 1){
				a.setDefending(-1);
				GraphNode actual = null;
				switch(dir){
				case 0: actual = map.getNode(a.getX(), a.getY()).getNNode(); break;
				case 1: actual = map.getNode(a.getX(), a.getY()).getNENode(); break;
				case 2: actual = map.getNode(a.getX(), a.getY()).getENode(); break;
				case 3: actual = map.getNode(a.getX(), a.getY()).getSENode(); break;
				case 4: actual = map.getNode(a.getX(), a.getY()).getSNode(); break;
				case 5: actual = map.getNode(a.getX(), a.getY()).getSWNode(); break;
				case 6: actual = map.getNode(a.getX(), a.getY()).getWNode(); break;
				case 7: actual = map.getNode(a.getX(), a.getY()).getNWNode(); break;
				}
				//MISSING CODE MISSING CODE MISSING CODE
				//Check if Obstacle is missing
				if(actual != null && actual.getActor() == null){
					map.getNode(a.getX(), a.getY()).setActor(null);
					map.getNode(a.getX(), a.getY()).setIsChanged(true);
					a.setX(actual.getX());
					a.setY(actual.getY());
					actual.setActor(a);	
					actual.setIsChanged(true);
				}
			}
			if (action == 2){
				a.setDefending(dir);
			}

			if (action == 3){
				a.setDefending(-1);
				GraphNode actual = null;
				switch(dir){
				case 0: actual = map.getNode(a.getX(), a.getY()).getNNode(); break;
				case 1: actual = map.getNode(a.getX(), a.getY()).getNENode(); break;
				case 2: actual = map.getNode(a.getX(), a.getY()).getENode(); break;
				case 3: actual = map.getNode(a.getX(), a.getY()).getSENode(); break;
				case 4: actual = map.getNode(a.getX(), a.getY()).getSNode(); break;
				case 5: actual = map.getNode(a.getX(), a.getY()).getSWNode(); break;
				case 6: actual = map.getNode(a.getX(), a.getY()).getWNode(); break;
				case 7: actual = map.getNode(a.getX(), a.getY()).getNWNode(); break;
				}

				Actor enemy = actual.getActor();
				if(enemy != null){
					if(enemy.getTeam() == a.getTeam())
					{
						int att = a.attackThrow();
						int def = enemy.defenseThrow();
						//Simple Fighting System
						if(att > def){
							enemy.setHealth(enemy.getHealth() - (att - def));
							if(enemy.getHealth() < 0){
								actual.setActor(null);
								actual.setIsChanged(true);
							}
						}
						//Retaliation!
						else{
							a.setHealth(a.getHealth() - (def - att)/2);
							if(a.getHealth() < 0){
								map.getNode(a.getX(), a.getY()).setActor(null);
								map.getNode(a.getX(), a.getY()).setIsChanged(true);
							}
						}
					}
				}
			}
		}
		return new GameState(map, actors );
	}
}