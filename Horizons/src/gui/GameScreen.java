package gui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;
import javax.swing.JPanel;

import misc.Util;
import screenObjects.Room;


public class GameScreen extends JPanel {
	
	GameFrame gameframe;
	BufferedImage image;
	ActionHandler achandler;
	int scaleFactor;
	Painter painter;
	ScheduledExecutorService refresh;
	ScheduledFuture<?> frame;
	

	public GameScreen(GameFrame gf) {
		gameframe = gf;
		init();
		
	}
	
	@SuppressWarnings("static-access")
	public void init() {
		achandler = new ActionHandler(this);
		image = new BufferedImage(gameframe.getSize().width, gameframe.getSize().height, BufferedImage.TYPE_INT_ARGB);
		setPreferredSize(gameframe.getSize());
		setBackground(gameframe.BACKGROUND_COLOR);
		setForeground(gameframe.FOREGROUND_COLOR);
		setVisible(true);
		setFocusable(true);
		setDoubleBuffered(true);
		scaleFactor = 4;
		addKeybinds();
		painter = new Painter();
		painter.addScreenObject(new Room(600, 300, 100, 50, 1));
		ScheduledExecutorService refresh = Executors.newScheduledThreadPool(3);
		frame = refresh.scheduleWithFixedDelay(Util.guiRunnable(()->{
			repaint();
		}), 0, 16666667, TimeUnit.NANOSECONDS);
	}
	
	
	@Override
    public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//g2.translate(this.getWidth()/2, this.getHeight()/2);
		
		g2.scale(scaleFactor, scaleFactor);
		
		//g2.translate(-this.getWidth()/2, this.getHeight()/2);
		super.paintComponent(g2);
		painter.drawObjects(g2, image, scaleFactor);
		g2.dispose();
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void addKeybinds() {
		achandler.addKeyBind(KeyEvent.VK_T, "ZoomIn", u->{scaleFactor++;}, false);
		achandler.addKeyBind(KeyEvent.VK_G, "ZoomOut", u->{if(scaleFactor>1)scaleFactor--;}, false);
		achandler.addKeyBind(KeyEvent.VK_W, "Up", u->{painter.ydisp--;}, false);
		achandler.addKeyBind(KeyEvent.VK_S, "Down", u->{painter.ydisp++;}, false);
		achandler.addKeyBind(KeyEvent.VK_A, "Left", u->{painter.xdisp--;}, false);
		achandler.addKeyBind(KeyEvent.VK_D, "Right", u->{painter.xdisp++;}, false);
	}
}
