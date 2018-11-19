package main.GameObjects.ConcreteObjects;

import java.awt.Graphics;

import main.ID;
import main.GameObjects.AbstractClasses.PhysicalObject;
import main.visuals.Camera;

public class Bullet extends PhysicalObject{
	
	
	public Bullet(double x, double y, double velX, double velY, int lengthX, int lengthY, Camera camera, int damage) {
		super(x, y, ID.Bullet, camera, 0, damage);
		
		this.velX = velX;
		this.velY = velY;
		
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		
		//visible = false;
		//visibleBounds = true;
		
		color = java.awt.Color.green;
	}
	
	@Override
	public void tick() {
	
		x += velX;
		y += velY;
		
	}
	
	@Override
	public void render(Graphics g) {
		if(visible) {
			g.setColor(color);
			g.fillRect((int)(x-camera.getX()),(int)(y-camera.getY()),lengthX,lengthY);
		}
		if(visibleBounds) {
			drawBoundsRelativeToCamera(g);
		}
	}
	
	@Override
	public boolean markedForDeletion() {
		return marked || outOfCameraBoundsX() || outOfCameraBoundsY();
	}
	
}
