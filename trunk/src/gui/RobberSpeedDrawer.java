package gui;

import java.awt.*;

public class RobberSpeedDrawer implements IDrawer {
	
	private Robber robber;
	private int prevSpeedLevel;
	private int x;
	private int y;
	
	RobberSpeedDrawer(Robber robber, int x, int y) {
		this.robber = robber;
		prevSpeedLevel = -1;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		
		g.translate(x,y);
		g.setColor(Color.black);
		g.fillRect(-7,-27,14,54);
		g.setColor(Color.red);
		int speedLength = ((int)(50.0*((float)robber.getSpeedLevel()/((float)robber.getMaxSpeedLevel()-1))));
		g.fillRect(-5,25-speedLength,10,speedLength);
		g.translate(-x,-y);
	}
	
	public void refresh(Graphics g) {
		
		if (prevSpeedLevel != robber.getSpeedLevel()) {
			prevSpeedLevel = robber.getSpeedLevel();
			draw(g);
		}
	}
}
