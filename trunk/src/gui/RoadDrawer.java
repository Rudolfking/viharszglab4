package gui;

import java.awt.*;

public class RoadDrawer implements IDrawer {
	
	final int width = 20;
	final int hw = width / 2;	
	
	private Road road;
	
	RoadDrawer(Road road) {
		
		this.road = road;		
	}
	
	public void draw(Graphics g) {		
		
		g.setColor(Color.gray);
		
		double x1 = road.getEntryIntersection().getDrawer().X();
		double y1 = road.getEntryIntersection().getDrawer().Y();
		double x2 = road.getExitIntersection().getDrawer().X();
		double y2 = road.getExitIntersection().getDrawer().Y();		
		double length = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		double dx = (x2 - x1) / length;
		double dy = (y2 - y1) / length;
		
		Polygon p = new Polygon();
		p.addPoint((int)(x1+dy*hw),(int)(y1-dx*hw));
		p.addPoint((int)(x1-dy*hw),(int)(y1+dx*hw));		
		p.addPoint((int)(x2-dy*hw),(int)(y2+dx*hw));
		p.addPoint((int)(x2+dy*hw),(int)(y2-dx*hw));		
		
		g.fillPolygon(p);	
		
		g.setColor(Color.white);
		
		int nCells = road.getCells().length;
		for(int i=0; i<nCells; i++) {
			int cx = (int)(x1+dx*(i+1)/(nCells+1)*length);
			int cy = (int)(y1+dy*(i+1)/(nCells+1)*length);
			g.drawLine(cx+(int)(+dx*3),cy+(int)(+dy*3),cx+(int)(-dy*3-dx*3),cy+(int)(+dx*3-dy*3));
			g.drawLine(cx+(int)(+dx*3),cy+(int)(+dy*3),cx+(int)(+dy*3-dx*3),cy+(int)(-dx*3-dy*3));
		}
	}
	
	public void refresh(Graphics g) {
	}
}
