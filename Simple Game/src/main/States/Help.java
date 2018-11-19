package main.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;
import main.Adapters.KeyInput;
import main.Adapters.MouseInput;
import main.Adapters.Buttons.BackButton;
import main.Adapters.Buttons.FwdButton;


//Unfinished


public class Help extends State{

	private BackButton back;
	
	public Help(KeyInput keyInput, MouseInput mouseInput, Game game) {
		super(StateID.Help,keyInput,mouseInput,game);
		back = new BackButton();
		buttons.add(back);
	}

	@Override
	public void tick() {
		//Setze den Zustand zurück auf Menü und Lösche diesen Zustand
		if(back.pressed()) {
			game.setState(StateID.Menu);
			delete();
		}
	}

	@Override
	public void render(Graphics g) {
		drawBackground(g);
		renderButtons(g);
		
		Font fnt = new Font("arial",1,30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("1,4",100,100);
		
	}

	@Override
	public int getObjects() {
		return 2;
	}
	
}
