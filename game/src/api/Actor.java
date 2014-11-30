package api;

import gameEngine.Graph;
import gameEngine.GraphNode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Actor
 *
 * Class so an actor can get information and interface with the game map.
 *
 * @author Frederick Nolte
 *
 */
public abstract class Actor {

	/*
	 * The basic stats of units
	 */
	Point basePos;
	Point flagPos;
	Point rallyPoint;
	Point enBasePos;


	int health;
	int attack;
	int agility;
	int vision;
	int defense;
	String type;
	boolean dead = false;

	/*
	 * Unit not defending have defending -1
	 * Then it is 0=North, 1=Northeast etc.
	 *
	 * The maxPoints describe the maximum points that can
	 * divded up for the stats
	 */

	int defending = -1;
	int maxPoints;
	int team;

	/*
	 * The actor needs to know Graph cause he will be passed a
	 * Graph with the Gamestate.
	 * X and y deschribe the actors position.
	 */

	int x;
	int y;
	Point prevLoc;

	/*
	 * These Variables are needed as macro so that the
	 * user can just type direction + action
	 *
	 *  * This is the movement Method the Player uses
	 * Return Values always 2 digits
	 *
	 * First digit
	 * 1 = MOVE
	 * 2 = DEFEND
	 * 3 = ATTACK
	 *
	 * Second digit
	 * 0 = North
	 * 1 = North-East
	 * 2 = East
	 * 3 = South-East
	 * 4 = South
	 * 5 = South-West
	 * 6 = West
	 * 7 = North-West
	 *
	 */

	static int MOVE = 10;
	static int DEFEND = 20;
	static int ATTACK = 30;

	static int NORTH = 0;
	static int NORTHEAST = 1;
	static int EAST = 2;
	static int SOUTHEAST = 3;
	static int SOUTH = 4;
	static int SOUTHWEST = 5;
	static int WEST = 6;
	static int NORTHWEST = 7;


	/*
	 * These functions are needed
	 */
	abstract public int attackThrow();
	abstract public int defenseThrow();
	abstract public int update(GameState G);

	//Pathfinding functions

	public void goToClosestEnemy(){
	}

	//Redundant
	public void goToClosestMarker(){
	}
	/**
	 * Path finding Functions
	 * these return the first step towards
	 * their destinations/VICTORY
	 */

	public int returnToBase(GameState G){
		Graph map = G.map;
		return map.pathFinder(new Point(x,y), basePos);

	}

	public int goToFlag(GameState G){
		Graph map = G.map;
		if(flagPos != null){
			return map.pathFinder(new Point(x,y), flagPos);
		}else{
			return 0;
		}
	}

	public int goToRally(GameState G){
		Graph map = G.map;
		if(rallyPoint != null){
			return map.pathFinder(new Point(x,y), rallyPoint);
		}else{
			return 0;
		}
	}

	public int goToEnemyBase(GameState G){
		Graph map = G.map;
		if(enBasePos != null){
			return map.pathFinder(new Point(x,y), enBasePos);
		}else{
			return 0;
		}
	}

	public int goToPoint(GameState G, Point p){
		Graph map = G.map;
		return map.pathFinder(new Point(x,y), p);

	}
	//END OF PATHFINDING FUNCTIONS

	/**
	 * "Tattling" Functions
	 * lest the user know if the actor knows
	 * the position of something
	 */
	public boolean knowFlagPosition(){
		return (flagPos != null);
	}
	public boolean knowEnemyBasePosition(){
		return (enBasePos != null);
	}
	public boolean heardRally(){
		return (rallyPoint != null);
	}
	public boolean isStuck(){
		if(prevLoc == null){
			prevLoc = new Point(x,y);
			return false;
		}
		Point temp = prevLoc;
		prevLoc = new Point(x,y);
		return (((int)temp.getX() == x) && ((int)temp.getY() == y));
	}
	//END OF TATTLING FUNCTIONS


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
			if(found != null){
				return (found != null && found.getTeam() != team);
			}
			if(team ==0 && node.getCastle() == "Red"){
				return true;
			}
			if(team ==1 && node.getCastle() == "Blue"){
				return true;
			}
		}
		return false;
	}
	public boolean isEnemyNE(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getNENode();
		if(node != null){
			Actor found = node.getActor();
			if(found != null){
				return (found != null && found.getTeam() != team);
			}
			if(team ==0 && node.getCastle() == "Red"){
				return true;
			}
			if(team ==1 && node.getCastle() == "Blue"){
				return true;
			}
		}
		return false;
	}
	public boolean isEnemyE(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getENode();
		if(node != null){
			Actor found = node.getActor();
			if(found != null){
				return (found != null && found.getTeam() != team);
			}
			if(team ==0 && node.getCastle() == "Red"){
				return true;
			}
			if(team ==1 && node.getCastle() == "Blue"){
				return true;
			}
		}
		return false;
	}
	public boolean isEnemySE(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getSENode();
		if(node != null){
			Actor found = node.getActor();
			if(found != null){
				return (found != null && found.getTeam() != team);
			}
			if(team ==0 && node.getCastle() == "Red"){
				return true;
			}
			if(team ==1 && node.getCastle() == "Blue"){
				return true;
			}
		}
		return false;
	}
	public boolean isEnemyS(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getSNode();
		if(node != null){
			Actor found = node.getActor();
			if(found != null){
				return (found != null && found.getTeam() != team);
			}
			if(team ==0 && node.getCastle() == "Red"){
				return true;
			}
			if(team ==1 && node.getCastle() == "Blue"){
				return true;
			}
		}
		return false;
	}
	public boolean isEnemySW(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getSWNode();
		if(node != null){
			Actor found = node.getActor();
			if(found != null){
				return (found != null && found.getTeam() != team);
			}
			if(team ==0 && node.getCastle() == "Red"){
				return true;
			}
			if(team ==1 && node.getCastle() == "Blue"){
				return true;
			}
		}
		return false;
	}
	public boolean isEnemyW(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getWNode();
		if(node != null){
			Actor found = node.getActor();
			if(found != null){
				return (found != null && found.getTeam() != team);
			}
			if(team ==0 && node.getCastle() == "Red"){
				return true;
			}
			if(team ==1 && node.getCastle() == "Blue"){
				return true;
			}
		}
		return false;
	}
	public boolean isEnemyNW(GameState G){
		GraphNode node = G.getMap().getNode(x, y).getNWNode();
		if(node != null){
			Actor found = node.getActor();
			if(found != null){
				return (found != null && found.getTeam() != team);
			}
			if(team ==0 && node.getCastle() == "Red"){
				return true;
			}
			if(team ==1 && node.getCastle() == "Blue"){
				return true;
			}
		}
		return false;
	}


	public ArrayList<Actor> getEnemies(GameState G){
		ArrayList<Actor> limlist = new ArrayList<Actor>();
		ArrayList<GraphNode> View = this.returnLimitedView(G);
		Iterator<GraphNode> i = View.iterator();
		while(i.hasNext()){
			GraphNode next = i.next();
			if(next.getActor() != null && next.getActor().getTeam() != team){
				limlist.add(next.getActor());
			}
		}
		return limlist;
	}

	public ArrayList<Actor> getAllies(GameState G){
		ArrayList<Actor> limlist = new ArrayList<Actor>();
		ArrayList<GraphNode> View = this.returnLimitedView(G);
		Iterator<GraphNode> i = View.iterator();
		while(i.hasNext()){
			GraphNode next = i.next();
			if(next.getActor() != null && next.getActor().getTeam() == team){
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

	public void lookForEnemyBase(GameState G){
		ArrayList<GraphNode> View = this.returnLimitedView(G);
		Iterator<GraphNode> i = View.iterator();
		while(i.hasNext()){
			GraphNode next = i.next();
			if(next.getCastle() != null){
				if(team == 1){
					if (next.getCastle() == "Blue"){
						enBasePos = new Point(next.getX(), next.getY());
					}
				}else{
					if (next.getCastle() == "Red"){
						enBasePos = new Point(next.getX(), next.getY());
					}
				}

			}
		}
	}
	//END OF OBSERVATION FUNCTIONS


	//Shouts

	public void shoutEnemyBasePosition(GameState G){
		ArrayList<Actor> limlist = getAllies(G);
		if(!limlist.isEmpty()){
			Iterator<Actor> i = limlist.iterator();
			Actor weakest = i.next();
			while(i.hasNext()){
				i.next().enBasePos = enBasePos;
			}
		}

	}
	//END OF SHOUTS

	/*
	 * This is the function the player will use to take any action
	 * with their Actor.
	 */

	public int action(int action, int direction){
		return action + direction;
	}
	//Caleb - I don't think we can expect the "macro" things to be passed down
	//to the children (Scouts, Knights, ect). Here's a string implementation.

	public int action(String act, String dir){
		int ret = 0;
		//for the action string
		if(act.toLowerCase() == "move")
			ret = 10;
		else if(act.toLowerCase() == "defend")
			ret = 20;
		else if(act.toLowerCase() == "attack")
			ret = 30;
		else
			return 0;


		//for the direction string
		if(dir.toLowerCase() == "north")
			ret += 0;
		else if(dir.toLowerCase() == "northeast")
			ret += 1;
		else if(dir.toLowerCase() == "east")
			ret += 2;
		else if(dir.toLowerCase() == "southeast")
			ret += 3;
		else if(dir.toLowerCase() == "south")
			ret += 4;
		else if(dir.toLowerCase() == "southwest")
			ret += 5;
		else if(dir.toLowerCase() == "west")
			ret += 6;
		else if(dir.toLowerCase() == "northwest")
			ret += 7;
		else
			return 0;

		return ret;

	}


	/**
	 * Gettas an' Settas
	 */
	public int getDefending(){
		return defending;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getTeam(){
		return team;
	}
	public int getHealth(){
		return health;
	}
	public int getAttack(){
		return attack;
	}
	public int getAgility(){
		return agility;
	}
	public int getVision(){
		return vision;
	}
	public int getDefense(){
		return defense;
	}
	public int maxPoints(){
		return maxPoints;
	}
	public void setDefending(int pDefending){
		defending = pDefending;
	}
	public void setHealth(int pHealth){
		health = pHealth;
	}
	public void setAgility(int pAgility){
		agility = pAgility;
	}
	public void setVision(int pVision){
		vision = pVision;
	}

	public void setX(int pX){
		x = pX;
	}
	public void setY(int pY){
		y= pY;
	}
	public void setAttack(int pAttack){
		attack = pAttack;
	}
	public void setDefense(int pDefense){
		defense = pDefense;
	}

	public void setBasePos(Point b){
		basePos = b;
	}

	public void setTeam(int i) {
		team = i;
	}
	public void setPoint(Point point) {
		x = (int) point.getX();
		y = (int) point.getY();

	}
	public String getType() {
		return type;
	}

	public Point getFlagPos() {
		return flagPos;
	}
	public void setFlagPos(Point flagPos) {
		this.flagPos = flagPos;
	}
	public Point getRallyPoint() {
		return rallyPoint;
	}
	public void setRallyPoint(Point rallyPoint) {
		this.rallyPoint = rallyPoint;
	}
	public Point getEnBasePos() {
		return enBasePos;
	}
	public void setEnBasePos(Point enBasePos) {
		this.enBasePos = enBasePos;
	}

	private ArrayList<GraphNode> returnLimitedView(GameState G){
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

	public void setDead() {
		dead = true;
	}

	public boolean isDead() {
		return dead;
	}
}