package launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author Nighthunter
 */
public class LauncherPanel extends JPanel {

	//Variables for dimensions of this panel
	private Integer originXPos = 0;
	private Integer originYPos = 0;
	private Integer panelWidth = 1920;
	private Integer panelHeight = 1080;

	/**
	 * Constructor that accepts no variables.
	 * @throws IOException
	 */
	public LauncherPanel() throws IOException{
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
	public LauncherPanel(Integer originX, Integer originY,Integer panelWidth, Integer panelHeight) throws IOException{
		this.originXPos = originX;
		this.originYPos = originY;
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
		initializePanel();
	}

	/**
	 * Method to initialize things inside the panel
	 * @throws IOException
	 */
	private void initializePanel() throws IOException {
		setBounds(new Rectangle(panelWidth, panelHeight));
	}

	/**
	 * Method used to paint things to the panel such as background.
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		paintPanelBackround(g2d);
	}

	/**
	 * Method to paint the background of the panel
	 * @param g
	 */
	public void paintPanelBackround(Graphics2D g){

		BufferedImage background = null;

		try {
			background = ImageIO.read(getClass().getResource("/assets/art/launcherWindowBackground.png").openStream());

		} catch (Exception e){
			e.printStackTrace();
			System.err.println("Error: Could not fetch file names");
			System.exit(1);
		}
		
		g.drawImage(background, 0, 0, null);
	}
}
