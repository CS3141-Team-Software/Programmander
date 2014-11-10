package playerCode;

import api.Actor;
import api.GameState;
import api.MeleeActor;
import api.Spawner;

public class ScienceAI extends Spawner {

	public ScienceAI() {
		current = new MyStrategy();
		System.out.println("Science AI");
	}

	public class MyStrategy implements SpawnStrategy{

		@Override
		public Actor update(GameState G) {
			System.out.println("Spanwer creating actor");
			return new MyActor();
		}
	}
	
	
	public class MyActor extends MeleeActor{
		public MyActor(){
			this.current = new MyActorStrat();
		}
		public class MyActorStrat implements ActorStrategy{

			@Override
			public int update(GameState G) {
				System.out.println("Actor moving left");
				return 12;
			}
			
		}
	}
}

