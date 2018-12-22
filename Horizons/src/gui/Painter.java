package gui;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import screenObjects.ScreenObject;
import screenObjects.ScreenObjectifiable;

public class Painter {
	ArrayList<ScreenObject<?>>[] loaded; 
	ArrayList<ScreenObject<?>>[] unloaded;
	public int xdisp;
	public int ydisp;
	public static final int PRIORITY_NUMBER = 10;
	
	@SuppressWarnings("unchecked")
	public Painter() {
		loaded = (ArrayList<ScreenObject<?>>[])new ArrayList[PRIORITY_NUMBER]; //change 10 to number of priority layers
		unloaded = (ArrayList<ScreenObject<?>>[])new ArrayList[PRIORITY_NUMBER];
		for(int i = 0; i < PRIORITY_NUMBER; i++) loaded[i] = new ArrayList<>();
		for(int i = 0; i < PRIORITY_NUMBER; i++) unloaded[i] = new ArrayList<>();
		xdisp=0;
		ydisp=0;
	}
	
	public void drawObjects(Graphics2D g2, BufferedImage image, int sf) {
		Graphics2D g1 = image.createGraphics();
		g1.clearRect(0, 0, image.getWidth(), image.getWidth());
		for(ArrayList<ScreenObject<?>> a : loaded) for(ScreenObject<?> so : a) so.paintInstructions(g1, xdisp, ydisp);
		g2.drawImage(image, (int)(-(1-1d/sf)*image.getWidth()/2),(int) (-(1-1d/sf)*image.getHeight()/2), image.getWidth(), image.getHeight(), null);
		g1.dispose();
	}


	public void addScreenObject(ScreenObjectifiable sf) {
		loaded[0].add(sf.toScreenObject());
	}
}
