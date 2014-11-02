package api;


/**
 * Melee Actor
 *
 * The class melee actor inherits from actor. It is the base class the user will use
 * to implement all kinds of melee units.
 *
 * The user can either inherit from MeleeActor or use the constructor to create a new unit.
 * The constructor is rather for tutorial purposes and therefore limited.
 *
 * @author Turel Tan
 *
 */

public class MeleeActor extends Actor {


	public MeleeActor(){

	}



	/*
	 * Some thinking on the actual fighting system:
	 * Agility decides who attacks first.
	 * This means the gameEngine must get the Agility of all fighters within a field
	 */

	public int attackThrow(){
		return (int)(attack * Math.random());
	}

	public int defenseThrow(){
		return (int)(defense * Math.random());
	}

	public int update(GameState G){
		return current.update(G);
	}



}