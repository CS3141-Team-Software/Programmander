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
			return action("move","northwest");
		}
	}


	public class MyKnight extends Knight {

		public int update(GameState G) {
			if(knowEnemyBasePosition()){
				if(isEnemyN(G)){
					return action("attack","north");
				}
				if(isEnemyE(G)){
					return action("attack","east");
				}
				if(isEnemyS(G)){
					return action("attack","south");
				}
				if(isEnemyW(G)){
					return action("attack","west");
				}
				return goToEnemyBase(G);
			}	
			
			return 0;
		}

	}
}


