package gameEngine;

import java.awt.Image;

import api.GraphNode;
import api.Obstruction;

import gameEngine.GraphNodeImplementation;

public class ObstructionImplementation implements Obstruction {
	
	private GraphNode node;
	//The type of obstruction. (Wall, hole, etc...) 
	private String type;
	
	public ObstructionImplementation(GraphNode n, String t) {
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
