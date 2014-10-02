package gameEngine;

//import GraphNode;
import Obstruction;

/*
 * Programmander Graph Node
 * Caleb Chapman
 */
public class GraphNode {
	
	private GraphNode north;
	private GraphNode south;
	private GraphNode east;
	private GraphNode west;
	private int row;
	private int col;
	private Obstruction obs;
	//private actor[] actors;
	
	/*
	 * Constructor for making an individual node
	 * ...for whatever
	 */
	public GraphNode(GraphNode n, GraphNode s, GraphNode e, GraphNode w){
		north = n;
		south = s;
		east = e;
		west = w;
	}
	
	/*
	 * Constructor the graph uses for
	 * making nodes
	 */
	public GraphNode(int y,int x){
		north = null;
		south = null;
		east = null;
		west = null;
		row = y;
		col = x;
	}
	
	/*
	 * Getter Methods for
	 * North South East West 
	 */
	public GraphNode getNNode(){
		return north;
	}
	public GraphNode getSNode(){
		return south;
	}
	public GraphNode getENode(){
		return east;
	}
	public GraphNode getWNode(){
		return west;
	}
	
	public GraphNode getNENode(){
		if(north == null){
			return null;
		}
		if(north.getENode() != null){
			return north.getENode();
		}
		else{
			return null;
		}
	}
	
	/*
	 * Getter Methods for
	 * NorthEast SouthEast
	 * NorthWest SouthWest
	 */
	public GraphNode getSENode(){
		if(south == null){
			return null;
		}
		if(south.getENode() != null){
			return south.getENode();
		}
		else{
			return null;
		}
	}
	
	public GraphNode getNWNode(){
		if(north == null){
			return null;
		}
		if(north.getWNode() != null){
			return north.getWNode();
		}
		else{
			return null;
		}
	}
	
	public GraphNode getSWNode(){
		if(south == null){
			return null;
		}
		if(south.getWNode() != null){
			return south.getWNode();
		}
		else{
			return null;
		}
	}
	
	public Obstruction getObstruction(){
		return obs;
	}
	/*
	 * End of Getter Methods
	 */
	
	/*
	 * Setter Methods
	 */
	public void setNNode(GraphNode n){
		north = n;
	}
	public void setSNode(GraphNode s){
		south = s;
	}
	public void setENode(GraphNode e){
		east = e;
	}
	public void setWNode(GraphNode w){
		west = w;
	}
	
	public void setObstruction(Obstruction o){
		obs = o;
	}
	/*
	 * End of Setter Methods
	 */
	
	/*
	 * getCoordinates
	 * returns the coordinates of the node as 
	 * they were set in the constructor
	 */
	public String getCoordinates(){
		return ("[Row: " + row + ", Col: " + col + "]");
	}
}
