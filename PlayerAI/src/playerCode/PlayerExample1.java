package playerCode;
import api.Actor;
import api.GameState;
import api.Knight;
import api.Scout;
import api.Spawner;
import api.MeleeActor;
import api.Actor;

public class PlayerExample1 extends Spawner {

	public PlayerExample1() {
		current = new MyStrategy();
		System.out.println("Player Example 1 exists");
	}

	public class MyStrategy implements SpawnStrategy{
		boolean spawn = true;

		public Actor update(GameState G) {
			System.out.println("Spanwer creating actor");
			if(spawn){
				spawn = false;
				System.out.println("Spawning scout");
				return new MyScout();
			}
			else{
				spawn = true;
				System.out.println("Spawning knight");
				return new MyKnight();
			}
		}
	}

	/*
	 * 
	 */

	public class MyScout extends Scout{
		public MyScout(){
			this.current = new Explorer();
		}
		public class Explorer implements ActorStrategy{

			public int update(GameState G) {
				return 12;
			}

		}
	}

	public class MyKnight extends Knight {
		public MyKnight(){
			this.current = new Huzzar();
		}
		public class Huzzar implements ActorStrategy{

			public int update(GameState G) {
				int dir = 0;
				switch(dir){
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
}

