package launcherWindow;

import gameWindow.GameWindow;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class displays the launcher window. 
 * The purpose of this class is to launch the game based on the 
 * information that the user has put into the forms. 
 * 
 * @author Nighthunter
 *
 */
public class LauncherWindow extends JFrame {
	
	JFrame mainFrameWindow;
	public LauncherWindow(JFrame main) {
		this.mainFrameWindow = main;
		
		//Set up the content pane definition.
		setMinimumSize(new Dimension(1720, 880));
		setMaximumSize(new Dimension(1720, 880));
		getContentPane().setMinimumSize(new Dimension(1720, 880));
		getContentPane().setMaximumSize(new Dimension(1720, 880));
		getContentPane().setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);		//Used to set to full width and height of the current screen
		
		JButton btnRunAi = new JButton("Run AI");
		btnRunAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameWindow gameFrame = new GameWindow();
				setVisible(false);
				gameFrame.setVisible(true);
			}
		});
		btnRunAi.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		btnRunAi.setBounds(777, 561, 518, 87);
		getContentPane().add(btnRunAi);
		
		JComboBox comboBoxMaps = new JComboBox();
		comboBoxMaps.setBounds(631, 261, 787, 45);
		getContentPane().add(comboBoxMaps);
		
		JLabel lblMaps = new JLabel("Maps");
		lblMaps.setFont(new Font("Arial Black", Font.BOLD, 50));
		lblMaps.setBounds(452, 261, 156, 50);
		getContentPane().add(lblMaps);
		
		JComboBox comboBoxUserAI = new JComboBox();
		comboBoxUserAI.setBounds(631, 380, 787, 50);
		getContentPane().add(comboBoxUserAI);
		
		JComboBox comboBoxDifficulty = new JComboBox();
		comboBoxDifficulty.setBounds(631, 318, 787, 50);
		getContentPane().add(comboBoxDifficulty);
		
		JLabel lblAi = new JLabel("Player 1 AI");
		lblAi.setFont(new Font("Arial Black", Font.BOLD, 50));
		lblAi.setBounds(300, 385, 308, 50);
		getContentPane().add(lblAi);
		
		JLabel lblNewLabel = new JLabel("Difficulty");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 50));
		lblNewLabel.setBounds(331, 323, 277, 50);
		getContentPane().add(lblNewLabel);
		
		JButton btnBackToMain = new JButton("Back To Main Window");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mainFrameWindow.setVisible(true);
			}
		});
		btnBackToMain.setBounds(10, 11, 141, 33);
		getContentPane().add(btnBackToMain);
		
		JLabel lblNewLabel_1 = new JLabel("Player 2 AI");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 50));
		lblNewLabel_1.setBounds(300, 447, 312, 57);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(631, 439, 787, 53);
		getContentPane().add(comboBox);
		
	}
	
	public void setMainFrame(JFrame main){
		this.mainFrameWindow = main;
	}
}
