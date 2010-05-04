package gui;

import java.awt.*;

/**
 * Rendőrautót rajzolni képes osztály.
 */ 
public class PolicemanDrawer extends VehicleDrawer {
	
	/**
	 * Konstruktor a rajzolandó kocsi megadásával.
	 * @param v a rajzolandó kocsi
	 */
	PolicemanDrawer(Vehicle v) {
		
		super(v);		
		color = new Color(128,128,255);
		borderColor = Color.white;
	}
}
