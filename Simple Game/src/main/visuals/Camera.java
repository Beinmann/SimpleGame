package main.visuals;

import main.Game;

public class Camera {

	private double x, y;
	
	public Camera() {
	}
	
	public void setX(double x) {
		this.x = Game.clamp(x,Game.MIN_BOUND_X-310,Game.MAX_BOUND_X-310);
	}
	
	public double getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y = Game.clamp(y,Game.MIN_BOUND_Y-210,Game.MAX_BOUND_Y-210);
	}
	
	public double getY() {
		return y;
	}
	
	public void addX(double velX) {
		this.x = Game.clamp(x+velX,Game.MIN_BOUND_X,Game.MAX_BOUND_X);
	}
	
	public void addY(double velY) {
		this.y = Game.clamp(y+velY,Game.MIN_BOUND_Y,Game.MAX_BOUND_Y);
	}
	
}
