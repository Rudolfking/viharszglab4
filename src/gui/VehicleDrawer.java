package gui;

import java.awt.*;

public class VehicleDrawer implements IDrawer {
	
	final int carLength = 20;
	final int carWidth = 10;
	
	protected Vehicle vehicle;
	private Cell prevCell;
	protected Color color;
	protected Color borderColor;
	protected double dx;
	protected double dy;
	
	VehicleDrawer(Vehicle v) {
		
		vehicle = v;
		prevCell = null;
		color = Color.black;
		borderColor = Color.gray;
		dx = 1;
		dy = 0;
	}	
	
	public void draw(Graphics g) {
		
		if(vehicle.getCell() == null)
			return;
	
		double x;
		double y;		
		
		if(vehicle.getCell().getRoad()==null) {
			x = ((Intersection)(vehicle.getCell())).getDrawer().X();
			y = ((Intersection)(vehicle.getCell())).getDrawer().Y();						
		} else {
			Road road = vehicle.getCell().getRoad();
			
			double x1 = road.getEntryIntersection().getDrawer().X();
			double y1 = road.getEntryIntersection().getDrawer().Y();
			double x2 = road.getExitIntersection().getDrawer().X();
			double y2 = road.getExitIntersection().getDrawer().Y();		
			double length = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
			dx = (x2 - x1) / length;
			dy = (y2 - y1) / length;
			
			int i = 0;
			while(road.getCells()[i] != vehicle.getCell())
				i++;
				
			int nCells = road.getCells().length;
			
			x = x1+dx*(i+1)/(nCells+1)*length;
			y = y1+dy*(i+1)/(nCells+1)*length;
		}		
		
		Polygon p = new Polygon();
		p.addPoint((int)(x+dx*carLength/2+dy*carWidth/2),(int)(y+dy*carLength/2-dx*carWidth/2));
		p.addPoint((int)(x+dx*carLength/2-dy*carWidth/2),(int)(y+dy*carLength/2+dx*carWidth/2));
		p.addPoint((int)(x-dx*carLength/2-dy*carWidth/2),(int)(y-dy*carLength/2+dx*carWidth/2));
		p.addPoint((int)(x-dx*carLength/2+dy*carWidth/2),(int)(y-dy*carLength/2-dx*carWidth/2));			
		
		g.setColor(color);
		g.fillPolygon(p);
		g.setColor(borderColor);
		g.drawPolygon(p);
		
		Polygon p3 = new Polygon();
		p3.addPoint((int)(x+dx*carLength/6+dy*carWidth/2),(int)(y+dy*carLength/6-dx*carWidth/2));
		p3.addPoint((int)(x+dx*carLength/6-dy*carWidth/2),(int)(y+dy*carLength/6+dx*carWidth/2));
		p3.addPoint((int)(x-dx*carLength/4-dy*carWidth/2),(int)(y-dy*carLength/4+dx*carWidth/2));
		p3.addPoint((int)(x-dx*carLength/4+dy*carWidth/2),(int)(y-dy*carLength/4-dx*carWidth/2));
		g.drawPolygon(p3);
		
		g.setColor(Color.yellow);
		g.fillOval(((int)(x+dx*carLength/2+dy*carWidth*0.35))-1,((int)(y+dy*carLength/2-dx*carWidth*0.35))-2,4,4);
		g.fillOval(((int)(x+dx*carLength/2-dy*carWidth*0.35))-1,((int)(y+dy*carLength/2+dx*carWidth*0.35))-2,4,4);
		g.setColor(Color.red);
		g.fillOval(((int)(x-dx*carLength/2-dy*carWidth*0.35))-1,((int)(y-dy*carLength/2+dx*carWidth*0.35))-2,4,4);
		g.fillOval(((int)(x-dx*carLength/2+dy*carWidth*0.35))-1,((int)(y-dy*carLength/2-dx*carWidth*0.35))-2,4,4);
	}
	
	public void refresh(Graphics g) {
	}
}
