package api;


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

	public void setTeam(int t) {
		team = t;
	}
	
	public Actor update(GameState G){
		return current.update(G);
	}
	
	/**
	 * This is the interface for SpawnStrategy
	 */
	protected interface SpawnStrategy{
		Actor update(GameState G);
	}

	/**
	 * Getters and Setters
	 */
	public void setStrategy(SpawnStrategy pCurrent){
		current = pCurrent;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getTeam(){
		return team;
	}

}
