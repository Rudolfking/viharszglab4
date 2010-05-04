package gui;

import java.awt.*;
/**
 * A városhatár (belépés) kirajzolásáért felelõs.
 */
public class CityEntryDrawer extends IntersectionDrawer {
	
	CityEntryDrawer(Intersection i, int x, int y) {		
		
		super(i,x,y);		
		borderColor = Color.white;		
	}
}
