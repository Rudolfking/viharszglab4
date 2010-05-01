package gui;

import java.awt.*;

public class RobberDrawer extends VehicleDrawer {
	
	RobberDrawer(Vehicle v) {
		
		super(v);		
		color = new Color(255,64,64);
		borderColor = new Color(192,0,0);
	}
}
