package api;


public class Knight extends MeleeActor{

	public Knight() {
		agility = 5;
		attack = 10;
		defense = 10;
		vision = 1;
		health = 15;
		type = "knight";
	}

	@Override
	public int update(GameState G) {
		// TODO Auto-generated method stub
		return 0;
	}
}


/**
 * This is how Player-default code is supposed to look like in their own file
 * @author Turel
 *
 *public class MyKnight extends Scout{
 *	setStrategy (explore);
 *	}
 *
 *public int explore implements MeleeStrategy{
 *	}
 *
 */