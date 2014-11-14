package playerCode;
import api.*;

public class PlayerExample1 extends Spawner {
	boolean spawn;
	public PlayerExample1(){
		spawn = true;

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


