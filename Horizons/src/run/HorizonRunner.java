package run;


import javax.swing.JApplet;

import gui.GameFrame;


public class HorizonRunner extends JApplet {
	
	GameFrame gameFrame;
	public HorizonRunner() {
		gameFrame = new GameFrame();
	}
	
	public static void main(String[] args) {
		HorizonRunner hr = new HorizonRunner();
		java.awt.EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            hr.setVisible(true);
	        }
	    });
	}
	

}