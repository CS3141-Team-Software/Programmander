package launcherWindow;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class LauncherWindow extends JFrame {
	public LauncherWindow() {
		setTitle("Programmander");
		getContentPane().setMaximumSize(new Dimension(1920, 1080));
		getContentPane().setMinimumSize(new Dimension(1920, 1080));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		setResizable(false);
		setSize(new Dimension(1920, 1080));
	}
}
