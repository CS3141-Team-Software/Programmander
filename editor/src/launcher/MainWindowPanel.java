package launcher;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindowPanel extends JPanel{
	
	ArrayList<JFrame> frames;
	private Integer panelWidth = 1920;
	private Integer panelHeight = 1080;
	
	public MainWindowPanel(ArrayList<JFrame> f) throws IOException{
		frames = f;
		initializePanel();
	}
	
	private void initializePanel() throws IOException {
		setBounds(new Rectangle(panelWidth, panelHeight));
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
//		BufferedImage imageFile = null;
//		try {
//			InputStream stream = getClass().getResourceAsStream("./assets/art/launcherWindowBackground.png");
//			imageFile = ImageIO.read(stream);
//			if (imageFile == null) {
//				throw new Exception();
//			}
//		} catch (Exception e){
//			e.printStackTrace();
//			System.err.println("Error loading map tile art ");
//			System.exit(1);
//		}
//		g2d.drawImage(imageFile, 0, 0, null); 
	}
}
