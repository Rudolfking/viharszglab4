package gui;

import java.awt.*;

/**
 * Általános interfész a kirajzolható játékelemek számára.
 */ 
public interface IDrawer {
	
	/**
	 * Teljes újrarajzolás.
	 */ 
	public void draw(Graphics g);
	/**
	 * Újrarajzolás csak akkor, ha a tárolt információhoz képest változott a kirajzolandó objektum állapota.
	 */ 
	public void refresh(Graphics g);
}
