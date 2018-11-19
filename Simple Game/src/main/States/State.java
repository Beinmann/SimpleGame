package main.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import main.Game;
import main.Adapters.Adapter;
import main.Adapters.KeyInput;
import main.Adapters.MouseInput;
import main.Adapters.Buttons.MyButton;
import main.Adapters.Buttons.SimpleButton;

public abstract class State extends Adapter{

	protected StateID id;
	protected boolean active = false;
	protected boolean marked = false;
	protected Game game;
	
	protected KeyInput keyInput = null;
	protected MouseInput mouseInput = null;
	protected LinkedList<SimpleButton> buttons = new LinkedList<SimpleButton>();
	
	

	public State(StateID id, KeyInput keyInput, MouseInput mouseInput, Game game) {
		this.id = id;
		this.game = game;
		keyInput.add(this);
		mouseInput.add(this);
		this.keyInput = keyInput;
		this.mouseInput = mouseInput;
	}
	
	public State(StateID id, MouseInput mouseInput, Game game) {
		this.id = id;
		this.game = game;
		mouseInput.add(this);
		this.mouseInput = mouseInput;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public void delete() {
		marked = true;
		if(mouseInput != null) {
			mouseInput.remove(this);
		}
		if(keyInput != null) {
			keyInput.remove(this);
		}
	}
	
	
	
	
		//Getter + Setter
		public StateID getID() {
			return id;
		}
		
		public void setActive(boolean active) {
			this.active = active;
		}
		
		public boolean active() {	
			return active;
		}
		
		public void setMarked(boolean marked) {
			this.marked = marked;
		}
		
		public boolean getMarked() {
			return marked;
		}
		
		public abstract int getObjects();
	
		
		
		
		
		
		
		
		
		
		
		
	public boolean markedForDeletion() {
		return marked;
	}
	
	
	public final void keyPressed(KeyEvent e) { if(active) {kPressed(e);} }	
	public void kPressed(KeyEvent e) {}
	public final void keyReleased(KeyEvent e) { if(active) {kReleased(e);} }
	public void kReleased(KeyEvent e) {}
	
	public void mousePressed(MouseEvent e) {
		if(active) {
			for(SimpleButton cur : buttons) {
				cur.mousePressed(e);
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
	}
	
	
	public void addKeyInput(KeyInput input) {
		input.add(this);
	}
	
	public void addMouseInput(MouseInput input) {
		input.add(this);
	}
	

	public void changeState(State newState) {
		game.add(newState);
		game.setState(newState.getID());
	}
	
	public void changeState(StateID newID) {
		game.setState(newID);
	}
	
	public void renderButtons(Graphics g) {
		for(SimpleButton cur : buttons) {
			cur.render(g);
		}
	}
	
	public void drawBackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
	}
}
