package screenObjects;
import java.awt.Graphics2D;
import java.awt.Shape;
public abstract class ScreenObject<T> {
	public T object;
	public Shape[] boxes;
	public int priority;
	
	public abstract void paintInstructions(Graphics2D g1, int xdisp, int ydisp);
	
	public abstract <U> boolean collidesWith(ScreenObject<U> obj);
	
	/*Area a = new Area(boxes[0]);
	a.intersect(new Area(obj.boxes[0]));
	return !a.isEmpty();*/
}
