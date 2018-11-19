/**
 * 
 */
package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import main.Adapters.KeyInput;
import main.Adapters.MouseInput;
import main.States.CurState;
import main.States.Menu;
import main.States.State;
import main.States.StateID;
import main.States.Options.Options;
import main.visuals.Window;
import main.visuals.sprites.BufferedImageLoader;


public class Game extends Canvas implements Runnable{

	
	public static BufferedImage background2;
	
	
	private static final long serialVersionUID = 6691247796639148462L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final String TITLE = "Test Game";
	public static int MIN_BOUND_X = 310;
	public static int MAX_BOUND_X = 1611;
	public static int MIN_BOUND_Y = 210;
	public static int MAX_BOUND_Y = 951;
	
	
	private boolean running = true;
	private boolean visibleStats = false; //gibt jede sekunde fps und anzahl an objekten in der konsole aus wenn wahr
	
	
	private Random rndm = new Random();
	
	
	private Options options;
	private LinkedList<State> states = new LinkedList<State>(); //Liste von Zuständen
	private MouseInput mouseInput = new MouseInput();
	private KeyInput keyInput = new KeyInput();
	private CurState curState = new CurState(StateID.Menu);
	private boolean comodification = false; //wird aktuell nicht benutzt, hatte ich aber mal gebraucht,
											//weil manche funktionen Listen bearbeitet haben, während sie iteriert wurden.
	
	
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Thread game = new Thread(new Game());
			game.start();
		});		
	}
	
	
	
	
	
	public Game() {
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);
		
		new Window(WIDTH,HEIGHT,TITLE,this);

		
		//Erstelle den Zustand Menü und setze den aktuellen Zustand auf Menü
		states.add(new Menu(mouseInput, keyInput, this));
		curState.setId(StateID.Menu);

		/*BufferedImageLoader loader = new BufferedImageLoader();
		try {
			background2 = loader.loadImage("test/Background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		options = new Options(StateID.Options,keyInput,mouseInput,this);
		add(options);
	}
	
	
	
	
	


	//aktiviert alle States, deren ID der aktuell aktiven ID entspricht. deaktiviert alle anderen
	//Löscht außerdem alle States, die zur löschung markiert sind
	public void getActiveStates() {
		LinkedList<State> temp = new LinkedList<State>();
		
		
		
		//entfernt markierte Zustände
		for(State cur : states) {
			if(cur.markedForDeletion()) {
				temp.add(cur);
			}
		}
		for(State cur : temp) {
			states.remove(cur);
		}
		
		
		
		
		//aktualisiert welche Zustände aktiv sind
		for(State cur : states) {
			StateID stateId = curState.getId();
			if(stateId == cur.getID()) {
				cur.setActive(true);
			} else {
				cur.setActive(false);
			}
		}
	}
	
	//Ändert den aktuellen Zustand des spiels
	public void setState(StateID sId) {
		curState.setId(sId);
	}
	
	
	
	//Game Loop
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; //updates bzw. ticks pro sekunde
		double amountOfFrames = 120.0; //max Framerate
		double nsT = 1000000000 / amountOfTicks; //zeit zwischen den updates in nanosekunden
		double nsF = 1000000000 / amountOfFrames; //erwünschte zeit zwischen den Frames, sodass die framerate erreicht wird
		double deltaT = 0; 
		double deltaF = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			//Zeit seit dem Letzten update
			long now = System.nanoTime();
			deltaT += (now - lastTime) / nsT;
			deltaF += (now - lastTime) / nsF;
			lastTime = now;
			//Updates
			while(deltaT >= 1) {
				tick();
				deltaT--;
			}
			//Rendern
			if(deltaF >= 1) { 
				render();
				deltaF = 0;
				frames++;
			}
			
			//Wenn visibleStats true ist, wird jede Sekunde fps und Anzahl der Objekte ausgegeben
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if(visibleStats) {
					System.out.print("FPS: " + frames + "   ");
				}
				frames = 0;
				int temp = 0;
				for(State cur : activeStates()) {
					temp += cur.getObjects();
				}
				if(visibleStats) {
					System.out.println("Objects: " + temp);
				}
			}
			
		}
	}
	
	

	
	
	
	
	//hinzufügen / entfernen von Zuständen wie z.B. Menü
	public void add(State s) {
		states.add(s);
	}
	public void remove(State s) {
		states.remove(s);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	//gibt alle aktiven Zustände in einer Liste zurück
	public LinkedList<State> activeStates() {
		LinkedList<State> temp = new LinkedList<State>();
		for(State cur : states) {
			if(cur.active()) {
				temp.add(cur);
			}
		}
		return temp;
	}
	

	
	//Tickt jeden aktiven Zustand
	public void tick() {
		getActiveStates();
		for(State cur : activeStates()) {
			cur.tick();
		}
	}
	
	
	
	//Rendert jeden aktiven Zustand
	public void render() {
		//Graphics zum zeichnen und Buffer erzeugen
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		java.awt.Graphics g = bs.getDrawGraphics();
		
		
		
		
		//Hier steht Graphics g zur verfügung

		//Durch alle aktuellen states durchgehen und alle aktiven rendern
		for(State cur : activeStates()) {
			cur.render(g);
		}
		
		
		
		
		
		
		//alles anzeigen und g disposen
		g.dispose();
		bs.show();
	}
	
	
	
	
	//sorgt dafür, dass ein wert im Intervall [min,max] bleibt. wird in anderen Klassen verwendet
	public static double clamp(double var, double min, double max) {
		if(var >= max) {
			return max;
		} else if(var <= min) {
			return min;
		} else {
			return var;
		}
	}
	public static int clamp(int var, int min, int max) {
		if(var >= max) {
			return max;
		} else if(var <= min) {
			return min;
		} else {
			return var;
		}
	}
	public static float clamp(float var, float min, float max) {
		if(var >= max) {
			return max;
		} else if(var <= min) {
			return min;
		} else {
			return var;
		}
	}
	
	
	

	
	//Getter
	public LinkedList<State> getStates() {
		return states;
	}
	public Random getRandom() {
		return rndm;
	}
	public Options getOptions() {
		return options;
	}
	
}
