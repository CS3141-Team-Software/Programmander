package api;

import api.Actor.Strategy;

public class Spawner {
	/* 
	 * X and y deschribe the actors position.
	 * Team indicates the Player that controls the Spawner
	 * The Spawner Strategy is different from other Strategies since 
	 * it returns Actors instead of integers
	 */
	int x;
	int y;
	int team;
	private GameState state;
	protected SpawnStrategy current;

	public Spawner(int t) {
		team = t;
	}
	
	public Actor update(GameState G){
		return current.update(G);
	}
	
	public void setStrategy(SpawnStrategy pCurrent){
		current = pCurrent;
	}

	/**
	 * This is the interface for SpawnStrategy
	 */
	private interface SpawnStrategy{
		Actor update(GameState G);
	}
}
