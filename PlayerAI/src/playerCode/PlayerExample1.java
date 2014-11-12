package playerCode;
import api.*;

public class PlayerExample1 extends Spawner {
	boolean spawn;
	public PlayerExample1(){
		spawn = true;
		System.out.println("Spawner made");
	}

	public Actor update(GameState G) {
		System.out.println("Scout: " + spawn);
		if(spawn){ 
			spawn = false;
			System.out.println("Spanwer creating scout");
			return new MyScout();
		}
		else{
			spawn = true;
			System.out.println("Spanwer creating knight");
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


