package gameEngine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Dictionary;

public class Renderer {

	//Used to store image data for caching
	private Dictionary<String, Image> cache;
	//Used to paint our image to the GameWindow.
	private BufferedImage currFrame;
	private BufferedImage nextFrame;
	private int xDim, yDim;
	private Graphics2D g;
	private Graph map;
	private GraphNode[] nodes;
	private ArrayList<Actor> actors;

	public Renderer(ArrayList<Actor> a, Graph m, GraphNode[] n) {

		nodes = n;
		actors = a;
		map = m;
		currFrame = null;
		nextFrame = new BufferedImage(xDim, yDim, BufferedImage.TYPE_INT_RGB);

		//These are the size of our window.
		xDim = 950;
		yDim = 950;

	}

	//Paint image into the buffered image
	private void generateImage(BufferedImage image) {
		for (GraphNode node : nodes) {
			if (node.getIsChanged()) {
				//Update the node
			}
			//Get background data
			//Check cache for that image
			//Paint that image to nextFrame based on coordinates of that frame
			//Get actor data
			//Check cache for that image
			//Paint that image to nextFrame based on coordinates of that frame
		}
	}

}
