package gui;

import java.awt.*;
/**
 * A grafikus fel�let canvas-a, azaz a fel�let, ahova a p�lya ker�l
 */
/**
 * Vászon komponens, ami minden újrarajzoláskor kirajzol magára egy
 * csatolt Game objektumot, és üzenetek megjelenítésére is képes.
 */ 
public class GUICanvas extends Canvas {
	
	private Game game;
	private String message;	
	/**
	 * Konstruktor: j�t�k be�ll�t�sa
	 * @param game a j�t�k referenci�ja
	 */
	public GUICanvas(Game game) {
		this.game = game;		
	}
	/**
	 * J�t�k hozz�rendel�se
	 * @param game a j�t�k referenci�ja
	 */
	/**
	 * Csatolja a megadott játék objektumot. Ettől kezdve ezt rajzolja ki a vászonra. 
	 * Rögtön egy újrarajzolást is hív.
	 * @param game a csatolandó játék objektum
	 */ 
	public void setGame(Game g) {
		game = g;
		repaint();
	}
	
	/**
	 * Új üzenetet tárol el, és rögtön újra is rajzolja a komponenst.
	 * @param message a tárolandó üzenet
	 */ 
	public void displayMessage(String message) {
		
		this.message = message;
		repaint();
	}
	
	/**
	 * Törli az eltárolt üzenetet.
	 */ 
	public void clearMessage() {
		
		this.message = "";
	}
	
	/**
	 * A komponens paint metódusának felüldefiniálása: kirajzolja a game-et,
	 * és esetlegesen az eltárolt üzenetet.
	 */ 
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
