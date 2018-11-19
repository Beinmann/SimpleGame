package main.Adapters.Buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import main.Adapters.Adapter;
import main.Adapters.MouseInput;

public class MyButton extends SimpleButton{
	
	private Color innerColor;
	private String text;
	private boolean pressed = false;
	private int xOffset, yOffset;
	private int edge;
	private Font fnt;

	public MyButton(int x, int y, int width, int height, int edge, int fontSize, Color outerColor, Color innerColor, String text, int xOffset, int yOffset) {
		super(x,y,width,height,outerColor);
		this.innerColor = innerColor;
		this.text = text;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		fnt = new Font("arial",1,fontSize);
		this.edge = edge;
	}
	
	@Override
	public void render(java.awt.Graphics g) {
		g.setColor(outerColor);
		g.fillRect(x,y,width,height);
		
		g.setColor(innerColor);
		g.fillRect(x+edge,y+edge,width-2*edge,height-2*edge);
		
		g.setColor(Color.black);
		g.setFont(fnt);
		g.drawString(text,x+(int)(width/3)+xOffset,y+(int)(height/1.5)+yOffset);
	}
	
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}	
}