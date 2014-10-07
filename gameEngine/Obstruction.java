package gameEngine;

import gameEngine.GraphNode;

public class Obstruction {
	
	private GraphNode node;
	//The type of obstruction. (Wall, hole, etc...) 
	private String type;
	
	public Obstruction(GraphNode n, String t) {
		type = t;
		node = n;		
	}
	
	public String getType() {
		return type;
	}
	
	public GraphNode getnode() {
		return node;
	}
	
}
