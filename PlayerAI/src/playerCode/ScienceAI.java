package playerCode;

import Programmander.api.*;

public class ScienceAI implements Spawner {
	public ScienceAI(){
		System.out.println("ScienceAI constructor");
	}
	public void printSomething(){
		System.out.println("Something Mk 1.0");
		MyNewActor actor = new MyNewActor();
		actor.doAThing();
	}
}

class MyNewActor extends Actor {
	public void doAThing(){
		System.out.println("New Actor does a  thing!");
	}
}
