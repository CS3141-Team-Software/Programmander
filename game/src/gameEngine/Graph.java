package gameEngine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import api.Actor;
/**
 * Graph
 * @author Caleb Chapman
 *
 */
//import GraphNode;


public class Graph{

	private int size;
	private GraphNode[][] map;
	private int rows;
	private int cols;
	private GraphNode[] list;
	private GraphNode redCastle;
	private GraphNode blueCastle;
	private int numUnits;

	/*
	 * Constructor given a text file
	 * will create a graph based on the values within
	 * Tutorial Maps
	 */
	public Graph(String fileName) {
		BufferedReader mapFile = null;

		try {
			InputStream stream = getClass().getResourceAsStream("/" + fileName);
			InputStreamReader streamReader = new InputStreamReader(stream);

			mapFile = new BufferedReader(streamReader);

		} catch (Exception e){
			System.err.println("Error: Could not fetch map.");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			Scanner in = new Scanner(mapFile);
			if(in.hasNext()){
				rows = in.nextInt();
			}
			if(in.hasNext()){
				cols = in.nextInt();
			}

			if (in.hasNext()) {
				numUnits = in.nextInt();
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
					if (obstrct.toLowerCase().equals("bluecastle")) {
						map[posX][posY].setCastle("BlueCastle");
						blueCastle = map[posX][posY];
						System.out.println("X: " + posX + " Y: " + posY);
					} else if (obstrct.toLowerCase().equals("redcastle")) {
						map[posX][posY].setCastle("RedCastle");
						redCastle = map[posX][posY];
					}
					else map[posX][posY].setObstruction(new Obstruction(map[posX][posY], obstrct));
				}

				if(in.hasNext()) {
					background = in.next();
					map[posX][posY].setBackground(background);
				}

			}

		}
		catch(Exception e){
			System.err.println("Map not found");
			e.printStackTrace();
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
		rows = y;
		cols = x;
		size = cols*rows;
		if (cols > 20 || rows > 20) {
			System.out.println("BAD MAP!");
			return;
		}

		map = new GraphNode[rows][cols];
		list = new GraphNode[size];


		int k = 0;
		for(y = 0; y < rows; y++){
			for(x = 0; x < cols; x++){
				map[y][x] = new GraphNode(y,x);
				list[k] = map[y][x];
				k++;
			}
		}
		/*
		 *
		 *
		 * 0,0   1,0
		 * 0,1
		 *
		 *
		 *
		 *
		 *
		 */
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
	 * Get NodePoint curr = toCheck.getFirst
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

	public GraphNode getBlueCastle() {
		return blueCastle;
	}

	public GraphNode getRedCastle() {
		return redCastle;
	}

	public int getNumUnits() {
		return numUnits;
	}
	public int pathFinder(Point start, Point dest){
		LinkedList<Point> toCheck = new LinkedList<Point>();

		//add start
		int[][] costMap = new int[rows][cols];
		//adding start
		toCheck.add(start);
		costMap[(int) start.getY()][(int) start.getX()] = 1;
		while(!toCheck.isEmpty()){
			Point curr = toCheck.removeFirst();
			GraphNode currPos = null;
			GraphNode myPos = map[(int) curr.getY()][(int) curr.getX()];
			for (int i = 0; i <= 8; i++) {
				switch(i){
				case 0: currPos = myPos.getNNode(); break;
				case 1: currPos = myPos.getNENode(); break;
				case 2: currPos = myPos.getENode(); break;
				case 3: currPos = myPos.getSENode(); break;
				case 4: currPos = myPos.getSNode(); break;
				case 5: currPos = myPos.getSWNode(); break;
				case 6: currPos = myPos.getWNode(); break;
				case 7: currPos = myPos.getNWNode(); break;
				default: currPos = null;
				}

				if(currPos != (null)){
					if(costMap[currPos.getY()][currPos.getX()] == 0 && (currPos.getActor() == null) && (currPos.getObstruction() == null)){
						toCheck.addLast(new Point(currPos.getX(), currPos.getY()));
						costMap[currPos.getY()][currPos.getX()] = costMap[(int) curr.getY()][(int) curr.getX()] + 1;
					}
				}

			}

		}

		Point ret = traceBack(dest, costMap);

		System.out.println(ret.toString());

		for (int i = 0; i < rows; i++){
			String temp = ">>";
			for (int j = 0; j < cols; j++){
				temp += ("  ["+i+"]["+j+"]: " + costMap[i][j]);
			}
			temp += " <<";
			System.out.println(temp);
		}

		return 0;
	}

	private Point traceBack(Point pnt, int[][] costMap){

		if(costMap[(int) pnt.getY()][(int) pnt.getX()] == 2){
			return pnt;
		}

		GraphNode currPos = null;
		GraphNode myPos = map[(int) pnt.getY()][(int) pnt.getX()];

		int cost = costMap[pnt.y][pnt.x];
		for (int i = 0; i <= 8; i++) {
			switch(i){
			case 0: currPos = myPos.getNNode(); System.out.println("0 " + pnt.toString());break;
			case 1: currPos = myPos.getNENode(); System.out.println("1");break;
			case 2: currPos = myPos.getENode(); System.out.println("2");break;
			case 3: currPos = myPos.getSENode(); System.out.println("3");break;
			case 4: currPos = myPos.getSNode(); System.out.println("4");break;
			case 5: currPos = myPos.getSWNode(); System.out.println("5");break;
			case 6: currPos = myPos.getWNode(); System.out.println("6");break;
			case 7: currPos = myPos.getNWNode(); System.out.println("7");break;
			}
			if(currPos != null){
				if(costMap[currPos.getY()][currPos.getX()] == cost-1){
					return traceBack(new Point(currPos.getX(), currPos.getY()), costMap);
				}
			}
		}
		return null; //error, no Path found
	}
}


