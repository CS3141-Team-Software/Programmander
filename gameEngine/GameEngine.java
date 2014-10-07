package gameEngine;

import java.util.ArrayList;


/**
 * @author Frederick Nolte
 *
 */
public class GameEngine {

	private Graph map;
	private GraphNode[][] nodes;

	public GameEngine(String mapName, String firstAIName, String difficulty, boolean is2Player) {

		
		//Make our map using data passed in by LauncherWindow
		map = new Graph(mapName);
		nodes = map.getNodes();
		

		//Initialize 1st player's AI by calling Mark's magic code.
		//TODO: Implement Mark's magic code
		
		if (is2Player) {
			//Initialize 2nd player's AI
		} else {
			//Initialize our AI based on which difficulty was selected
		}



	}


}
