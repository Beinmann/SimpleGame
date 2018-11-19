package main.backgroundScripts;

import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;
import main.ID;
import main.GameObjects.AbstractClasses.Enemy;
import main.GameObjects.AbstractClasses.GameObject;
import main.GameObjects.ConcreteObjects.Bullet;
import main.GameObjects.ConcreteObjects.Obstacle;
import main.GameObjects.ConcreteObjects.Player;
import main.States.Shop.Money;


//beinhaltet Listen mit den aktuellen Objekten des Spiels und ist dafür zuständig alle zu rendern zu updaten und Kollisionen zu berechnen


public class Handler {
	
	private LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private LinkedList<GameObject> trails = new LinkedList<GameObject>();
	private LinkedList<Player> players = new LinkedList<Player>();
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	private LinkedList<Obstacle> obstacles = new LinkedList<Obstacle>();
	
	//private Game game;
	private Money money;
	
	public Handler(Money money, Game game) {
		//this.game = game;
		money.setMoney(money.getMoney() + 10000000);
		this.money = money;
	}
	
	
	//entfernt alle toten objekte und tickt die restlichen
	public void tick() {
		//remove uneccesary objects
		removeDeadObjects();
		
		
		//tick all objects
		for(GameObject cur : players) {
			cur.tick();
		}
		for(GameObject cur : objects) {
			cur.tick();
		}
		for(GameObject cur : trails) {
			cur.tick();
		}
		for(GameObject cur : bullets) {
			cur.tick();
		}
		for(GameObject cur : enemies) {
			cur.tick();
		}
		for(GameObject cur : obstacles) {
			cur.tick();
		}
		collision();
	}
	
	
	//rendert alle Objekte
	public void render(Graphics g) {
		for(GameObject cur : trails) {
			cur.render(g);
		}
		for(GameObject cur : players) {
			cur.render(g);
		}
		for(GameObject cur : objects) {
			cur.render(g);
		}
		for(GameObject cur : bullets) {
			cur.render(g);
		}
		for(GameObject cur : enemies) {
			cur.render(g);
		}
		for(GameObject cur : obstacles) {
			cur.render(g);
		}
	}
	
	
	public void removeDeadObjects() {
	LinkedList<GameObject> temp = new LinkedList<GameObject>();
	for(GameObject cur : objects) {
		if(cur.markedForDeletion()) {
			temp.add(cur);
		}
	}
	for(GameObject cur : trails) {
		if(cur.markedForDeletion()) {
			temp.add(cur);
		}
	}
	for(GameObject cur : temp) {
		removeObject(cur);
	}
	
	
	
	
	
	
	temp = new LinkedList<GameObject>();
	for(GameObject cur : players) {
		if(cur.markedForDeletion()) {
			temp.add(cur);
		}
	}
	
	for(GameObject cur : temp) {
		removePlayer(cur);
	}
	
	
	
	
	temp = new LinkedList<GameObject>();
	for(Enemy cur : enemies) {
		if(cur.markedForDeletion()) {
			temp.add(cur);
			money.setMoney(money.getMoney()+cur.getMoney());
		}
	}
	for(GameObject cur : temp) {
		removeEnemy(cur);
	}
	
	
	
	temp = new LinkedList<GameObject>();
	for(GameObject cur : bullets) {
		if(cur.markedForDeletion()) {
			temp.add(cur);
		}
	}
	for(GameObject cur : temp) {
		removeBullet(cur);
	}
	
	
	
	temp = new LinkedList<GameObject>();
	for(GameObject cur : obstacles) {
		if(cur.markedForDeletion()) {
			temp.add(cur);
		}
	}
	for(GameObject cur : temp) {
		removeObstacle(cur);
	}
	
	
	
	}
	
	
	
	
	public void collision() {
		
		
		//Kollision Bullets und Enemies
		for(Bullet curBullet : bullets) {
			for(Enemy curEnemy : enemies) {
				if(curBullet.getBoundsRelativeToCamera().intersects(curEnemy.getBoundsRelativeToCamera())) {
					curBullet.kill();
					curEnemy.subtractHealth(curBullet.getDamage());
				}
			}
		}
		
		
		//Kollision Players und Enemies
		for(Player curPlayer : players) {
			for(Enemy curEnemy : enemies) {
				if(curPlayer.getBoundsRelativeToCamera().intersects(curEnemy.getBoundsRelativeToCamera())) {
					curEnemy.kill();
					curPlayer.subtractHealth(curEnemy.getDamage());
				}
			}
		}
		
		
		//Kollision Bullets und Obstacles
		for(Bullet curBullet : bullets) {
			for(Obstacle curObstacle : obstacles) {
				if(curBullet.getBoundsRelativeToCamera().intersects(curObstacle.getBoundsRelativeToCamera())) {
					curBullet.kill();
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//Hinzufügen und entfernen von Objekten
	public void addObject(GameObject object) {
		if(object.getID() == ID.Trail) {
			trails.add(object);
		} else {
			objects.add(object);
		}
	}
	
	public void addObstacle(Obstacle obstacle) {
		obstacles.add(obstacle);
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
	
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	
	
	
	
	
	
	
	
	public void removeObject(GameObject object) {
		if(object.getID() == ID.Player) {
			players.remove(object);
		} else if(object.getID() == ID.Trail) {
			trails.remove(object);
		} else {
			objects.remove(object);
		}
	}
	
	public void removeObstacle(GameObject object) {
		obstacles.remove(object);
	}
	
	public void removeBullet(GameObject bullet) {
		bullets.remove(bullet);
	}
	
	
	public void removePlayer(GameObject object) {
		players.remove(object);
	}
	
	public void removeEnemy(GameObject enemy) {
		enemies.remove(enemy);
	}
	
	
	
	
	//Getter
	public int numberOfObjects() {
		return (objects.size() + players.size() + trails.size() + bullets.size() + enemies.size() + obstacles.size());
	}
	
	public LinkedList<GameObject> getObjectList() { return objects; }
	public LinkedList<Player> getPlayerList() { return players; }
	public LinkedList<GameObject> getTrailList() { return trails; }
	public LinkedList<Bullet> getBulletList() { return bullets; }
	public LinkedList<Enemy> getEnemyList() { return enemies; }
	public LinkedList<Obstacle> getObstacleList() { return obstacles; }
	
}
