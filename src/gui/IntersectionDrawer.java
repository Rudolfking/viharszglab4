package gui;

import java.awt.*;

public class IntersectionDrawer implements IDrawer {
	
	protected Intersection intersection;
	private int x;
	private int y;
	private Graphics graphics;
	protected Color borderColor;
	
	IntersectionDrawer(Intersection i, Graphics g, int x, int y) {
		intersection = i;
		graphics = g;
		this.x = x;
		this.y = y;
		borderColor = Color.white;
	}
	
	public int X() {
		return x;
	}
	
	public int Y() {
		
		return y;
	}
	
	public void draw() {
		
		graphics.translate(x,y);
		graphics.setColor(Color.gray);
		graphics.fillOval(-20,-20,40,40);
		graphics.setColor(borderColor);
		graphics.drawOval(-20,-20,40,40);
		graphics.translate(-x,-y);
	}
	
	public void refresh() {
	}
}
