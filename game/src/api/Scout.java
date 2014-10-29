package api;

public class Scout extends MeleeActor {
	Scout(){
		this.setAgility(20);
		this.setAttack(5);
		this.setDefense(5);
		this.setVision(3);
		this.setHealth(10);
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