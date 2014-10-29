package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	//Used to be GameWindow, unsure why it was that when it's a panel.
	
	//Variables for dimensions of this panel
	private BufferedImage currFrame;
	private Point gameImageLocation;
	private Dimension gameImageDim;
	private Rectangle gameImageBounds;
	
	/**
	 * Default constructor
	 */
	public GamePanel() throws IOException{
	}
	
	/**
	 * Constructor that accepts no variables.
	 * @param gamePanelBounds 
	 * @param gamePanelLocation 
	 * @param gamePanelDim 
	 * @throws IOException
	 */
	public GamePanel(Dimension gamePanelDim, Point gamePanelLocation, Rectangle gamePanelBounds){
		gameImageDim = gamePanelDim;
		gameImageLocation = gamePanelLocation;
		gameImageBounds = gamePanelBounds;
		
		currFrame = null;
	}
	
	public void setCurrFrame(BufferedImage i) {
		currFrame = i;
	}
	/**
	 * Method used to paint things to the panel such as background.
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.drawImage(currFrame, null, (int)gameImageLocation.getX(), (int)gameImageLocation.getY());
	}	
}
