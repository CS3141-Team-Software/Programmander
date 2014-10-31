package playerCode;

import api.*;

public class ScienceAI extends Spawner {

	public ScienceAI() {
		System.out.println("New ScienceAI");
		current = new MyStrategy();
	}

	public class MyStrategy implements SpawnStrategy{

		@Override
		public Actor update(GameState G) {
			System.out.println("Changing strategy to s2");
			setStrategy(new MyStrategy2());
			return null;
		}
	}
	public class MyStrategy2 implements SpawnStrategy{
		@Override
		public Actor update(GameState G) {
			System.out.println("Strategy S2 update");
			return null;
		}
	}
}

