package gui;

import java.awt.*;
/**
 * A bank kirajzol�s��rt felel�s.
 */
public class BankDrawer extends IntersectionDrawer {
	
	BankDrawer(Intersection i, int x, int y) {		
		
		super(i,x,y);		
		borderColor = Color.red;		
	}
}
