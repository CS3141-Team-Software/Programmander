package launcherWindow;


public class Programmander {

	public static void main(String[] args) throws Exception {
		
		MainWindow main = new MainWindow();
		main.pack();
		main.setVisible(true);
		
		LauncherWindow launcher = new LauncherWindow();
		launcher.pack();
		launcher.setVisible(false);
	}
}

