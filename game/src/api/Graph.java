package api;

import java.util.ArrayList;

import gameEngine.GraphNodeImplementation;

public interface Graph {
	public GraphNodeImplementation getNode(int x, int y);
	public GraphNodeImplementation[][] getNodes();
	public GraphNodeImplementation[] getNodesSing();
	public ArrayList<Actor> getActors();
	public int getRows();
	public int getCols();
}
