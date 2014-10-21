package api;

import java.util.ArrayList;

import gameEngine.GraphImplementation;//TODO: make a graph interface in api, that graph implements

public class GameState {
	public GraphImplementation map;
	private ArrayList<Actor> actors;
	
	public GameState(GraphImplementation map,ArrayList<Actor> actors){
		this.map = map;
		this.actors = actors;
	}

	public GraphImplementation getMap() {
		return map;
	}
}
