package gui;

import java.awt.*;
/**
 * A közlekedési lámpa kirajzolásáért felelõs.
 */
public class TrafficLightDrawer extends SignDrawer {
	
	private int prevState;
	
	TrafficLightDrawer(ISign s, Cell c) {
		super(s,c);
		prevState = -1;
	}	
	/**
	 * A draw függvény rajzolja ki a lámpát.
	 * @param g méghozzá a graphics-re.
	 */
	public void draw(Graphics g) {		
		
		if (((TrafficLight)(sign)).isBlocking())
			prevState = 1;
		else
			prevState = 0;
		
		g.translate(x,y);
		g.setColor(Color.black);
		g.fillRect(-5,-5,11,21);
		if(prevState == 1)
			g.setColor(Color.red);
		else
			g.setColor(Color.gray);
		g.fillOval(-4,-4,8,8);
		if(prevState == 0)
			g.setColor(Color.green);
		else
			g.setColor(Color.gray);
		g.fillOval(-4,6,8,8);
		g.translate(-x,-y);
	}
	/**
	 * Frissítés, ha szükséges
	 * @param g méghozzá a graphics-re.
	 */
	public void refresh(Graphics g) {
		int currentState = ((TrafficLight)(sign)).isBlocking()? 1 : 0;
		if (currentState != prevState)
			draw(g);
	}
}
