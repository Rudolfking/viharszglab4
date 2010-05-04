package gui;

import java.awt.*;
/**
 * Egy elágazás (intersection) kirajzolásáért felelõs.
 */
public class IntersectionDrawer implements IDrawer {
	
	protected Intersection intersection;
	private int x;
	private int y;	
	protected Color borderColor;
	/**
	 * Konstruktor: koordináták beállítása
	 * @param i a keresztezõdés
	 * @param x az x koordináta
	 * @param y az y koordináta
	 */
	IntersectionDrawer(Intersection i, int x, int y) {		
		
		intersection = i;	
		intersection.setDrawer(this);	
		this.x = x;
		this.y = y;
		borderColor = Color.yellow;		
	}
	/**
	 * Koordináták rögzítése x és y alapján
	 */
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Egy getX
	 */
	public int X() {
		return x;
	}
	/**
	 * Egy getY
	 */
	public int Y() {
		
		return y;
	}
	/**
	 * Egy kirajzoló függvény
	 * @param g a grafikus felület, amire rajzolja a keresztezõdést
	 */
	public void draw(Graphics g) {
				
		g.translate(x,y);
		g.setColor(Color.gray);
		g.fillOval(-20,-20,40,40);
		g.setColor(borderColor);
		g.drawOval(-20,-20,40,40);
		g.drawOval(-19,-19,38,38);
		g.drawOval(-18,-18,36,36);
		g.translate(-x,-y);
	}
	/**
	 * Egy frissítés, ha kell
	 * @param g a grafikus felület, amire rajzolja a keresztezõdést
	 */
	public void refresh(Graphics g) {
	}
}
