package playerCode;

import api.*;

public class AIofWin implements Spawner {
	public AIofWin(){
		System.out.println("AIofWin constructor");
	}
	public void printSomething(){
		System.out.println("Something Mk 2.0");
		MyActor actor = new MyActor();
		actor.doAThing();
	}
}

class MyActor extends Actor {
	public void doAThing(){
		System.out.println("Actor does a  thing!");
	}
}
