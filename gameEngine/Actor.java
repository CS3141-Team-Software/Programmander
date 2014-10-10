package gameEngine;


/**
 * Actor 
 * 
 * Class so an actor can get information and interface with the game map. 
 * 
 * @author Frederick Nolte
 *
 */
public abstract class Actor {
	
	public Obstruction[] getObstructions() {
		//TODO: This is dummy code so there's no errors. Fix this once we decide on an implementation.
		Obstruction[] obs = new Obstruction[10];
		return obs;
	}
	
	public Actor[] getActors() {
		//TODO: This is dummy code so there's no errors. Fix this once we decide on an implementation.
		Actor[] act = new Actor[10];
		return act;
	}
	
	public GraphNode[] getSurrounding() {
		//TODO: This is dummy code so there's no errors. Fix this once we decide on an implementation.
		GraphNode[] surr = new GraphNode[8];
		return surr;
	}
}
