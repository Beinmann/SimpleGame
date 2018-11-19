package main.Adapters.Buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import main.Adapters.Adapter;
import main.Adapters.MouseInput;

public class SimpleButton {
	
	protected Color outerColor;
	protected boolean pressed = false;
	protected int x, y, width, height, xOffset, yOffset;
	
	private boolean comodification = false, read = false;

	public SimpleButton(int x, int y, int width, int height, Color outerColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.outerColor = outerColor;
	}
	
	public void render(java.awt.Graphics g) {
		g.setColor(outerColor);
		g.fillRect(x,y,width,height);
	}
	
	
	
	
	//testet, ob der Knopf gedrückt wurde und setzt seinen zustand zurück
	//außerdem wird verhindert dass 2 funktionen gleichzeitig auf den Knopf zugreifen
	public boolean pressed() {
		while(comodification) {
		}
		read = true;
		if(pressed) {
			pressed = false;
			read = false;
			return true;
		} else {
			read = false;
			return false;
		}
	}


	//wenn die maus gedrückt wurde und der mauszeiger im bereich des Knopfes liegt, wird pressed aus true gesetzt
	public void mousePressed(MouseEvent e) {
			while(read) {}
			comodification = true;
			if(mouseOver(e, x, y, width, height)) {
				pressed = true;
			}
			comodification = false;
	}
	
	public boolean mouseOver(MouseEvent e, int x, int y, int width, int height) {
		Rectangle rect1 = new Rectangle(e.getX(),e.getY(),1,1);
		Rectangle rect2 = new Rectangle(x, y, width, height);
		
		if(rect1.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//Getter und Setter
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}