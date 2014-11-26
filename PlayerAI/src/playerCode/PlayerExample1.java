package playerCode;
import api.*;

public class PlayerExample1 extends Spawner {
	boolean spawn;
	int i = 0;
	public PlayerExample1(){
		spawn = true;

	}

	public Actor update(GameState G) {

		if(i < 1){ 
			spawn = false;
			i = i+1;
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
		boolean gotStuck = false;
		public int update(GameState G) {
			if(gotStuck || isStuck()){
				gotStuck = true;
				int ret = returnToBase(G);
				return ret;	
			}
			return 12;
		}
	}


	public class MyKnight extends Knight {

		public int update(GameState G) {
				if(isStuck()){
					return action("move","west");
				}
			if(isEnemyN(G)){
				return action("attack","north");
			}
			return action("move","north");
		}

	}
}


