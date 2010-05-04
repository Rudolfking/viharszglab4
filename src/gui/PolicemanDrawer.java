package gui;

import java.awt.*;
/**
 * A rend�rt rajzolja ki a megfelel� helyre.
 */
public class PolicemanDrawer extends VehicleDrawer {
	
	/**
	 * A konstruktor r�gz�ti a j�rm�vet. Kirajzolja a rend�rt, m�s sz�nnel.
	 * @param v ezt.
	 */
	PolicemanDrawer(Vehicle v) {
		
		super(v);		
		color = new Color(128,128,255);
		borderColor = Color.white;
	}
}
