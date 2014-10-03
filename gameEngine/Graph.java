package gameEngine;

//import GraphNode;


public class Graph {

	private int size;
	private GraphNode[][] map;
	private int rows;
	private int cols;

	public Graph(String fileName) {
		//Read first line to get size of graph	
		//Call makeGraph
		
		//While EOF != true, read subsequent lines
			//Split up tokens 
			//Send data to helper methods that generate the required classes/plug in the required art
		//Close file
	}
	/*
	 * Constructor
	 */
	public Graph(int x, int y){
		makeGraph(x, y);
	}
	
	public void makeGraph(int x, int y) {
		size = x*y;
		rows = y;
		cols = x;
		map = new GraphNode[y][x];


		//making an empty map...
		for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				map[i][j] = new GraphNode(i, j);
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
}	


