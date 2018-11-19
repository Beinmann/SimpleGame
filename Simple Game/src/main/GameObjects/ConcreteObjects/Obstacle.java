package main.GameObjects.ConcreteObjects;

import java.awt.Color;
import java.awt.Graphics;

import main.ID;
import main.GameObjects.AbstractClasses.GameObject;
import main.visuals.Camera;

public class Obstacle extends GameObject {

	public Obstacle(double x, double y, int width, int height, Color color, Camera camera) {
		super(x, y, ID.Obstacle, camera);
		this.x = x;
		this.y = y;
		lengthX = width;
		lengthY = height;
		this.color = color;
	}
	
	@Override
	public void render(Graphics g) {
		if(visible) {
			renderRelativeToCamera(g);
		}
		if(visibleBounds) {
			drawBoundsRelativeToCamera(g);
		}
	}
	
	@Override
	public void tick() {}

}
