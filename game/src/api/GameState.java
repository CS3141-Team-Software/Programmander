package api;

import java.util.ArrayList;

import gameEngine.Graph;//TODO: make a graph interface in api, that graph implements

public class GameState {
	public Graph map;
	private ArrayList<Actor> actors;
	
	//This Gamestate is passed tho and fro GameEngine and GameLogic
	public GameState(Graph map,ArrayList<Actor> actors){
		this.map = map;
		this.actors = actors;
	}
	
	//This is the limiting gamestate that gets passed to the actor
	public GameState(int pActorx, int pActorY, int pVisibility){
		
	}

	public Graph getMap() {
		return map;
	}
	
	public ArrayList<Actor> getActors(){
		return actors;
	}
}
