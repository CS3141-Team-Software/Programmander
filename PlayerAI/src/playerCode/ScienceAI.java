package playerCode;

import api.Actor;
import api.GameState;
import api.Scout;
import api.Spawner;

public class ScienceAI extends Spawner {

	@Override
	public Actor update(GameState G) {
		System.out.println("Spanwer creating actor");
		return new MyActor();

	}


	public class MyActor extends Scout{


		public int update(GameState G) {
			System.out.println("Actor moving left");
			return 12;


		}
	}
	
}

