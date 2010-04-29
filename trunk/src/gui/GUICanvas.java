package gui;

import java.awt.*;

public class GUICanvas extends Canvas {
	
	Game game;
	
	public GUICanvas(Game game) {
		this.game = game;
	}
	
	public void paint(Graphics g) {
		
		Rectangle rect = getBounds();
		
		g.setColor(Color.green);
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		
		g.translate(rect.x,rect.y);
		game.draw(g);
		g.translate(-rect.x,-rect.y);
	}
}
