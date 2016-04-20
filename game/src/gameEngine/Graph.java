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
			System.err.println("Bad mapfile. Too large.");
			System.exit(-1);
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
		if(x >= cols || y >= rows || x < 0 || y < 0){
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
					if(costMap[currPos.getY()][currPos.getX()] == 0 && (currPos.getActor() == null) && (currPos.getObstruction() == null || currPos.getCastle() != null)){
						toCheck.addLast(new Point(currPos.getX(), currPos.getY()));
						costMap[currPos.getY()][currPos.getX()] = costMap[(int) curr.getY()][(int) curr.getX()] + 1;
					}
					if(costMap[currPos.getY()][currPos.getX()] == 0 && (currPos.getActor() != null) && (currPos.getObstruction() == null || currPos.getCastle() != null)){
						costMap[currPos.getY()][currPos.getX()] = -1 * (costMap[(int) curr.getY()][(int) curr.getX()] + 1);
					}
				}

			}

		}
		//Print costmap
		/*
		System.out.println("Costmap\n\t  0   1   2   3   4   5   6   7   8   9  10");
		for(int i=0; i<rows;i++){
			System.out.print(i + "\t");
			for(int j=0; j<cols; j++){
				System.out.printf("%3d ",costMap[i][j]);
			}
			System.out.println();
		}*/


		Point ret = traceBack(dest, costMap);

		if(ret == null){// we couldn't find a rout to dest
			return 0;
		}
		else{
			int act = 0;
			GraphNode currPos = null;
			GraphNode myPos = map[(int) start.getY()][(int) start.getX()];
			for (int i = 0; i <= 8; i++) {
				switch(i){
				case 0: currPos = myPos.getNNode(); act = 10; break;
				case 1: currPos = myPos.getSNode(); act = 14; break;
				case 2: currPos = myPos.getENode(); act = 12; break;
				case 3: currPos = myPos.getWNode(); act = 16; break;
				case 4: currPos = myPos.getNENode(); act = 11; break;
				case 5: currPos = myPos.getSWNode(); act = 15; break;
				case 6: currPos = myPos.getSENode(); act = 13; break;
				case 7: currPos = myPos.getNWNode(); act = 17; break;
				default: currPos = null;
				}

				if(currPos != (null)){
					if((currPos.getX() == (int)ret.getX()) && (currPos.getY() == (int)ret.getY())){
						return act;
					}
				}

			}
		}
		return 0;// should never reach this
	}

	private Point traceBack(Point pnt, int[][] costMap){


		if(costMap[(int) pnt.getY()][(int) pnt.getX()] == 2){
			return pnt;
		}

		GraphNode currPos = null;
		GraphNode myPos = map[(int) pnt.getY()][(int) pnt.getX()];


		int cost = Math.abs(costMap[pnt.y][pnt.x]);

		if (cost == 0){
			int lowest = 9999999;
			GraphNode lowestNode = null;
			for (int i = 0; i <= 8; i++) {
				switch(i){
				case 0: currPos = myPos.getNNode(); break;
				case 1: currPos = myPos.getSNode(); break;
				case 2: currPos = myPos.getENode(); break;
				case 3: currPos = myPos.getWNode(); break;
				case 4: currPos = myPos.getNENode(); break;
				case 5: currPos = myPos.getSWNode(); break;
				case 6: currPos = myPos.getSENode(); break;
				case 7: currPos = myPos.getNWNode();break;
				}
				if(currPos != null){
					if(Math.abs(costMap[currPos.getY()][currPos.getX()]) < lowest){
						lowest = Math.abs(costMap[currPos.getY()][currPos.getX()]);
						if(lowest != 0){
							lowestNode = currPos;
						}
					}
				}
			}
			if(lowestNode != null){
				Point ret = new Point(lowestNode.getX(), lowestNode.getY());
				return traceBack(ret, costMap);
			}
		}

		for (int i = 0; i <= 8; i++) {
			switch(i){
			case 0: currPos = myPos.getNNode(); break;
			case 1: currPos = myPos.getSNode(); break;
			case 2: currPos = myPos.getENode(); break;
			case 3: currPos = myPos.getWNode(); break;
			case 4: currPos = myPos.getNENode(); break;
			case 5: currPos = myPos.getSWNode(); break;
			case 6: currPos = myPos.getSENode(); break;
			case 7: currPos = myPos.getNWNode();break;
			}
			if(currPos != null){
				if(costMap[currPos.getY()][currPos.getX()] == cost-1){
					return traceBack(new Point(currPos.getX(), currPos.getY()), costMap);
				}
			}
		}

		for (int i = 0; i <= 8; i++) {
			switch(i){
			case 0: currPos = myPos.getNNode(); break;
			case 1: currPos = myPos.getSNode(); break;
			case 2: currPos = myPos.getENode(); break;
			case 3: currPos = myPos.getWNode(); break;
			case 4: currPos = myPos.getNENode(); break;
			case 5: currPos = myPos.getSWNode(); break;
			case 6: currPos = myPos.getSENode(); break;
			case 7: currPos = myPos.getNWNode();break;
			}
			if(currPos != null){
				if(Math.abs(costMap[currPos.getY()][currPos.getX()]) == cost-1){
					return traceBack(new Point(currPos.getX(), currPos.getY()), costMap);
				}
			}
		}
		return null; //error, no Path found
	}
}


