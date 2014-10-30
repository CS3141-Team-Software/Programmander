package launcher;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.JFrame;

import launcher.*;

public class Launcher {
	public static void main(String[] args) throws Exception {
		
		//Is initializeWindows
		LauncherWindow launcher = new LauncherWindow();
		launcher.pack();
		launcher.setVisible(true);
		
		

		
		/*
	    if(args.length == 0){
	    	System.err.println("Needs at least one argument, try 'ant run -Darg0=aiName'");
	    	System.exit(1);
	    }
	    System.out.println("Using ai: " + args[0]);
		
		File f = new File("ais/" + args[0] + "jar");
	    URLClassLoader urlCl = new URLClassLoader(new URL[] { f.toURI().toURL()},Spawner.class.getClassLoader());
	    Class<?> testAI = urlCl.loadClass("playerCode." + args[0]);
	    
	    Class<? extends Spawner> myAIClass = testAI.asSubclass(Spawner.class);
	    Spawner woo = myAIClass.newInstance();
	    woo.printSomething();
	    // */
	}
}
