package gui;

import java.awt.*;

public class TrafficLightDrawer extends SignDrawer {
	
	private int prevState;
	
	TrafficLightDrawer(ISign s, Cell c) {
		super(s,c);
		prevState = -1;
	}	
	
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
	
	public void refresh(Graphics g) {
	}
}
