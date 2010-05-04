package gui;

import java.awt.*;
/**
 * A nyuszi kirajzol�s��rt felel.
 */
public class BunnyDrawer extends VehicleDrawer {
	/**
	 * Kirajzolja a nyuszit.
	 * @param v a kirajzoland� nyuszi.
	 */
	BunnyDrawer(Vehicle v) {
		
		super(v);		
		color = Color.white;
		borderColor = Color.black;
	}
	/**
	 * A kirajzol� f�ggv�ny �s a nyuszi alakja.
	 * @param g a Graphics, amire tudunk rajzolni
	 */
	public void draw(Graphics g) {
		
		if(vehicle.getCell() == null)
			return;
	
		int x;
		int y;		
		
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
			
			x = ((int)(x1+dx*(i+1)/(nCells+1)*length));
			y = ((int)(y1+dy*(i+1)/(nCells+1)*length));
		}		
		
		g.setColor(color);
		g.fillOval(x-4,y-4,8,8);
		g.setColor(borderColor);
		g.drawOval(x-4,y-4,8,8);
		
		g.setColor(color);
		g.fillOval(x-5,y-4,4,4);
		g.setColor(borderColor);
		g.drawOval(x-5,y-4,4,4);
		
		g.setColor(color);
		g.fillOval(x+1,y-4,4,4);
		g.setColor(borderColor);
		g.drawOval(x+1,y-4,4,4);		
	}
}
