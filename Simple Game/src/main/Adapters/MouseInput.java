package main.Adapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;


//besitzt eine liste von Adaptern
//f�hrt die Methode mousePressed f�r alle Elemente der Liste aus, immer wenn eine Maustaste gedr�ckt wurde


public class MouseInput extends MouseAdapter {
	
	private LinkedList<Adapter> adapters = new LinkedList<Adapter>();

	public void add(Adapter object) {
		adapters.add(object);
	}
	
	public void remove(Adapter object) {
		adapters.remove(object);
	}
	
	
	
	
	
	public void mousePressed(MouseEvent e) {
		for(Adapter cur : adapters) {
			cur.mousePressed(e);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		for(Adapter cur : adapters) {
			cur.mouseReleased(e);
		}
	}
}
