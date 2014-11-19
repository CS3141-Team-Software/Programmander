package api;

import gameEngine.Graph;
import gameEngine.GraphNode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;


public class Spawner {
	/*
	 * X and y deschribe the actors position.
	 * Team indicates the Player that controls the Spawner
	 * The Spawner Strategy is different from other Strategies since
	 * it returns Actors instead of integers
	 */
	int x;
	int y;
	int team;
	private GameState state;
	int vision = 3;


	public void setTeam(int t) {
		team = t;
	}

	public Actor update(GameState G){
		return null;
	}


	/**
	 * Getters and Setters
	 */

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getTeam(){
		return team;
	}



	/**
	 * Observation Functions
	 * lets the user "look" at the map
	 * and remember things
	 */
	public boolean isNOpen(GameState G){
		return (G.getMap().getNode(x, y).getNNode() != null);
	}
	public boolean isNEOpen(GameState G){
		return (G.getMap().getNode(x, y).getNENode() != null);
	}
	public boolean isEOpen(GameState G){
		return (G.getMap().getNode(x, y).getENode() != null);
	}
	public boolean isSEOpen(GameState G){
		return (G.getMap().getNode(x, y).getSENode() != null);
	}
	public boolean isSOpen(GameState G){
		return (G.getMap().getNode(x, y).getSNode() != null);
	}
	public boolean isSWOpen(GameState G){
		return (G.getMap().getNode(x, y).getSWNode() != null);
	}
	public boolean isWOpen(GameState G){
		return (G.getMap().getNode(x, y).getWNode() != null);
	}
	public boolean isNWOpen(GameState G){
		return (G.getMap().getNode(x, y).getNWNode() != null);
	}


	public boolean isEnemyN(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getNNode();
		if(node != null){
			Actor found = node.getActor();
			return (found != null && found.getTeam() != team);
		}
		return false;
	}
	public boolean isEnemyNE(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getNENode();
		if(node != null){
			Actor found = node.getActor();
			return (found != null && found.getTeam() != team);
		}
		return false;
	}
	public boolean isEnemyE(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getENode();
		if(node != null){
			Actor found = node.getActor();
			return (found != null && found.getTeam() != team);
		}
		return false;
	}
	public boolean isEnemySE(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getSENode();
		if(node != null){
			Actor found = node.getActor();
			return (found != null && found.getTeam() != team);
		}
		return false;
	}
	public boolean isEnemyS(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getSNode();
		if(node != null){
			Actor found = node.getActor();
			return (found != null && found.getTeam() != team);
		}
		return false;
	}
	public boolean isEnemySW(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getSWNode();
		if(node != null){
			Actor found = node.getActor();
			return (found != null && found.getTeam() != team);
		}
		return false;
	}
	public boolean isEnemyW(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getWNode();
		if(node != null){
			Actor found = node.getActor();
			return (found != null && found.getTeam() != team);
		}
		return false;
	}
	public boolean isEnemyNW(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getNWNode();
		if(node != null){
			Actor found = node.getActor();
			return (found != null && found.getTeam() != team);
		}
		return false;
	}


	public ArrayList<Actor> getEnemies(GameState G){
		ArrayList<Actor> limlist = new ArrayList<Actor>();
		ArrayList<GraphNode> View = this.returnLimmitedView(G);
		Iterator<GraphNode> i = View.iterator();
		while(i.hasNext()){
			GraphNode next = i.next();
			if(next.getActor() != null && next.getActor().getTeam() != team){
				limlist.add(next.getActor());
			}
		}
		return limlist;
	}

	public Point getWeakestEnemy(GameState G){
		ArrayList<Actor> limlist = getEnemies(G);
		if(!limlist.isEmpty()){

			Iterator<Actor> i = limlist.iterator();
			Actor weakest = i.next();
			while(i.hasNext()){
				Actor next = i.next();
				if(weakest.getHealth() >= next.getHealth()){
					weakest = next;
				}


			}
			return new Point(weakest.getX(), weakest.getY());
		}
		return null;

	}

	public Point getStrongestEnemy(GameState G){
		ArrayList<Actor> limlist = getEnemies(G);
		if(!limlist.isEmpty()){

			Iterator<Actor> i = limlist.iterator();
			Actor weakest = i.next();
			while(i.hasNext()){
				Actor next = i.next();
				if(weakest.getHealth() <= next.getHealth()){
					weakest = next;
				}


			}
			return new Point(weakest.getX(), weakest.getY());
		}
		return null;

	}
	//END OF OBSERVATION FUNCTIONS


	private ArrayList<GraphNode> returnLimmitedView(GameState G){
		Graph map = G.map;
		ArrayList<GraphNode> array = new ArrayList<GraphNode>();
		for(int i =(-1 * vision); i <= vision; i++){
			for(int j = (-1 * vision); j <= vision; j++){

				if(map.getNode(x+i, y+j) != null){
					array.add(map.getNode(x+i, y+j));
				}
			}
		}

		return array;
	}
}
