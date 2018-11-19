package main.visuals;

import java.awt.Color;

import main.Game;
import main.GameObjects.ConcreteObjects.Player;
import main.States.GameState;
import main.backgroundScripts.Handler;

public class HUD {
	
	private Player player;
	private int greenValue = 255;
	private int level = 1;
	private int score = 0;
	private int lastScore;
	private boolean alive = true;
	private Handler handler;
	private GameState gameState;
	
	public HUD(Player player, Handler handler, GameState gameState) {
	 	this.player = player;
	 	this.handler = handler;
	 	this.gameState = gameState;
	}
	
	public void tick() {
		int tempHealth = Game.clamp(player.getHealth(),0,100);
		greenValue = (int)(tempHealth*2.5);
		greenValue = Game.clamp(greenValue,0,255);
		if(alive) {
			alive = !player.markedForDeletion();
			score += 5;
			if(score - lastScore > 500) {
				level++;
				lastScore += 500;
			}
		} else {
			gameState.delete(score);
		}
	}
	
	public void render(java.awt.Graphics g) {
		int tempHealth = Game.clamp(player.getHealth(),0,100);
		g.setColor(java.awt.Color.gray);
		g.fillRect(15,15,200,32);
		g.setColor(new Color(75,greenValue,0));
		g.fillRect(15,15,tempHealth*2,32);
		g.setColor(java.awt.Color.white);
		g.drawRect(15,15,200,32);

		g.drawString("Score: " + score, 14, 59);
		g.drawString("Level: " + level, 14, 70);
		g.drawString("Money: " + gameState.getMoney() + "$", 14, 81);
		g.drawString("enemies: " + handler.getEnemyList().size(), 14, 92);
	}
	
	public int getScore() { return score; }
	public int getLevel() { return level; }
	
}
