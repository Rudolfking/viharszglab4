package gui;

import java.awt.*;

public class BankDrawer extends IntersectionDrawer {
	
	BankDrawer(Intersection i, Graphics g, int x, int y) {
		super(i,g,x,y);		
		borderColor = Color.red;
	}
}
