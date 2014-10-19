package gameEngine;

import java.awt.image.BufferedImage;

import gameWindow.GamePanel;
import gameWindow.GameFrame;
import api.GameState;

public class Display {
	private Renderer renderer;
	private GamePanel window;
	GameFrame gameFrame;
	public Display(String mapName, String firstAIName, String difficulty, boolean is2Player,Graph map) {
		//renderer really only needs the dimensions, but it currently calculates this from map
		//Maybe we could fix this later, if we ever make the tiles a dynamic size?
		renderer = new Renderer(map);
		gameFrame = new GameFrame(mapName,firstAIName,difficulty,is2Player);
		gameFrame.setVisible(true);
		window = gameFrame.getGamePanel();
	}
	
	public void render(GameState state){
		window.setCurrFrame(renderer.generateImage(state));
	}
}
