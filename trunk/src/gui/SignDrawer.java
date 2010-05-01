package gui;

import java.awt.*;

public abstract class SignDrawer implements IDrawer {
	
	final int distFromCenter = 10;
	
	protected ISign sign;
	protected Cell cell;
	
	protected int x;
	protected int y;
	
	SignDrawer(ISign s, Cell c) {
		sign = s;
		cell = c;
		
		double x1 = cell.getRoad().getEntryIntersection().getDrawer().X();
		double y1 = cell.getRoad().getEntryIntersection().getDrawer().Y();
		double x2 = cell.getRoad().getExitIntersection().getDrawer().X();
		double y2 = cell.getRoad().getExitIntersection().getDrawer().Y();		
		double length = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		double dx = (x2 - x1) / length;
		double dy = (y2 - y1) / length;		
		int nCells = cell.getRoad().getCells().length;
		
		x = (int)(x2-dy*distFromCenter*2-dx*length/(nCells+1)/2);
		y = (int)(y2+dx*distFromCenter*2-dy*length/(nCells+1)/2);
	}
	
	public abstract void draw(Graphics g);		
	public abstract void refresh(Graphics g);
}
