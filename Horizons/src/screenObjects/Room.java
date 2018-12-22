package screenObjects;

import java.awt.Graphics2D;

public class Room implements ScreenObjectifiable {
	int x;
	int y;
	int w;
	int h;
	int t;
	
	public Room(int a, int b, int c, int d, int e) {
		x=a;
		y=b;
		w=c;
		h=d;
		t=e;
	}
	
	@Override
	public ScreenObject<Room> toScreenObject() {
		return new ScreenObject<Room>() {

			@Override
			public void paintInstructions(Graphics2D g1, int xdisp, int ydisp) {
				int x1 = x-xdisp;
				int y1 = y-ydisp;
				g1.fillRect(x1,y1, t, h);
				g1.fillRect(x1+w-t,y1, t, h);
				g1.fillRect(x1+t,y1, w-2*t, t);
				g1.fillRect(x1+t,y1+h-t, w-2*t, t);
				
			}

			@Override
			public <U> boolean collidesWith(ScreenObject<U> obj) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
	}

}
