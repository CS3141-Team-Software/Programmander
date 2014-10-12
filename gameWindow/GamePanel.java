package gameWindow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	//Variables for dimensions of this panel
	private Integer originXPos = 0;
	private Integer originYPos = 0;
	private Integer panelWidth = 950;
	private Integer panelHeight = 950;
	private Graphics2D g;
	private BufferedImage currFrame;
	
	/**
	 * Constructor that accepts no variables.
	 * @throws IOException
	 */
	public GamePanel() throws IOException{
		initializePanel();
	}
	
	/**
	 * This constructor takes the dimensions of the panel and the starting x and y values.
	 * @param originX
	 * @param originY
	 * @param panelWidth
	 * @param panelHeight
	 * @throws IOException 
	 */
	public GamePanel(Integer originX, Integer originY,Integer panelWidth, Integer panelHeight) throws IOException{
		this.originXPos = originX;
		this.originYPos = originY;
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
		g = (Graphics2D) this.getGraphics();
		currFrame = null;
		initializePanel();
	}
	
	/**
	 * Method to initialize things inside the panel
	 * @throws IOException
	 */
	private void initializePanel() throws IOException {
		setBounds(new Rectangle(panelWidth, panelHeight));
	}
	
	public void setCurrFrame(BufferedImage i) {
		currFrame = i;
	}
	/**
	 * Method used to paint things to the panel such as background.
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(currFrame, null, 0, 0);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(0, 0, 200, 200);
	}	
}
