package main.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import main.ID;
import main.Adapters.KeyInput;
import main.Adapters.MouseInput;
import main.Adapters.Buttons.FwdButton;
import main.Adapters.Buttons.MyButton;
import main.GameObjects.AbstractClasses.GameObject;
import main.GameObjects.ConcreteObjects.Obstacle;
import main.GameObjects.ConcreteObjects.Player;
import main.States.Shop.Money;
import main.States.Shop.Shop;
import main.backgroundScripts.Handler;
import main.backgroundScripts.Spawner;
import main.visuals.Camera;
import main.visuals.HUD;

public class GameState extends State {

	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	private final long startTime = System.currentTimeMillis();
	private Player mainPlayer;
	private Camera camera = new Camera();
	private MyButton shopButton;
	private Obstacle[] wall = new Obstacle[4];
	private Money money = new Money();
	private BufferedImage background;
	private FwdButton fwd;
	
	
	
	private BufferedImage scaledImage;
	
	
	
	
	public GameState(KeyInput keyInput, MouseInput mouseInput, Game game, Random rndm) {
		super(StateID.Game, keyInput, mouseInput, game);
		
		handler = new Handler(money, game);
		mainPlayer = new Player(100,100, ID.Player, handler, 100, camera, game.getOptions().getPlayerModel());
		handler.addPlayer(mainPlayer);
		hud = new HUD(mainPlayer, handler, this);
		spawner = new Spawner(handler, hud, rndm,mainPlayer, camera);
		
		shopButton = new MyButton(450,400,45,45,4,40,Color.gray,Color.white,"$",-3,6);
		buttons.add(shopButton);
		fwd = new FwdButton();
		buttons.add(fwd);
		
		wall[0] = new Obstacle(-300,-300,2500,510,Color.gray,camera);
		wall[1] = new Obstacle(0,0,310,1500,Color.gray,camera);
		wall[2] = new Obstacle(0,950,2500,300,Color.gray,camera);
		wall[3] = new Obstacle(1611,0,400,1500,Color.gray,camera);
		for(int i = 0; i < wall.length; i++) {
			handler.addObstacle(wall[i]);
		}
		

		background = game.getOptions().getBackground();
		
		
		

		int w = background.getWidth();
		int h = background.getHeight();
		scaledImage = new BufferedImage(w*2,h*2,BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(1.0,1.0);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		scaledImage = scaleOp.filter(background, scaledImage);
	}

	@Override
	public void tick() {
		long curTime = System.currentTimeMillis();
		if((curTime - startTime) > 10000) {
		}
		handler.tick();
		spawner.tick();
		hud.tick();
		
		
		if(shopButton.pressed()) {
			changeState(new Shop(handler.getPlayerList().getFirst().getUpgrades(), this, keyInput, mouseInput, game));
		}
		if(fwd.pressed()) {
			changeState(StateID.Menu);
			delete();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background,(int)(310-camera.getX()),(int)(210-camera.getY()),null);
		handler.render(g);

		hud.render(g);

		renderButtons(g);
	}
	
	@Override
	public void kPressed(KeyEvent e) {

		
		for(GameObject cur : handler.getPlayerList()) {
			if(cur.getID() == ID.Player) {
				cur.keyPressed(e);
			}
		}
	}
	
	@Override
	public void kReleased(KeyEvent e) {

		for(GameObject cur : handler.getPlayerList()) {
			if(cur.getID() == ID.Player) {
				cur.keyReleased(e);
			}
		}
	}
	
	@Override
	public int getObjects() {
		return 1 + 1 + handler.numberOfObjects();
	}
	
	public void delete(int finalScore) {
		delete();
		game.add(new GameOver(mouseInput, keyInput, game, finalScore));
		game.setState(StateID.GameOver);
		delete();
	}
	
	
	public void setMoney(int money) {
		this.money.setMoney(money);
	}
	
	public int getMoney() {
		return this.money.getMoney();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Cursor over: (" + (e.getX() + camera.getX()) + "," + (e.getY() + camera.getY()) + ")" );
		if(active) {
			for(main.Adapters.Buttons.SimpleButton cur : buttons) {
				cur.mousePressed(e);
			}
		}
	}
}
