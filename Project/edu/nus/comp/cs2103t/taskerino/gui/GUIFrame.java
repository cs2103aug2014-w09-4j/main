//@author A0113742N
package edu.nus.comp.cs2103t.taskerino.gui;


import javax.swing.JFrame;

/**
 * This class creates a frame for GUI with specified properties.
 */
@SuppressWarnings("serial")
public class GUIFrame extends JFrame {
	private static final String APP_NAME = "Taskerino";
	private static final int MAIN_FRAME_WIDTH = 1000;
	private static final int MAIN_FRAME_HEIGHT = 650;
	
	public GUIFrame() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setTitle(APP_NAME);
		
		GUIComponents components = new GUIComponents();
		this.setJMenuBar(components.getMenuBar());
		this.getContentPane().add(components.getContentPanel());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		components.focusInputTextField();
	}
}
