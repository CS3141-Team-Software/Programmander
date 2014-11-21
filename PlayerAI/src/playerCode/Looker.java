package playerCode;
import api.*;

public class Looker extends Spawner {
	boolean spawn;
	int i = 0;
	public Looker(){
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
		public int update(GameState G) {
			lookForEnemyBase(G);
			if(knowEnemyBasePosition()){
				int ret = returnToBase(G);
				shoutEnemyBasePosition(G);
				return ret;	
			}
			return 10;
		}
	}


	public class MyKnight extends Knight {

		public int update(GameState G) {
			if(knowEnemyBasePosition()){
				if(isEnemyN(G)){
					return action("attack","north");
				}
				return action("move","north");
			}	
			
			return 0;
		}

	}
}


