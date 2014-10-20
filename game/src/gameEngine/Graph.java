package gameEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import api.Actor;
/**
 * Graph
 * @author Caleb Chapman
 *
 */
//import GraphNode;


public class Graph {

	private int size;
	private GraphNode[][] map;
	private int rows;
	private int cols;
	private GraphNode[] list;

	/*
	 * Constructor given a text file
	 * will create a graph based on the values within
	 * Tutorial Maps
	 */
	public Graph(String fileName) {

		try{
			File file = new File(System.getProperty("user.dir") + "/assets/maps/" + fileName);
			Scanner in = new Scanner(file);
			if(in.hasNext()){
				rows = in.nextInt();
			}
			if(in.hasNext()){
				cols = in.nextInt();
			}

			makeGraph(cols, rows);

			int posX = -1;
			int posY = -1;
			String obstrct = "";
			String background = "";

			while(in.hasNext()){
				posX = in.nextInt();

				if(in.hasNext()) {
					posY = in.nextInt();
				}

				if(in.hasNext()) {
					obstrct = in.next();
				}
				
				if(!obstrct.equals("0")){
					//Implement castle checks
					//if obstrct.equals("BlueCastle"); // generate a new Blue team spawner
					//else if obstruct.equals("RedCastle"); //generate a new enemy spawner
					map[posX][posY].setObstruction(new Obstruction(map[posX][posY],"wall"));
				}

				if(in.hasNext()) {
					background = in.next();
					map[posX][posY].setBackground(background);
				}

			}

		}
		catch(FileNotFoundException e){
			System.err.println("Map not found");
			System.exit(1);
		}
	}
	
	
	/*
	 * Constructor
	 */
	public Graph(int x, int y){
		makeGraph(x, y);
	}

	public void makeGraph(int x, int y) {
		if (x > 19 || y > 19) {
			System.out.println("BAD MAP!");
			return;
		}
		size = x*y;
		rows = y;
		cols = x;

		map = new GraphNode[y][x];
		list = new GraphNode[x * y]; 


			int posX = -1;
			int posY = -1;
			String obstrct = "";
			String background = "";//making an empty map...\
			int k = 0;
		for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				map[i][j] = new GraphNode(i, j);
				list[k] = map[i][j];
				k++;
			}
		}

		//Connect all nodes together
		for(int i = 0; i < y ; i++){
			for(int j = 0; j < x ; j++){
				//North
				if(i > 0){
					map[i][j].setNNode(map[i-1][j]);
				}
				//South
				if(i < y-1){
					map[i][j].setSNode(map[i+1][j]);
				}
				//East
				if(j < x-1){
					map[i][j].setENode(map[i][j+1]);
				}
				//West
				if(j > 0){
					map[i][j].setWNode(map[i][j-1]);
				}
			}
		}
	}

	/*
	 * Get Node
	 * returns the node at the specified coordinates 
	 */
	public GraphNode getNode(int x, int y){
		if(x >= cols || y >= rows){
			return null;
		}
		else{
			return map[y][x];
		}
	}

	/*
	 * Get nodes
	 * Returns the 2D array
	 */
	public GraphNode[][] getNodes(){
		return map;
	}


	public Actor getUnitType(String s) {
		//Returns a newly created unit based on what we read in from the file
		return null;
	}
	
	/*
	 * getNodesSing
	 * returns a list of nodes existing in the graph
	 */
	public GraphNode[] getNodesSing(){
		return list;
	}
	
	/*
	 * getActors
	 * returns an arraylist of actors
	 */
	public ArrayList<Actor> getActors(){
		ArrayList<Actor> ret = new ArrayList<Actor>();
		for(int i = 0; i < list.length; i++){
			if(! (list[i].getActor() == null)){
				ret.add(list[i].getActor());
			}
		}
		return ret;
	}
	
	/*
	 * getRows
	 * Returns an integer representing the number of rows in the array
	 */
	public int getRows() {
		return rows;
	}
	
	/*
	 * getCols
	 * Returns an integer representing the number of columns in the array
	 */
	public int getCols() {
		return cols;
	}

}	


