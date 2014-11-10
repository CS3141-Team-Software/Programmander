package playerCode;
import api.Actor;
import api.GameState;
import api.Knight;
import api.Scout;
import api.Spawner;
import api.MeleeActor;
import api.Actor;

public class PlayerExample1 extends Spawner {
	public PlayerExample1(){

	}
	public Actor update(GameState G) {
		boolean spawn = true;
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
			int dir = 0;
			switch(dir%4){

			case 0: dir++; return 10;
			case 1: dir++; return 12;
			case 2: dir++; return 14;
			case 3: dir = 0; return 16;
			}

			//Should never get here
			return -1;
		}
	}
}


