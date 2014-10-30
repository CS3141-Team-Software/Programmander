package api;

public class Spawner extends Actor {

	SpawnStrategy current;

	public Spawner(int t) {
		team = t;
	}
	
	@Override
	public int attackThrow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int defenseThrow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(GameState G){
		return current.update(G);
	}
	
	public void setStrategy(SpawnStrategy pCurrent){
		current = pCurrent;
	}

	/**
	 * This is the interface for SpawnStrategy
	 */
	private interface SpawnStrategy{
		int update(GameState G);
	}
}
