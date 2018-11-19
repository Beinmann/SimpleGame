package main.GameObjects.AbstractClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.Game;
import main.ID;
import main.Adapters.Adapter;
import main.visuals.Camera;

public abstract class GameObject extends Adapter{

	protected double x, y;
	protected ID id;
	protected double velX, velY;
	protected int lengthX = 0, lengthY = 0;
	protected boolean visible = true, visibleBounds = false;
	protected Color color = Color.WHITE;
	protected boolean action = false;
	protected boolean marked = false;
	protected Camera camera;
	
	public GameObject(double x, double y, ID id, Camera camera) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.camera = camera;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		clamp();
	}
	
	public void clamp() {
		x = Game.clamp(x,Game.MIN_BOUND_X,Game.MAX_BOUND_X-lengthX);
		y = Game.clamp(y,Game.MIN_BOUND_Y,Game.MAX_BOUND_Y-lengthY);
	}
	
	public void printCoordinates() {
		System.out.println("(" + x + "," + y + ")");
	}

	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	public double getX() { return x; }
	public double getY() { return y; }
	public void setID(ID id) { this.id = id; }
	public ID getID() { return id; }
	public void setVelX(double velX) { this.velX = velX; }
	public void setVelY(double velY) { this.velY = velY; }
	public double getVelX() { return velX; }
	public double getVelY() { return velY; }
	public void setLengthX(int lengthX) { this.lengthX = lengthX; }
	public int getLengthX() { return lengthX; }
	public void setLengthY(int lengthY) { this.lengthY = lengthY; }
	public int getLengthY() { return lengthY; }
	
	public boolean markedForDeletion() {
		return false;
	}
	
	public boolean outOfBoundsX() {
		return (x+lengthX < 0) || (x > Game.WIDTH);
	}
	
	public boolean outOfBoundsY() {
		return (y+lengthY < 0) || (y > Game.HEIGHT);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,lengthX,lengthY);
	}
	
	public Rectangle getBoundsRelativeToCamera() {
		return new Rectangle((int)(x-camera.getX()),(int)(y-camera.getY()),lengthX,lengthY);
	}
	
	public void render(Graphics g) {
		if(visible) {
			g.setColor(color);
			g.fillRect((int)x,(int)y,lengthX,lengthY);
		}
		if(visibleBounds) {
			drawBounds(g);
		}
	}
	
	public void renderRelativeToCamera(Graphics g) {
		if(visible) {
			g.setColor(color);
			g.fillRect((int)(x-camera.getX()),(int)(y-camera.getY()),lengthX,lengthY);
		}
		if(visibleBounds) {
			drawBoundsRelativeToCamera(g);
		}
	}
	
	public void drawBoundsRelativeToCamera(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.green);
		g2d.draw(getBoundsRelativeToCamera());
	}
	
	public void drawBounds(Graphics g) {
		java.awt.Graphics2D g2d = (Graphics2D)g;
		g.setColor(java.awt.Color.green);
		g2d.draw(getBounds());
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setVisibleBounds(boolean visibleBounds) {
		this.visibleBounds = visibleBounds;
	}
	
	public boolean getAction() { return action; }
	public void setAction(boolean action) { this.action = action; }
	
	public boolean getMarked() { return marked; }
	public void setMarked(boolean marked) { this.marked = marked; }

	public boolean outOfCameraBoundsX() {
		return (x+lengthX) < Game.MIN_BOUND_X || x > Game.MAX_BOUND_X;
	}
	
	public boolean outOfCameraBoundsY() {
		return (y+lengthY) < Game.MIN_BOUND_Y || y > Game.MAX_BOUND_Y;
	}
	
	public void bounce() {
		
		double upperDistance = y - Game.MIN_BOUND_Y;
		double lowerDistance = Game.MAX_BOUND_Y - lengthY - y;
		double leftDistance = x - Game.MIN_BOUND_X;
		double rightDistance = Game.MAX_BOUND_X -lengthX - x;
		
		
		if(upperDistance < 0) {
			y = Game.MIN_BOUND_Y - upperDistance;
			velY *= -1;
		} else if(lowerDistance < 0) {
			y = Game.MAX_BOUND_Y - lengthY + lowerDistance;
			velY *= -1;
		}
		
		if(rightDistance < 0) {
			x = Game.MAX_BOUND_X - lengthX + rightDistance;
			velX *= -1;
		} else if(leftDistance < 0) {
			x = Game.MIN_BOUND_X - leftDistance;
			velX *= -1;
		}
	}
}
