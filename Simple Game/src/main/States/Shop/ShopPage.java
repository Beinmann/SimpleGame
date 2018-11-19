package main.States.Shop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Adapters.Adapter;
import main.Adapters.MouseInput;
import main.Adapters.Buttons.MyButton;
import main.States.GameState;

public class ShopPage extends Adapter{

	private Upgrade[] upgrades;
	private MyButton[] buttons;
	private MyButton[] names;
	private GameState gameState;
	private long tStart = System.currentTimeMillis();
	private final int dist = 150;
	
	public ShopPage(Upgrade[] upgrades, GameState gameState, MouseInput mouseInput) {
		this.gameState = gameState;
		this.upgrades = upgrades;
		buttons = new MyButton[upgrades.length];
		names = new MyButton[upgrades.length];

		for(int i = 0; i < upgrades.length; i++) {
			buttons[i] = new MyButton(-30 + dist*(i+1),140,110,110,8,12,Color.gray,Color.white,upgrades[i].getUpgradeName(),-10,-20);
			names[i] = new MyButton(-15 + dist*(i+1),270,80,80,7,12,Color.gray,Color.white,upgrades[i].getSign() + upgrades[i].getValue(),-3,-10);
		}
		mouseInput.add(this);
	}
	
	public void tick() {
		for(int i = 0; i < buttons.length; i++) {
			/*
			names[i].setX(-15 + 150*(i+1));
			names[i].setY(270);
			names[i].setHeight(80);
			names[i].setWidth(80);*/
			
			names[i].setText(upgrades[i].getSign() + upgrades[i].getValue());
			}
		
		for(int i = 0; i < buttons.length; i++) {
			if(buttons[i].pressed()) {
				if(gameState.getMoney() >= upgrades[i].getCost()) {
					gameState.setMoney(gameState.getMoney()-upgrades[i].getCost());
					upgrades[i].buy();
				} else {
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < upgrades.length; i++) {
			buttons[i].render(g);
			names[i].render(g);
		}
		Font fnt = new Font("arial",1,12);

		g.setFont(fnt);
		g.setColor(Color.black);
		
		for(int i = 0; i < upgrades.length; i++) {
			g.drawString("Cost: " + upgrades[i].getCost(),-5+dist*(i+1),220);
		}
	}
	
	public void printUpgrades() {
		for(int i = 0; i < upgrades.length; i++) {
			System.out.print(upgrades[i].getUpgradeName() + "   ");
		}
		System.out.println();
	}
	
	
	@Override public void mousePressed(MouseEvent e) {
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].mousePressed(e);
		}
	}
	
}
