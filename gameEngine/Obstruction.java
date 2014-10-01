package gameEngine;

import gameEngine.GraphNode;

public abstract class Obstruction {
	
	GraphNode node;
	//The type of obstruction. (Wall, hole, etc...) 
	String type;
	
	public String getType() {
		return type;
	}
	
	public GraphNode getnode() {
		return node;
	}
	
}
