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
					 if(fileList[i].getName().equals(str)){
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
