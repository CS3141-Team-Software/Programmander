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
		g = nextFrame.createGraphics();

		//These are the size of our bufferedImage.
		xDim = map.getCols() * 50;
		yDim = map.getRows() * 50;

	}

	//Paint image into the buffered image
	private void generateImage() {
		currFrame = nextFrame;
		Image bg = null;
		Image unit = null;
		int xCoord = 0;
		int yCoord = 0;
		Actor actor;
		for (GraphNode node : nodes) {
			
			if (node.getIsChanged()) {
				//Update the node
				bg = getFromCache(node.getBackground());
				g.drawImage(bg, xCoord, yCoord, null);
				
				actor = node.getActor();
				if (actor != null) {
					unit = getFromCache(actor.getType());
					g.drawImage(unit, xCoord, yCoord, null);
				}
				
			}
			xCoord = xCoord + 50;
			
			if (xCoord >= xDim) {
				xCoord = 0;
				yCoord = yCoord + 50;
			}
		}
	}
	
	//private int[] getNodePositionCoords(GraphNode n) {
	//	int[] position = new int[2];
		
	//}
}
