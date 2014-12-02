package display;

import gameEngine.Graph;

import javax.swing.JOptionPane;

import api.GameState;

public class Display {
	private Renderer renderer;
	private GamePanel panel;
	GameWindow gameFrame;
	public Display(String mapName, String firstAIName, String difficulty,Graph map) {
		//renderer really only needs the dimensions, but it currently calculates this from map
		//Maybe we could fix this later, if we ever make the tiles a dynamic size?
		gameFrame = new GameWindow(mapName,firstAIName,difficulty, map.getRows(), map.getCols());
		gameFrame.setVisible(true);
		panel = gameFrame.getGamePanel();
		renderer = new Renderer(map);
	}

	public void render(GameState state) {
		//Sets the new image to render and repaints it
		panel.setCurrFrame(renderer.generateImage());
	}

	public void gameOver(boolean redTeamWon) {
		if(redTeamWon){
			JOptionPane.showMessageDialog(null,"RED TEAM WINS");
		} else {
			JOptionPane.showMessageDialog(null,"BLUE TEAM WINS");
		}
	}
}
