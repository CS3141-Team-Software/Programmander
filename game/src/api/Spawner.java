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


	public void setTeam(int t) {
		team = t;
	}

	public Actor update(GameState G){
		return null;
	}


	/**
	 * Getters and Setters
	 */

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
