package gui;

import java.awt.*;

public class IntersectionDrawer implements IDrawer {
	
	protected Intersection intersection;
	private int x;
	private int y;	
	protected Color borderColor;
	
	IntersectionDrawer(Intersection i, int x, int y) {		
		
		intersection = i;		
		this.x = x;
		this.y = y;
		borderColor = Color.yellow;		
	}
	
	public int X() {
		return x;
	}
	
	public int Y() {
		
		return y;
	}
	
	public void draw(Graphics g) {
		
		System.out.println("drawing intersection to Graphics " + g.hashCode() + " at (" + Integer.toString(x) + "," + Integer.toString(y) + ")");
		g.translate(x,y);
		g.setColor(Color.gray);
		g.fillOval(-20,-20,40,40);
		g.setColor(borderColor);
		g.drawOval(-20,-20,40,40);
		g.drawOval(-19,-19,38,38);
		g.drawOval(-18,-18,36,36);
		g.translate(-x,-y);
	}
	
	public void refresh(Graphics g) {
	}
}
