package main.States;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.Game;
import main.Adapters.KeyInput;
import main.Adapters.MouseInput;
import main.Adapters.Buttons.MyButton;

public class GameOver extends State{
	
	private int finalScore;
	private MyButton retry;
	private MyButton menu;
	private float alpha = 0.00f;
	private long lastTime = System.currentTimeMillis();
	
	public GameOver(MouseInput mouseInput, KeyInput keyInput, Game game, int finalScore) {
		super(StateID.GameOver,keyInput,mouseInput,game);
		this.finalScore = finalScore;

		retry = new MyButton(200,242,245,70,7,40,Color.gray, Color.white, "Try Again",-48,2);
		menu = new MyButton(200,327,245,70,7,40,Color.gray, Color.white, "Menu",-10,2);
		
		buttons.add(retry);
		buttons.add(menu);
	}

	@Override
	public void tick() {
		long now = System.currentTimeMillis();
		if(now - lastTime > 100) {
			alpha += 0.001f;
			alpha = Game.clamp(alpha,0f,0.14f);
			lastTime = now;
		}
		if(retry.pressed()) {
			changeState(new GameState(keyInput, mouseInput, game, game.getRandom()));
			delete();
		} else if(menu.pressed()) {
			changeState(StateID.Menu);
			delete();
		}
	}

	@Override
	public void render(Graphics g) {

		java.awt.Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(makeTransparent(alpha));
		drawBackground(g);
		g2d.setComposite(makeTransparent(1.0f));
		
		Font fnt = new Font("arial",1,70);
		Font fnt3 = new Font("arial",1,30);

		g.setFont(fnt);
		g.setColor(Color.red);
		g.drawString("Game Over",130,150);
		
		g.setFont(fnt3);
		g.setColor(Color.white);
		g.drawString("Final Score: " + finalScore,160,185);

		renderButtons(g);
	}
	

	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	
	
	
	
	
	
	
	
	
	

	@Override
	public int getObjects() {
		return 2;
	}
}
