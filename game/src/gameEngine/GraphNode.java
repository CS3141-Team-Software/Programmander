package gameEngine;

import java.awt.Point;

import api.Actor;

/**
 * Graph Node
 * @author Caleb Chapman
 *
 */
public class GraphNode {

	private GraphNode north;
	private GraphNode south;
	private GraphNode east;
	private GraphNode west;
	private int row;
	private int col;
	private Obstruction obs;

	private String backImg;
	private Actor actor;
	private boolean isChanged;

	private boolean isBlueCastle;
	private boolean isRedCastle;

	/*
	 * Constructor for making an individual node
	 * ...for whatever
	 */
	public GraphNode(GraphNode n, GraphNode s, GraphNode e, GraphNode w){
		north = n;
		south = s;
		east = e;
		west = w;
		actor = null;
		isBlueCastle = false;
		isRedCastle = false;
	}

	/*
	 * Constructor the graph uses for
	 * making nodes
	 */
	public GraphNode(int y,int x){
		actor = null;
		north = null;
		south = null;
		east = null;
		west = null;
		row = y;
		col = x;
		isChanged = true;
		isBlueCastle = false;
		isRedCastle = false;
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
	 * Getter Method
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
		}else{
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

	public String getBackground(){
		return backImg;
	}

	public Actor getActor(){
		return actor;
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
	public void setBackground(String str){
		backImg = str;
	}

	public void setActor(Actor a){
		actor = a;
		if(a != null){
			a.setX(col);
			a.setY(row);
		}
		this.setIsChanged(true);
	}
	/*
	 * End of Setter Methods
	 */

	private Point getPoint() {
		return new Point(col,row);
	}

	/*
	 * getCoordinates
	 * returns the coordinates of the node as
	 * they were set in the constructor
	 */
	public String getCoordinates(){
		return ("[Row: " + row + ", Col: " + col + "]");
	}

	public boolean getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(boolean b) {
		isChanged = b;
	}
	public int getX(){
		return col;
	}
	public int getY(){
		return row;
	}

	public void setCastle(String s) {
		if (s.equals("BlueCastle")) {
			isBlueCastle = true;
			obs = new Obstruction(this, "bluecastle");
		}else if(s.equals("RedCastle")){
			isRedCastle = true;
			obs = new Obstruction(this, "redcastle");
		}else{
			try {
				throw new Exception("setCastle invalid argument");
			} catch (Exception e) {
				System.err.println("Invalid argument passed to setCastle");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public String getCastle() {
		if (isBlueCastle == true) {
			return "Blue";
		}
		else if (isRedCastle == true) {
			return "Red";
		}
		else return null;
	}
}
