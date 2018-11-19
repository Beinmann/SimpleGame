package main.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;
import main.Adapters.KeyInput;
import main.Adapters.MouseInput;

public class ScaleTest extends State {
	
	private boolean firstPress = false, draw = false, delete = false;
	private int x, y, width, height;
	
	
	public ScaleTest(KeyInput keyInput, MouseInput mouseInput, Game game) {
		super(StateID.Test,keyInput,mouseInput,game);
		delete = true;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		if(delete)
		drawBackground(g);
		g.setColor(Color.blue);
		if(draw)
			g.fillRect(x,y,width,height);
	}

	@Override
	public int getObjects() {
		// TODO Auto-generated method stub
		return 0;
	}	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(!firstPress) {
			draw = false;
			
			x = e.getX();
			y = e.getY();
			
			firstPress = true;
			
		} else {
			width = e.getX() -x;
			height = e.getY() - y;
			
			if(width < 0) {
				width *= -1;
				x -= width;
			}
			if(height < 0) {
				height *= -1;
				y -= height;
			}
			
			System.out.println("(" + x + "," + y + ")   height: " + height + "   width: " + width);
			firstPress = false;
			draw = true;
		}
		if(e.getButton() == 1) {
			delete = true;
		} else {
			delete = false;
		}
	}

}
