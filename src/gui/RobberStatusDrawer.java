package gui;

import java.awt.*;

/**
 * Bankrabló státuszinformációit (sebességét és GodMode állapotát) lekérdező és kirajzoló osztály.
 */ 
public class RobberStatusDrawer implements IDrawer {
	
	private Robber robber;
	private int prevSpeedLevel;
	private boolean prevGodMode;
	private int x;
	private int y;
	
	/**
	 * Konstruktor a vizsgálandó rabló, és a kirajzolás helye koordinátáinak megadásával.
	 * @param robber ennek a rablónak az adatait fogja lekérdezni
	 * @param x a kirajzolás helyének x koordinátája
	 * @param y a kirajzolás helyének y koordinátája
	 */ 
	RobberStatusDrawer(Robber robber, int x, int y) {
		this.robber = robber;
		prevSpeedLevel = -1;
		prevGodMode = false;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		
		g.translate(x,y);
		
		// sebességjelző csík
		g.setColor(Color.black);
		g.fillRect(-7,-27,14,54);
		g.setColor(Color.red);
		int speedLength = ((int)(50.0*((float)robber.getSpeedLevel()/((float)robber.getMaxSpeedLevel()-1))));
		g.fillRect(-5,25-speedLength,10,speedLength);
		
		// GodMode-ot jelző csillag
		Polygon p = new Polygon();
		p.addPoint(0,30);
		p.addPoint(2,38);
		p.addPoint(8,38);
		p.addPoint(4,42);
		p.addPoint(7,51);
		p.addPoint(0,45);
		p.addPoint(-7,51);
		p.addPoint(-4,42);
		p.addPoint(-8,38);
		p.addPoint(-2,38);
		
		if(robber.inGodMode())
			g.setColor(Color.yellow);
		else
			g.setColor(Color.green);
		g.fillPolygon(p);
		
		g.translate(-x,-y);
	}
	
	public void refresh(Graphics g) {
		
		if ((prevSpeedLevel != robber.getSpeedLevel()) || (prevGodMode != robber.inGodMode())) {
			prevSpeedLevel = robber.getSpeedLevel();
			prevGodMode = robber.inGodMode();
			draw(g);
		}
	}
}
