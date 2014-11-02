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

}

/**
 * This is how Player-default code is supposed to look like in their own file
 * @author Turel
 *
 *public class MyScout extends Scout{
 *	setStrategy (explore);
 *	}
 *
 *public int explore implements MeleeStrategy{
 *	}
 *
 */