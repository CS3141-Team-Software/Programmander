package launcherWindow;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindowPanel extends JPanel{
	
	
	ArrayList<JFrame> frames;
	public MainWindowPanel(ArrayList<JFrame> f) throws IOException{
		frames = f;
		initializePanel();
	}
	
	private void initializePanel() throws IOException {
		setBounds(new Rectangle(1720, 880));
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image background = null;
		try {
			background = ImageIO.read(new File(System.getProperty("user.dir") + "/src/assets/art/mainWindowBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2d.drawImage(background, 0, 0, null);
	}
}
