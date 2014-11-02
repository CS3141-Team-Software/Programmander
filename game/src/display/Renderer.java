package display;

import gameEngine.Graph;
import gameEngine.GraphNode;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import api.Actor;
import api.GameState;

public class Renderer {

	//Used to store image data for caching
	private TreeMap<String, Image> cache;
	//Used to paint our image to the GameWindow.
	private BufferedImage currFrame;
	private int xDim, yDim;
	private Graphics2D g;


	public Renderer(Graph map) {
		cache = new TreeMap<String, Image>();
		//These are the size of our bufferedImage.
		xDim = map.getCols() * 50;
		yDim = map.getRows() * 50;
		currFrame = new BufferedImage(xDim, yDim, BufferedImage.TYPE_INT_RGB);
		g = currFrame.createGraphics();
	}


	//Paint image into the buffered image
	public BufferedImage generateImage(GameState state) {
		Image bg = null;
		Image obstruction = null;
		Image unit = null;
		int xCoord = 0;
		int yCoord = 0;
		Actor actor;

		for (GraphNode node : state.getMap().getNodesSing()) {

			if (node.getIsChanged()) {
				//Update the node
				bg = getFromCache(node.getBackground());

				g.drawImage(bg, xCoord, yCoord, null);

				if (node.getObstruction() != null) {
					obstruction = getFromCache(node.getObstruction().getType());
					g.drawImage(obstruction, xCoord, yCoord, null);
				}

				actor = node.getActor();
				if (actor != null) {
					unit = getFromCache("scout");//actor.getType());
					g.drawImage(unit, xCoord, yCoord, null);
				}

				//THIS LINE MUST BE AT THE END OF THIS IF STATEMENT
				node.setIsChanged(false);
			}
			xCoord = xCoord + 50;

			if (xCoord >= this.xDim) {
				xCoord = 0;
				yCoord = yCoord + 50;
			}
		}
		return currFrame;
	}

	/*
	 * getFromCache
	 * gets the matched image from the provided string
	 * if the image isn't found in the dictionary, then find
	 * it in the art folder
	 * 
	 */
	public Image getFromCache(String str){
		if(cache.get(str) == null) {

			BufferedImage imageFile = null;
			str = str.toLowerCase();

			try {
				InputStream stream = getClass().getResourceAsStream("/assets/art/" + str + ".png");
				imageFile = ImageIO.read(stream);
				if (imageFile == null) {
					throw new Exception();
				}
			} catch (Exception e){
				e.printStackTrace();
				System.err.println("Error loading map tile art " + str);
				System.exit(1);
			}

			return imageFile;

		} else {
			return cache.get(str);
		}
	}

}
