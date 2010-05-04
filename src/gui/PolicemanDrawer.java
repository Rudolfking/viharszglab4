package gui;

import java.awt.*;
/**
 * A rendõrt rajzolja ki a megfelelõ helyre.
 */
public class PolicemanDrawer extends VehicleDrawer {
	
	/**
	 * A konstruktor rögzíti a jármûvet. Kirajzolja a rendõrt, más színnel.
	 * @param v ezt.
	 */
	PolicemanDrawer(Vehicle v) {
		
		super(v);		
		color = new Color(128,128,255);
		borderColor = Color.white;
	}
}
