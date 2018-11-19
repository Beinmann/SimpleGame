package main.GameObjects.AbstractClasses;

import main.ID;
import main.visuals.Camera;

public abstract class Enemy extends PhysicalObject {

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	protected int money;
	
	public Enemy(double x, double y, ID id, Camera camera, int health, int damage, int money) {
		super(x,y,id,camera, health, damage);
		this.money = money;
	}
	
	
}
