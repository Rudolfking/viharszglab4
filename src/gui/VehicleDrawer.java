package gui;

import java.awt.*;
/**
 * Egy általános jármû kirajzolásárt felelõs.
 */
public class VehicleDrawer implements IDrawer {
	
	final int carLength = 20;
	final int carWidth = 10;
	
	protected Vehicle vehicle;	
	protected Cell prevCell;
	protected Color color;
	protected Color borderColor;
	protected double dx;
	protected double dy;
	protected boolean erasing;	
	/**
	 * Konstruktor: beállítja a szükséges állapotokat.
	 * @param v a jármû referenciája.
	 */
	/**
	 * Konstruktor a rajzolandÃ³ kocsi megadÃ¡sÃ¡val.
	 * @param v a rajzolandÃ³ kocsi
	 */ 
	VehicleDrawer(Vehicle v) {
		
		vehicle = v;		
		prevCell = vehicle.getCell();
		color = Color.black;
		borderColor = Color.gray;
		dx = 1;
		dy = 0;
		erasing = false;
	}	
	/**
	 * Kirajzolás
	 * @param g grafikus felületre rajzolunk.
	 */
	public void draw(Graphics g) {
		
		if(prevCell == null)
			return;
	
		double x;
		double y;		
		
		if(prevCell.getRoad()==null) {
			x = ((Intersection)(prevCell)).getDrawer().X();
			y = ((Intersection)(prevCell)).getDrawer().Y();						
		} else {
			Road road = prevCell.getRoad();
			
			double x1 = road.getEntryIntersection().getDrawer().X();
			double y1 = road.getEntryIntersection().getDrawer().Y();
			double x2 = road.getExitIntersection().getDrawer().X();
			double y2 = road.getExitIntersection().getDrawer().Y();		
			double length = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
			dx = (x2 - x1) / length;
			dy = (y2 - y1) / length;
			
			int i = 0;
			while(road.getCells()[i] != prevCell)
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
		
		g.setColor(erasing?Color.gray:color);
		g.fillPolygon(p);
		g.setColor(erasing?Color.gray:borderColor);
		g.drawPolygon(p);
		
		Polygon p3 = new Polygon();
		p3.addPoint((int)(x+dx*carLength/6+dy*carWidth/2),(int)(y+dy*carLength/6-dx*carWidth/2));
		p3.addPoint((int)(x+dx*carLength/6-dy*carWidth/2),(int)(y+dy*carLength/6+dx*carWidth/2));
		p3.addPoint((int)(x-dx*carLength/4-dy*carWidth/2),(int)(y-dy*carLength/4+dx*carWidth/2));
		p3.addPoint((int)(x-dx*carLength/4+dy*carWidth/2),(int)(y-dy*carLength/4-dx*carWidth/2));
		g.drawPolygon(p3);
		
		g.setColor(erasing?Color.gray:Color.yellow);
		g.fillOval(((int)(x+dx*carLength/2+dy*carWidth*0.35))-1,((int)(y+dy*carLength/2-dx*carWidth*0.35))-2,4,4);
		g.fillOval(((int)(x+dx*carLength/2-dy*carWidth*0.35))-1,((int)(y+dy*carLength/2+dx*carWidth*0.35))-2,4,4);
		g.setColor(erasing?Color.gray:Color.red);
		g.fillOval(((int)(x-dx*carLength/2-dy*carWidth*0.35))-1,((int)(y-dy*carLength/2+dx*carWidth*0.35))-2,4,4);
		g.fillOval(((int)(x-dx*carLength/2+dy*carWidth*0.35))-1,((int)(y-dy*carLength/2-dx*carWidth*0.35))-2,4,4);
		
		if (erasing && (prevCell.getRoad() != null)) {
			g.setColor(Color.white);
			g.drawLine((int)(x+dx*3),(int)(y+dy*3),(int)(x-dy*3-dx*3),(int)(y+dx*3-dy*3));
			g.drawLine((int)(x+dx*3),(int)(y+dy*3),(int)(x+dy*3-dx*3),(int)(y-dx*3-dy*3));
		}
	}
	/**
	 * Frissítés: ha kell, frissítjük a megjelenõ jármûvet, ha nem, nem.
	 * @param g a Graphics objektum
	 */
	public void refresh(Graphics g) {
		if(vehicle.getCell() != prevCell) {			
			
			erasing = true;
			draw(g);
			erasing = false;
			
			prevCell = vehicle.getCell();			
			draw(g);
		}
	}
}
