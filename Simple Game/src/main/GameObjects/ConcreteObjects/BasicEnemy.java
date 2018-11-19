package main.GameObjects.ConcreteObjects;

import java.awt.Graphics;
import java.util.Random;

import main.ID;
import main.GameObjects.AbstractClasses.Enemy;
import main.visuals.Camera;

public class BasicEnemy extends Enemy{

	private java.util.Random rndm;
	
	
	
	
	
	public BasicEnemy(double x, double y, ID id, Random rndm, Camera camera) {
		super(x, y, id, camera, 10, 50, 16000);
		this.rndm = rndm;
		velX = randomSpeed(1,3);
		velY = randomSpeed(1,3);
		lengthX = 40;
		lengthY = 40;
		color = java.awt.Color.red;
		
		//visible = false;
		//visibleBounds = true;
	}
	
	
	
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		bounce();
	}
	
	
	
	public double randomSpeed(int minSpeed, int maxSpeed) {
		int speed = rndm.nextInt(maxSpeed - minSpeed + 1) + minSpeed;
		if(rndm.nextBoolean()) {
			speed *= -1;
		}
		return (double)speed;
	}
	
	
	
	@Override
	public void render(Graphics g) {
		if(visible)
			renderRelativeToCamera(g);
		if(visibleBounds)
			drawBoundsRelativeToCamera(g);
	}
	
	
}
