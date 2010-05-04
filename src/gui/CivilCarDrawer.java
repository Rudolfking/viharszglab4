package gui;

import java.awt.*;
/**
 * A civil autó kirajzolásáért felelõs.
 */
public class CivilCarDrawer extends VehicleDrawer {
	
	/**
	* Kirajzolja a civil autót.
	* @param v a kirajzolandó jármû.
	*/
	CivilCarDrawer(Vehicle v) {
		
		super(v);		
		color = Color.white;
		borderColor = Color.black;
	}
}
