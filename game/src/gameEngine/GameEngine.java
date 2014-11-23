package gameEngine;
//xinput set-button-map 10 1 0 3 4 5 6 7
import java.awt.Point;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import api.Actor;
import api.GameState;
import api.Spawner;
import display.Display;


public class GameEngine {

	private boolean gameOver = false;
	private Graph map;
	private GameState current;

	private ArrayList<Actor> actors;
	private ArrayList<Spawner> spawners;
	private int blueNumUnits, redNumUnits, mapNumUnits;

	private Display display;
	private long turnTime = 1000;
	private long currTime;


	public GameEngine(String mapName, String firstAIName, String ai2, boolean tutorial) {

		map = new Graph(mapName);
		display = new Display(mapName, firstAIName, ai2, map);
		spawners = new ArrayList<Spawner>();
		blueNumUnits = 0;
		redNumUnits = 0;
		mapNumUnits = map.getNumUnits();

		//Initialize player AI by calling Mark's magic code.
		//---------------Just don't look
		System.out.println("Map: " + mapName + "\np0: " + firstAIName + "\np1: " + ai2 + "\nisTut: " + tutorial);
		Spawner playerSpawner = null;
		File f0 = new File("ais/ai.jar");
		URLClassLoader urlCl0;
		try {
			urlCl0 = new URLClassLoader(new URL[] { f0.toURI().toURL() },	Spawner.class.getClassLoader());
			System.out.println("p0: "	+ firstAIName);
			Class<?> testAI0 = urlCl0.loadClass("playerCode."	+ firstAIName);
			Class<? extends Spawner> myAIClass0 = testAI0.asSubclass(Spawner.class);
			playerSpawner = myAIClass0.newInstance();
			playerSpawner.setTeam(0);
			spawners.add(playerSpawner);
		} catch (ClassNotFoundException e1) {
			System.err.println("ERR: Class not found while loading p0 code");
			e1.printStackTrace();
			System.exit(1);
		} catch (MalformedURLException e2) {
			System.err.println("ERR: MalformedULR while loading p0 code");
			e2.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.err.println("ERR: Loading p0 code");
			e.printStackTrace();
			System.exit(1);
		}

		Spawner secondSpawner = null;
		if(tutorial){//Load p1 as tutorial
			try {
				secondSpawner = (Spawner) Class.forName("tutorial." + ai2).newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				System.out.println("Loading tutorial failed, get fucked");
				e.printStackTrace();
				System.exit(42);
			}
		}else{
			//Initialize 2nd player's AI
			File f1 = new File("ais/ai.jar");
			URLClassLoader urlCl1;
			try {
				urlCl1 = new URLClassLoader(new URL[] { f1.toURI().toURL() },	Spawner.class.getClassLoader());
				System.out.println("p1: "	+ ai2);
				Class<?> testAI1 = urlCl1.loadClass("playerCode."	+ ai2);
				Class<? extends Spawner> myAIClass1 = testAI1.asSubclass(Spawner.class);
				secondSpawner = myAIClass1.newInstance();
			} catch (ClassNotFoundException e1) {
				System.err.println("ERR: Class not found while loading p1 code");
				e1.printStackTrace();
				System.exit(1);
			} catch (MalformedURLException e2) {
				System.err.println("ERR: MalformedULR while loading p1 code");
				e2.printStackTrace();
				System.exit(1);
			} catch (Exception e) {
				System.err.println("ERR: Loading p1 code");
				e.printStackTrace();
				System.exit(1);
			}
		}//End trying to laod p1 as playercode
		secondSpawner.setTeam(1);
		spawners.add(secondSpawner);
		// -----------------You can look now

		actors = map.getActors();
		current = new GameState(map, actors);

	}
	//Run the game!
	public void run() {

		//while(!gameOver) should be subbed with this for loop {
		for(;;){
			updateGameState(current);
			//Generates and paints a bufferedImage of the current frame
			display.render(current);

			currTime = System.currentTimeMillis();

			while (System.currentTimeMillis() < currTime + turnTime) {
				continue;
			}
		}// End gameloop
	}// End run func

	public void updateGameState(GameState state) {

		// iterate through actors and update the map with the move each actor
		// wants to take.]
		ArrayList<Actor> toRem = new ArrayList<Actor>();
		for (Actor a : actors) {
			int aMove = a.update(state);
			int action = aMove / 10;
			int dir = aMove % 10;
			// If there is a movement
			if (action == 1) {
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

				//Check the node that the actor wants to move into and make sure it's valid
				if(actual != null && actual.getActor() == null && actual.getObstruction() == null){
					map.getNode(a.getX(), a.getY()).setActor(null);
					actual.setActor(a);
				}else{//Cannot move
					//System.out.println("Actor blocked");
				}
			}
			if (action == 2) {
				a.setDefending(dir);
			}

			if (action == 3) {
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
				if (enemy != null) {
					if (enemy.getTeam() != a.getTeam()) {
						System.out.println(enemy+" and "+a+" are fighting!");
						int att = a.attackThrow();
						int def = enemy.defenseThrow();
						// Simple Fighting System
						if (att > def) {
							enemy.setHealth(enemy.getHealth() - (att - def));
							if (enemy.getHealth() < 0) {

								toRem.add(enemy);
							}
						}
						// Retaliation!
						else {
							a.setHealth(a.getHealth() - (def - att) / 2);
							if (a.getHealth() < 0) {
								toRem.add(a);
							}
						}
					}
				}
			}
		}//End foreach actors

		for(Actor x : toRem){

			System.out.println(x + " should be deleated");
			map.getNode(x.getX(), x.getY()).setActor(null);
			actors.remove(x);

		}


		//Go through spawners and update them
		for (Spawner s : spawners) {
			Actor unit = s.update(state);

			if (unit != null) {
				//Set the team of the unit & make sure we can spawn that unit
				if (s.getTeam() == 0) {
					if (blueNumUnits <= mapNumUnits) {
						unit.setTeam(0);
					} else continue;
				} else {
					if (redNumUnits <= mapNumUnits) {
						unit.setTeam(1);
					} else continue;
				}
				System.out.println("New unit, team " + unit.getTeam());

				//If we want to spawn a unit, make sure we have space
				GraphNode castlePos = null;
				GraphNode currPos = null;
				if (s.getTeam() == 0) {
					castlePos = map.getBlueCastle();
				}else{
					castlePos = map.getRedCastle();
				}

				int castleX = castlePos.getX();
				int castleY = castlePos.getY();

				for (int i = 0; i <= 8; i++) {
					switch(i){
					case 0: currPos = map.getNode(castleX, castleY).getNNode(); break;
					case 1: currPos = map.getNode(castleX, castleY).getNENode(); break;
					case 2: currPos = map.getNode(castleX, castleY).getENode(); break;
					case 3: currPos = map.getNode(castleX, castleY).getSENode(); break;
					case 4: currPos = map.getNode(castleX, castleY).getSNode(); break;
					case 5: currPos = map.getNode(castleX, castleY).getSWNode(); break;
					case 6: currPos = map.getNode(castleX, castleY).getWNode(); break;
					case 7: currPos = map.getNode(castleX, castleY).getNWNode(); break;
					default: currPos = null;
					}
					if (currPos != null && currPos.getActor() == null) {//if currPos is valid
						currPos.setActor(unit);
						unit.setBasePos(new Point(castleX,castleY));

						//Add new actor to actor list
						//This bit works by running a loop till i is found, and exiting the loop when it is,
						//and then adding the unit after i to the array
						boolean foundPos = false;
						int j=0;
						while(!foundPos){
							if(j+1 >= actors.size()){
								//At end of list, add to end
								foundPos = true;
							}else if(actors.get(j).getAgility() > unit.getAgility()) {//If new actor is greater
								//add here
								foundPos = true;
							}else{
								j++;
							}
						}
						actors.add(j,unit);
						break;
					}
				}


			}
		}//End spawner
	}//End updateGameState method
}//End gameenging
