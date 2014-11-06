package api;

public class Scout extends MeleeActor{

	public Scout() {
		agility = 20;
		attack = 5;
		defense = 5;
		vision = 3;
		health = 10;
		type = "scout";
	}

	public int update(GameState G) {
		// TODO Auto-generated method stub
		return 0;
	}

}

/**
 * This is how Player-default code is supposed to look like in their own file
 * @author Turel
 *public int update(GameState G) {
 *		return 0;
 *	}
 *
 */