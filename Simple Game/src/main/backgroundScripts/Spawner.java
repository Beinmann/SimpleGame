package main.backgroundScripts;

import java.awt.Rectangle;
import java.util.Random;

import main.Game;
import main.ID;
import main.GameObjects.ConcreteObjects.BasicEnemy;
import main.GameObjects.ConcreteObjects.Player;
import main.visuals.Camera;
import main.visuals.HUD;

public class Spawner {
	private Handler handler;
	private Random rndm;
	private HUD hud;
	private int lastLevel = 0;
	private boolean activated = true;
	private Player player;
	private Camera camera;
	
	
	
	
	
	public Spawner(Handler handler, HUD hud, Random rndm, Player player, Camera camera) {
		this.handler = handler;
		this.hud = hud;
		this.rndm = rndm;
		this.player = player;
		this.camera = camera;
	}
	
	
	
	
	//Immer wenn sich das Level vom Hud erhöht, spawnt die nächste Welle an gegnern
	public void tick() {
		if(activated) {
			if(lastLevel < hud.getLevel()) {
				int enemyNum = numberOfEnemies((double)hud.getLevel());
				lastLevel++;
				for(int i = 0; i < enemyNum; i++) {
					Rectangle randomCoordinates = randomCoordinatesNotIntersectingWithPlayer();
					handler.addEnemy(new BasicEnemy(randomCoordinates.getX(),randomCoordinates.getY(),ID.Enemy,rndm,camera));
				}
			}
		}
	}
	
	
	
	
	//berechnet wie viele Gegner in dieser Welle Spawnen
	public int numberOfEnemies(double x) {
		int enemies = 0;
		x = x / 10;
		if(x > 0) {
			enemies++;
		}
		x--;
		while(x > 0) {
			enemies++;
			x--;
		}
		return enemies;
	}
	
	
	
	
	//erstellt einen Punkt (eigentlich Rechteck) wo der nächste gegner spawnt
	public Rectangle randomCoordinatesNotIntersectingWithPlayer() {
		//No Spawn Zone
		Rectangle playerArea = new Rectangle((int)player.getX()-80,(int)player.getY()-80,170,170);
		//Enemy Location
		Rectangle enemy = randomCoordinates();
		
		
		while(playerArea.intersects(enemy)) {
			enemy = randomCoordinates();
		}
		return enemy;
	}
	
	public Rectangle randomCoordinates() {
		int tempX = rndm.nextInt(Game.MAX_BOUND_X-Game.MIN_BOUND_X-100)+50;
		int tempY = rndm.nextInt(Game.MAX_BOUND_Y-Game.MIN_BOUND_Y-100)+50;
		return new Rectangle(tempX,tempY,1,1);
	}
}
