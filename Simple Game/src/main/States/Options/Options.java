package main.States.Options;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import main.Game;
import main.Adapters.KeyInput;
import main.Adapters.MouseInput;
import main.Adapters.Buttons.BackButton;
import main.Adapters.Buttons.BufferedImageButton;
import main.Adapters.Buttons.ButtonCast;
import main.Adapters.Buttons.SimpleButton;
import main.States.State;
import main.States.StateID;
import main.visuals.sprites.BufferedImageLoader;
import main.visuals.sprites.Textures;

public class Options extends State {

	private int background = 0;
	private LinkedList<BufferedImage> backgrounds;
	
	private int playerModel = 0;
	private LinkedList<BufferedImage> playerModels;
	
	private int enemyModel = 0;
	private LinkedList<LinkedList<BufferedImage>> enemyModels;
	
	private int bulletModel = 0;
	private LinkedList<BufferedImage> bulletModels;
	
	private LinkedList<BufferedImage> buttonImages = new LinkedList<BufferedImage>();
	
	private BackButton back;
	private BufferedImageButton button1, button2, button3, button4;
	
	private int curPage = 0;
	private LinkedList<OptionsPage> pages = new LinkedList<OptionsPage>();
	
	
	public Options(StateID id, KeyInput keyInput, MouseInput mouseInput, Game game) {
		super(id, keyInput, mouseInput, game);

		back = new BackButton();
		buttons.add(back);
		
		Textures tex = new Textures();
		
		playerModels = tex.getPlayerModels();
		enemyModels = tex.getEnemyModels();
		backgrounds = tex.getBackgroundModels();
		bulletModels = tex.getBulletModels();
		
		
		
		
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			buttonImages.add(loader.loadImage("/buttonIcons/backgroundModelIcon.png"));
			buttonImages.add(loader.loadImage("/buttonIcons/enemyModelIcon.png"));
			buttonImages.add(loader.loadImage("/buttonIcons/wallModelIcon.png"));
			buttonImages.add(loader.loadImage("/buttonIcons/playerModelIcon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		button1 = new BufferedImageButton(40,100,buttonImages.get(0));
		buttons.add(button1);
		button2 = new BufferedImageButton(340,100,buttonImages.get(1));
		buttons.add(button2);
		button3 = new BufferedImageButton(40,250,buttonImages.get(2));
		buttons.add(button3);
		button4 = new BufferedImageButton(340, 250, buttonImages.get(3));
		buttons.add(button4);
		
		

		
		initializeOptionPages();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void initializeOptionPages() {
		
		
		
		
		
		
		
		
		

		//Page 1 Background
		addOptionsPage(new OptionsPage() {
			@Override
			public void init() {
			}
			
			@Override
			public void tick() {
			}
			
			@Override
			public void render(Graphics g) {
				this.renderButtons(g);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		//Page 2 Enemy Model
		addOptionsPage(new OptionsPage() {
			@Override
			public void init() {
			}
			
			@Override
			public void tick() {
				
			}
			
			@Override
			public void render(Graphics g) {
				renderButtons(g);
			}
		});
		
		

		//Page 3 Bullet Model
		addOptionsPage(new OptionsPage() {
			@Override
			public void init() {
			}
			
			@Override
			public void tick() {
				
			}
			
			@Override
			public void render(Graphics g) {
				renderButtons(g);
			}
		});
		
		
		
		
		
		
		
		
		

		//Page 4 Player Model
		addOptionsPage(new OptionsPage() {
			@Override
			public void init() {
				int j = 0;
				for(BufferedImage curModel : playerModels) {
					BufferedImage tempImage = new BufferedImage(100,100, BufferedImage.TYPE_INT_ARGB);
			        Graphics2D g = tempImage.createGraphics();
			        AffineTransform at = AffineTransform.getScaleInstance(5.0, 5.0);
			        g.drawRenderedImage(curModel, at);
			        BufferedImageButton tempButton;
					if(j < 4) {
						tempButton = new BufferedImageButton(j*150 + 43,100,tempImage);
					} else {
						tempButton = new BufferedImageButton((j-4)*150 + 43,250,tempImage);
					}
					this.buttons.add(tempButton);
					this.casts.add(new ButtonCast(tempButton,8,Color.gray));
					j++;
				}
			}
			
			@Override
			public void tick() {
				for(int i = 0; i < this.buttons.size(); i++) {
					if(buttons.get(i).pressed()) {
						playerModel = i;
					}
				}
			}
			
			@Override
			public void render(Graphics g) {
				casts.get(playerModel).render(g);
				renderButtons(g);
			}
		});
		
		
		
		
		
		
	}
	
	
	private void addOptionsPage(OptionsPage page) {
		pages.add(page);
		page.init();
	}
	
	
	
	
	
	
	
	

	@Override
	public void tick() {
		if(back.pressed()) {
			if(curPage <= 0) {
				changeState(StateID.Menu);
			}
			curPage = 0;
		}
		if(button1.pressed()) {
			curPage = 1;
		}
		if(button2.pressed()) {
			curPage = 2;
		}
		if(button3.pressed()) {
			curPage = 3;
		}
		if(button4.pressed()) {
			curPage = 4;
		}

		if(curPage > 0) {
			pages.get(curPage-1).tick();
		}
	}

	@Override
	public void render(Graphics g) {
		drawBackground(g);
		back.render(g);
		if(curPage == 0) {
			renderButtons(g);
		} else {
			if(!pages.isEmpty()) {
				pages.get(curPage-1).render(g);
			} else {
				throw new IllegalArgumentException();
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(active) {
			back.mousePressed(e);
			if(curPage == 0) {
				for(SimpleButton cur : buttons) {
					cur.mousePressed(e);
				}
			} else {
				pages.get(curPage-1).mousePressed(e);
				//System.out.println("Currently on page " + curPage);
			}
		}
	}

	
	public BufferedImage getBackground() {
		return backgrounds.get(background);
	}
	
	public BufferedImage getPlayerModel() {
		return playerModels.get(playerModel);
	}
	
	public LinkedList<BufferedImage> getEnemyModel() {
		return enemyModels.get(enemyModel);
	}
	
	
	@Override
	public int getObjects() {
		return 0;
	}

}
