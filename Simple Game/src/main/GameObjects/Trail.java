package main.GameObjects;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.ID;
import main.GameObjects.AbstractClasses.GameObject;
import main.visuals.Camera;

public class Trail extends GameObject{

	private float alpha = 0.6f;
	private float reduction;
	private BufferedImage image;
	
	public Trail(double x, double y, BufferedImage image, float reduction, Camera camera) {
		super(x, y, ID.Trail, camera);
		this.image = image;
		this.reduction = reduction;
	}
	
	public void tick() {
		alpha -= reduction;
		alpha = main.Game.clamp(alpha,0f,1f);
	}
	
	public void render(Graphics g) {
		java.awt.Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(makeTransparent(alpha));
		g.drawImage(image,(int)(x-camera.getX()),(int)(y-camera.getY()),null);
		g2d.setComposite(makeTransparent(1.0f));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	
	public boolean markedForDeletion() {
		return alpha < 0.1f;
	}

}
