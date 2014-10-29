package gameEngine;
import java.util.ArrayList;

import api.Actor;
import api.GameState;

/**
 * 
 * @author Turel
 *
 *This could as well be a function!
 *
 *This class gets the "gamestate" from GameEngine, simulates all requested moves
 *and checks if there are any conflicts. After resolving conflicts the turn is being returned to 
 *the gameEngine.
 */

public class GameLogic{
	private ArrayList<Actor> actors;

	/*
	 * Gamestate is passed to an Actor
	 * In the creation, the gamestate is adapted to the Actor
	 * The actor calls gamestate for information depending his
	 * surroundings
	 * 
	 */

	GameLogic(ArrayList<Actor> pActors, Graph g){
		actors = pActors;


		//Go through actors and update them

		//TODO after gamestate is created
		for (Actor a : actors) {
			Graph map = g;
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
						//Reteliation!
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
	}


}
