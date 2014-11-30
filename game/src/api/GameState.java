package api;

import gameEngine.Graph;

import java.util.ArrayList;

public class GameState {
	public Graph map;
	private ArrayList<Actor> actors;


	public GameState(Graph map, ArrayList<Actor> actors){
		this.map = map;
		this.actors = actors;
	}
	public Graph getMap() {
		return map;
	}

	public ArrayList<Actor> getActors(){
		return actors;
	}
}
