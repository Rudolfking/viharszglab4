package gui;

import java.awt.*;

/**
 * Civil kocsit rajzolni képes osztály.
 */
public class CivilCarDrawer extends VehicleDrawer {
	
	/**
	 * Konstruktor a rajzolandó kocsi megadásával.
	 * @param v a rajzolandó kocsi
	 */
	CivilCarDrawer(Vehicle v) {
		
		super(v);		
		color = Color.white;
		borderColor = Color.black;
	}
}
