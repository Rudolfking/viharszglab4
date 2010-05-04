package gui;

import java.awt.*;
/**
 * A civil aut� kirajzol�s��rt felel�s.
 */
public class CivilCarDrawer extends VehicleDrawer {
	
	/**
	* Kirajzolja a civil aut�t.
	* @param v a kirajzoland� j�rm�.
	*/
	CivilCarDrawer(Vehicle v) {
		
		super(v);		
		color = Color.white;
		borderColor = Color.black;
	}
}
