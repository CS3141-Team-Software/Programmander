package playerCode;

import api.Actor;
import api.GameState;
import api.Knight;
import api.Scout;
import api.Spawner;

public class ScienceAI extends Spawner {

	int i;
	public ScienceAI(){
		i=1;
		System.out.println("ScienceAI is team " + this.getTeam());
	}
	@Override
	public Actor update(GameState G) {
		if(getEnemies(G).isEmpty()){
			if(i == 1){
				i = 0;
				return new Norm();
			}else{
				i = 1;
				return new Attacker();
			}
		}else{
			return new Defender();
		}

	}


	public class Norm extends Scout{


		public int update(GameState G) {
			return 12;


		}
	}

	public class Attacker extends Scout{


		public int update(GameState G) {

			if(isEnemyS(G)){
				return action("attack","south");
			}
			return action("move","south");
		}

	}
	public class Defender extends Knight{


		public int update(GameState G) {

			if(isEnemyS(G)){
				return action("attack","south");
			}
			return action("move","south");
		}

	}
}






