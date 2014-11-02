package display;

import gameEngine.Graph;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import api.GameState;

public class Display {
	private Renderer renderer;
	private GamePanel panel;
	GameWindow gameFrame;
	public Display(String mapName, String firstAIName, String difficulty, boolean is2Player,Graph map) {
		//renderer really only needs the dimensions, but it currently calculates this from map
		//Maybe we could fix this later, if we ever make the tiles a dynamic size?
		gameFrame = new GameWindow(mapName,firstAIName,difficulty, is2Player, map.getRows(), map.getCols());
		gameFrame.setVisible(true);
		panel = gameFrame.getGamePanel();
		renderer = new Renderer(map);
	}
	
	public void render(GameState state){
		panel.setCurrFrame(renderer.generateImage(state));
		panel.paintNewFrame();
		System.out.println("Repainted");
	}
}
