package gameEngine;


import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import display.Display;
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
	private GameState current;

	private ArrayList<Actor> actors;
	private ArrayList<Spawner> spawners;

	private Display display;
	private long turnTime = 1000;
	private long currTime;


	public GameEngine(String mapName, String firstAIName, String difficulty, boolean is2Player) {

		map = new Graph(mapName);
		display = new Display(mapName, firstAIName, difficulty, is2Player, map);
		spawners = new ArrayList<Spawner>();

		//Initialize 1st player's AI by calling Mark's magic code.
		
		
		//---------------Just don't look
		Spawner playerSpawner = null;
		File f = new File("ais/" + firstAIName);
	    URLClassLoader urlCl;
		try {
			urlCl = new URLClassLoader(new URL[] { f.toURI().toURL()},Spawner.class.getClassLoader());
			System.out.println(firstAIName.substring(0,firstAIName.length() - 4));
		    Class<?> testAI = urlCl.loadClass("playerCode." + firstAIName.substring(0,firstAIName.length() - 4));
		    Class<? extends Spawner> myAIClass = testAI.asSubclass(Spawner.class);
		    playerSpawner = myAIClass.newInstance();
		    playerSpawner.setTeam(0);
		    spawners.add(playerSpawner);

		} catch (Exception e) {
			System.err.println("ERR: Loading their code");
			e.printStackTrace();
			System.exit(1);
		}
		//-----------------You can look now
		

		if (is2Player) {
			//Initialize 2nd player's AI
		} else {
			//Initialize our AI based on which difficulty was selected
		}

		actors = map.getActors();
		current = new GameState(map, actors);
		
	}

	//Run the game!
	public void run() {
		
		//while(!gameOver) should be subbed with this for loop {
		for(int i=0;i<5;i++){
			updateGameState(current);

			//Generates and paints a bufferedImage of the current frame	
			display.render(current);
			
			currTime = System.currentTimeMillis();
			
			while (System.currentTimeMillis() < currTime + turnTime) {
				continue;
			}
			
		}

	}

	public void updateGameState(GameState state) {

		//Go through spawners and update them
		for (Spawner s : spawners) {
			Actor unit = s.update(state);

			if (unit != null) {
				GraphNode castlePos = null;
				GraphNode currPos = null;
				if (s.getTeam() == 0) {
					castlePos = map.getBlueCastle();
				} else castlePos = map.getRedCastle();

				int castleX = castlePos.getX();
				int castleY = castlePos.getY();

				for (int i = 0; i < 8; i++) {
					switch(i){
					case 0: currPos = map.getNode(castleX, castleY).getNNode(); break;
					case 1: currPos = map.getNode(castleX, castleY).getNENode(); break;
					case 2: currPos = map.getNode(castleX, castleY).getENode(); break;
					case 3: currPos = map.getNode(castleX, castleY).getSENode(); break;
					case 4: currPos = map.getNode(castleX, castleY).getSNode(); break;
					case 5: currPos = map.getNode(castleX, castleY).getSWNode(); break;
					case 6: currPos = map.getNode(castleX, castleY).getWNode(); break;
					case 7: currPos = map.getNode(castleX, castleY).getNWNode(); break;
					}

					if (currPos != null || currPos.getActor() == null) {
						break;
					} 
				}
				//currPos is the node to spawn the actor into.
				currPos.setActor(unit);
				for (int i = 0; i < actors.size(); i++) {
					if (actors.get(i).getAgility() <= unit.getAgility()) {
						actors.add(i, unit);
					}
				}
			}
		}

		//iterate through actors and update the map with the move each actor wants to take.
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

				//Check the node that the actor wants to move into and make sure it's valid
				if(actual != null && actual.getActor() == null && actual.getObstruction() == null){
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
								actors.remove(enemy);
							}
						}
						//Retaliation!
						else{
							a.setHealth(a.getHealth() - (def - att)/2);
							if(a.getHealth() < 0){
								map.getNode(a.getX(), a.getY()).setActor(null);
								map.getNode(a.getX(), a.getY()).setIsChanged(true);
								actors.remove(a);
							}
						}
					}
				}
			}
		}//End foreach actors
	}//End updateGameState method
}//End gameenging