package main.States.Options;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import main.Adapters.Buttons.ButtonCast;
import main.Adapters.Buttons.SimpleButton;

public abstract class OptionsPage {
	
	protected LinkedList<SimpleButton> buttons = new LinkedList<SimpleButton>();
	protected LinkedList<ButtonCast> casts = new LinkedList<ButtonCast>();
	
	public abstract void init();
	
	public abstract void tick();
	
	public void render(Graphics g) {
		renderButtons(g);
	}
	
	public LinkedList<SimpleButton> getButtons() {
		return buttons;
	}
	
	public void mousePressed(MouseEvent e) {
		for(SimpleButton cur : buttons) {
			cur.mousePressed(e);
		}
	}
	
	public void renderButtons(Graphics g)  {
		for(SimpleButton cur : buttons) {
			cur.render(g);
		}
	}
}
