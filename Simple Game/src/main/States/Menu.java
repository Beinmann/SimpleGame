package main.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import main.Game;
import main.Adapters.KeyInput;
import main.Adapters.MouseInput;
import main.Adapters.Buttons.MyButton;
import main.Adapters.Buttons.SimpleButton;

public class Menu extends State {
	
	private MyButton start, help, options;
	
	public Menu(MouseInput mouseInput, KeyInput keyInput, Game game) {
		super(StateID.Menu, mouseInput, game);
		this.keyInput = keyInput;
		start = new MyButton(180,130,300,80,8,42,Color.gray, Color.white, "Start", 0, 0);
		help = new MyButton(180,235,300,80,8,42,Color.gray, Color.white, "Help", 0, 0);
		options = new MyButton(180,340,300,80,8,42,Color.gray, Color.white, "Options", -30, 0);

		buttons.add(start);
		buttons.add(help);
		buttons.add(options);
	}
	
	
	
	
	
	
	
	@Override
	public void tick() {
		//erstelle ein Spiel, setze den Zustand auf Spiel
		if(start.pressed()) {
			changeState(new GameState(keyInput, mouseInput, game,game.getRandom()));
		}
		
		
		//Erstelle einen Hilfs Zustand und setze den aktuellen Zustand auf Help
		if(help.pressed()) {
			//changeState(new Help(keyInput, mouseInput, game));
			changeState(new ScaleTest(keyInput,mouseInput,game));
		}
		
		
		//
		if(options.pressed()) {
			changeState(StateID.Options);
		}
	}
	
	
	//Rendert den Titel und die Knöpfe 
	public void render(java.awt.Graphics g) {
		drawBackground(g);
		
		Font fnt = new Font("arial", 1, 50);
		

		g.setColor(Color.white);
		g.setFont(fnt);
		g.drawString(Game.TITLE,200,80);
		
		
		start.render(g);
		help.render(g);
		options.render(g);
	}
	
	@Override
	public int getObjects() {
		return 1;
	}
	
}
