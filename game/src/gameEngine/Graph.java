package gameEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import launcher.LauncherWindow;
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
					if (obstrct.equals("BlueCastle")) {
						map[posX][posY].setCastle("BlueCastle");
						blueCastle = map[posX][posY];
					} else if (obstrct.equals("RedCastle")) {
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
}	


