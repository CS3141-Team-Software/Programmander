package api;

import java.util.ArrayList;

import gameEngine.Graph;//TODO: make a graph interface in api, that graph implements

public class GameState {
	public Graph map;
	private ArrayList<Actor> actors;
	
	public GameState(Graph map,ArrayList<Actor> actors){
		this.map = map;
		this.actors = actors;
	}
	public GameState(int a, int b, int c){
		
	}

	public Graph getMap() {
		return map;
	}
}
