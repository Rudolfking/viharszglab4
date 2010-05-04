package gui;

import java.awt.*;
/**
 * A STOP tábla kirajzolásáért felelõs.
 */
public class StopSignDrawer extends SignDrawer {
	
	StopSignDrawer(ISign s, Cell c) {
		super(s,c);
	}	
	/**
	 * A draw függvény rajzolja ki a táblát.
	 * @param g a Graphics-re.
	 */
	public void draw(Graphics g) {
				
		g.translate(x,y);
		g.setColor(Color.gray);
		g.drawLine(0,0,0,15);
		g.drawLine(-1,0,-1,15);
		g.drawLine(1,0,1,15);
		g.setColor(Color.red);
		g.fillOval(-5,-5,10,10);				
		g.translate(-x,-y);
	}
	/**
	 * Frissítés, ha szükséges
	 * @param g Graphics.
	 */
	public void refresh(Graphics g) {
	}
}
