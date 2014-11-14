package api;

import gameEngine.Graph;
import gameEngine.GraphNode;

import java.awt.Point;
import java.util.ArrayList;


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
	//END OF TATTLING FUNCTIONS


	//Shouts

	public void callAllies(){
	}

	public int checkAllies(GameState G){

		return 0;
	}



	/*
	 * This is the function the player will use to take any action
	 * with their Actor.
	 */

	public int action(int action, int direction){
		return action + direction;
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