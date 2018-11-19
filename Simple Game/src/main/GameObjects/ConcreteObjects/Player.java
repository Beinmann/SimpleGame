package main.GameObjects.ConcreteObjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import main.Game;
import main.ID;
import main.GameObjects.Trail;
import main.GameObjects.AbstractClasses.GameObject;
import main.States.Shop.Upgrade;
import main.backgroundScripts.Handler;
import main.visuals.Camera;
import main.visuals.sprites.BufferedImageLoader;

public class Player extends GameObject{

	private BufferedImage playerModel;
	private Handler handler;
	private int health;
	private long lastTimeShot = System.currentTimeMillis();
	private boolean[] keyDown = new boolean[8];
	private final int distanceToCameraX = 310, distanceToCameraY = 210;
	private Upgrade shotSpeedUpgrade = new Upgrade("Shot Speed","+",0,1,10,10), speedUpgrade = new Upgrade("Speed","*",1,0.1,100,100), shotSizeUpgrade = new Upgrade("Shot Size","+",0,1,100,100), shotDelayUpgrade = new Upgrade("Shot Delay","-",0,5,100,100), damageUpgrade = new Upgrade("Damage","+",0,1,100,100), healthUpgrade = new Upgrade("Health","+",0,1,100,100);
	
	private double xOffset = 0;
	private double yOffset = 0;

	public Player(int x, int y, ID id, Handler handler, int health, Camera camera, BufferedImage playerModel) {
		super(x,y,id,camera);
		this.health = health;
		lengthX = 20;
		lengthY = 20;
		this.handler = handler;
		color = java.awt.Color.blue;
		this.playerModel = playerModel;

		
		
		//visible = false;
		//visibleBounds = true;
	}

	@Override
	public void tick() {
		//x = Game.clamp(x + (velX*speedUpgrade),distanceToCameraX,camera.getMaxX()+distanceToCameraX);
		//y = Game.clamp(y + (velY*speedUpgrade),distanceToCameraY,camera.getMaxY()+distanceToCameraY);
		
		x += velX*speedUpgrade.getValue();
		y += velY*speedUpgrade.getValue();

		x = Game.clamp(x,Game.MIN_BOUND_X,Game.MAX_BOUND_X-lengthX);
		y = Game.clamp(y,Game.MIN_BOUND_Y,Game.MAX_BOUND_Y-lengthY);
		
		camera.setX(x-distanceToCameraX);
		camera.setY(y-distanceToCameraY);
		
		handler.addObject(new Trail(x,y,playerModel,0.05f, camera));
		
		//collision();
		
		if(action) {
			shoot();
		}
	}
	
	/*private void collision() {
		for(GameObject cur : handler.getObjectList()) {
			if(cur.getID() == ID.Enemy) {
				if(getBounds().intersects(cur.getBounds())) {
					health -= 2;
					health = Game.clamp(health,0,300);
				}
			}
		}
	}*/

	
	@Override
	public boolean markedForDeletion() {
		return health <= 0;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void subtractHealth(int damage) {
		this.health -= damage;
	}
	
	public void shoot() {
		long now = System.currentTimeMillis();
		if((now - lastTimeShot) > (300 - shotDelayUpgrade.getValue())) {
			
			double tempVelX = (velX/1.2) + (velX * 2 * (shotSpeedUpgrade.getValue()/30));
			double tempVelY = (velY/1.2) + (velY * 2 * (shotSpeedUpgrade.getValue()/30));
			
			if(keyDown[4]) {
				tempVelY = -7 - shotSpeedUpgrade.getValue() + (velY/10);
			} else if(keyDown[5]) {
				tempVelY = 7 + shotSpeedUpgrade.getValue() + (velX/10);
			} else if(keyDown[6]) {
				tempVelX = -7 - shotSpeedUpgrade.getValue() + (velX/10);
			} else if(keyDown[7]) {
				tempVelX = 7 + shotSpeedUpgrade.getValue() + (velX/10);
			}

			
			handler.addBullet(new Bullet(x+5-(shotSizeUpgrade.getValue()/2),y+5-(shotSizeUpgrade.getValue()/2),tempVelX,tempVelY, 10+(int)shotSizeUpgrade.getValue(), 10+(int)shotSizeUpgrade.getValue(), camera, 5+(int)damageUpgrade.getValue()));
			lastTimeShot = now;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) { setVelY(-5); keyDown[0] = true; }
		else if(key == KeyEvent.VK_S) { setVelY(5); keyDown[1] = true; }
		else if(key == KeyEvent.VK_A) { setVelX(-5); keyDown[2] = true; }
		else if(key == KeyEvent.VK_D) { setVelX(5); keyDown[3] = true; }
		else if(key == KeyEvent.VK_UP) { action = true; keyDown[4] = true; }
		else if(key == KeyEvent.VK_DOWN) { action = true; keyDown[5] = true; }
		else if(key == KeyEvent.VK_LEFT) { action = true; keyDown[6] = true; }
		else if(key == KeyEvent.VK_RIGHT) { action = true; keyDown[7] = true; }
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) keyDown[0] = false;
		else if(key == KeyEvent.VK_S) keyDown[1] = false;
		else if(key == KeyEvent.VK_A) keyDown[2] = false;
		else if(key == KeyEvent.VK_D) keyDown[3] = false;
		else if(key == KeyEvent.VK_UP) keyDown[4] = false;
		else if(key == KeyEvent.VK_DOWN) keyDown[5] = false;
		else if(key == KeyEvent.VK_LEFT) keyDown[6] = false;
		else if(key == KeyEvent.VK_RIGHT) keyDown[7] = false;
		
		if(!keyDown[4] && !keyDown[5] && !keyDown[6] && !keyDown[7]) {
			action = false;
		}
		
		if(!keyDown[0] && !keyDown[1]) {
			setVelY(0);
		}
		if(!keyDown[2] && !keyDown[3]) {
			setVelX(0);
		}
	
		
	}
	
	@Override
	public void render(Graphics g) {
		/*
		if(visible) {
			g.setColor(color);
			g.fillRect((int)(310+xOffset),(int)(210+yOffset),lengthX,lengthY);
		}
		if(visibleBounds) {
			g.setColor(java.awt.Color.green);
			g.drawRect((int)(310+xOffset),(int)(210+yOffset),lengthX,lengthY);
		}
		*/
		g.drawImage(playerModel,(int)(Game.MIN_BOUND_X+xOffset),(int)(Game.MIN_BOUND_Y+yOffset),null);
	}
	
	public Upgrade[] getUpgrades() {
		Upgrade[] temp = new Upgrade[6];
		temp[0] = this.shotSpeedUpgrade;
		temp[1] = this.speedUpgrade;
		temp[2] = this.shotSizeUpgrade;
		temp[3] = this.shotDelayUpgrade;
		temp[4] = this.damageUpgrade;
		temp[5] = this.healthUpgrade;
		return temp;
	}
}
