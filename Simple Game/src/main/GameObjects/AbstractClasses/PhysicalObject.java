package main.GameObjects.AbstractClasses;

import main.ID;
import main.visuals.Camera;

public abstract class PhysicalObject extends GameObject {

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	protected int health;
	protected int damage;
	
	public PhysicalObject(double x, double y, ID id, Camera camera, int health, int damage) {
		super(x, y, id, camera);
		this.health = health;
		this.damage = damage;
	}
	
	public void Collision(PhysicalObject other) {
		this.health -= other.getDamage();
	}
	
	public void kill() {
		marked = true;
	}
	
	public void subtractHealth(int health) {
		this.health -= health;
	}
	
	@Override
	public boolean markedForDeletion() {
		return health <= 0 || marked;
	}

}
