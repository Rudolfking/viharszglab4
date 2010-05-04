package gui;

import java.awt.*;
/**
 * Egy el�gaz�s (intersection) kirajzol�s��rt felel�s.
 */
public class IntersectionDrawer implements IDrawer {
	
	protected Intersection intersection;
	private int x;
	private int y;	
	protected Color borderColor;
	/**
	 * Konstruktor: koordin�t�k be�ll�t�sa
	 * @param i a keresztez�d�s
	 * @param x az x koordin�ta
	 * @param y az y koordin�ta
	 */
	IntersectionDrawer(Intersection i, int x, int y) {		
		
		intersection = i;	
		intersection.setDrawer(this);	
		this.x = x;
		this.y = y;
		borderColor = Color.yellow;		
	}
	/**
	 * Koordin�t�k r�gz�t�se x �s y alapj�n
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
	 * Egy kirajzol� f�ggv�ny
	 * @param g a grafikus fel�let, amire rajzolja a keresztez�d�st
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
	 * Egy friss�t�s, ha kell
	 * @param g a grafikus fel�let, amire rajzolja a keresztez�d�st
	 */
	public void refresh(Graphics g) {
	}
}
