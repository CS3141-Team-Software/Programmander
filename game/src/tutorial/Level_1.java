package tutorial;
import api.Actor;
import api.GameState;
import api.Knight;
import api.Scout;
import api.Spawner;

public class Level_1 extends Spawner {
	boolean spawn;

	//Alternates spawning MyScout and MyKnight. Each one just goes in a specific direction.
	public Level_1(){
		spawn = true;
		System.out.println("Level 1 is team " + this.getTeam());
	}

	public Actor update(GameState G) {
		if(spawn){
			spawn = false;
			return new MyScout();
		}
		else{
			spawn = true;
			return new MyKnight();
		}
	}

	/*
	 * 
	 */

	public class MyScout extends Scout{
		public int update(GameState G) {
			return 12;
		}
	}


	public class MyKnight extends Knight {
		public int update(GameState G) {
			return 10;
		}

	}
}


