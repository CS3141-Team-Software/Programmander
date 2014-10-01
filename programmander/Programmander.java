package programmander;

import gui.LauncherWindow;

public class Programmander {

	public static void main(String[] args) throws Exception {

		System.out.println("Our game is fuckin' sweet.");
		LauncherWindow launcher = new LauncherWindow();
		launcher.pack();
		launcher.setVisible(true);
	}
}

