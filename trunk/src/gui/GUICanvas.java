package gui;

import java.awt.*;

public class GUICanvas extends Canvas {
	
	private Game game;
	private String message;	
	
	public GUICanvas(Game game) {
		this.game = game;		
	}
	
	public void setGame(Game g) {
		game = g;
		repaint();
	}
	
	public void displayMessage(String message) {
		
		this.message = message;
		repaint();
	}
	
	public void clearMessage() {
		
		this.message = "";
	}
	
	public void paint(Graphics g) {
		
		Rectangle rect = getBounds();

		g.setColor(Color.green);
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		
		if (game != null)			
			game.draw(g);
			
		if(message.length()>0) {
			int cx = rect.x + rect.width / 2;
			int cy = rect.y + rect.height / 2;
			
			g.setColor(new Color(192,192,192));
			g.fillRect(rect.x,cy-20,rect.width,40);
			g.setColor(Color.black);
			g.drawRect(rect.x,cy-20,rect.width,40);
			g.drawString(message, cx - 4 * message.length(), cy + 4);
		}				
	}
}
