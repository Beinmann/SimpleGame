package main.Adapters;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;




//Adapter können an KeyInput und MouseInput übergeben werden, da sie die Methoden key/mouse pressed/released beinhalten
//Diese Methoden müssen aber nicht zwingend etwas tun
//Alle States sind Adapter genauso wie alle Buttons



public abstract class Adapter {
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	
	//Testet ob die maus in einem bestimmten Rechteck liegt
	protected boolean mouseOver(MouseEvent e, int x, int y, int width, int height) {
		Rectangle rect1 = new Rectangle(e.getX(),e.getY(),1,1);
		Rectangle rect2 = new Rectangle(x, y, width, height);
		
		if(rect1.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
	}
}
