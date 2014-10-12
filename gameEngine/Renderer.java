package gameEngine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.imageio.ImageIO;

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
		
		//These are the size of our bufferedImage.
		xDim = map.getCols() * 50;
		yDim = map.getRows() * 50;
		currFrame = null;
		nextFrame = new BufferedImage(xDim, yDim, BufferedImage.TYPE_INT_RGB);
		g = nextFrame.createGraphics();



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
					//unit = getFromCache(actor.getType());
					//g.drawImage(unit, xCoord, yCoord, null);
				}

			}
			xCoord = xCoord + 50;

			if (xCoord >= xDim) {
				xCoord = 0;
				yCoord = yCoord + 50;
			}
		}
	}

	/*
	 * getFromCache
	 * gets the matched image from the provided string
	 * if the image isn't found in the dictionary, then find
	 * it in the art folder
	 * 
	 * NOTE: IF THERE IS A PROBLEM WITH IMAGES, IT'LL BE HERE
	 */
	public Image getFromCache(String str){// str should be full file name (e.g. "image.jpg")

		if(cache.get(str).equals(null)){
			String dir = System.getProperty("user.dir") + "/assets/art";
			File artParent = new File(dir);

			if(artParent.isDirectory()){
				File[] fileList = artParent.listFiles();
				for(int i = 0; i < fileList.length; i++){
					if(fileList[i].getName().equals(str +".png")){
						Image img = null;

						try {
							img = ImageIO.read(fileList[i]);
						} catch (IOException e) {
							e.printStackTrace();
						}

						cache.put(str, img); 
						return img;
					}
				}
			}
			else{
				System.out.println("getFromCache: error with fetching art. Unacceptable directory path. Returning null.");
				return null;
			}
		}
		else{
			return cache.get(str);
		}
		return null; //I DON't KNOW WHY JAVA NEEDS THIS
	}

}
