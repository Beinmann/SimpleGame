package main.States.Shop;

import java.awt.Graphics;

import main.Game;
import main.Adapters.KeyInput;
import main.Adapters.MouseInput;
import main.Adapters.Buttons.BackButton;
import main.Adapters.Buttons.FwdButton;
import main.GameObjects.ConcreteObjects.Player;
import main.States.GameState;
import main.States.State;
import main.States.StateID;

public class Shop extends State{
	
	private Player player;
	private Upgrade[] upgrades;
	private int curPage = 0;
	private ShopPage[] pages;
	private BackButton back;
	private FwdButton fwd;
	private GameState gameState;
	
	public Shop(Upgrade[] upgrades, GameState gameState, KeyInput keyInput, MouseInput mouseInput, Game game) {
		super(StateID.Shop, keyInput, mouseInput, game);
		this.gameState = gameState;
		this.player = player;
		this.upgrades = upgrades;
		
		pages = new ShopPage[roundUp((double)upgrades.length / 3.0)];
		
		int takenUpgrades = 0;
		for(int i = 0; i < pages.length; i++) {
			Upgrade[] temp;
			int n;
			if(upgrades.length - takenUpgrades >= 3) {
				n = 3;
			} else if(upgrades.length - takenUpgrades == 2) {
				n = 2;
			} else {
				n = 1;
			}
			temp = new Upgrade[n];
			for(int j = 0; j < n; j++) {
				temp[j] = upgrades[takenUpgrades];
				takenUpgrades++;
			}
			pages[i] = new ShopPage(temp, gameState, mouseInput);
		}
		
		back = new BackButton();
		fwd = new FwdButton();
		
		buttons.add(back);
		buttons.add(fwd);
	}
	
	@Override
	public void tick() {
		if(back.pressed()) {
			if(curPage <= 0) {
				game.setState(StateID.Game);
				delete();
			}
			curPage--;
		}
		if(fwd.pressed()) {
			curPage++;
		}
		curPage = Game.clamp(curPage,0,pages.length-1);
		
		for(int i = 0; i < pages.length; i++) {
			if(i == curPage) {
				pages[i].tick();
			}
		}
	}
	
	public int roundUp(double number) {
		if((number - (int)number) > 0) {
			return (int)number +1;
		} else {
			return (int)number;
		}
	}
	
	@Override
	public void render(Graphics g) {
		renderButtons(g);
		if(curPage != (pages.length - 1)) {
			fwd.render(g);
		}
		for(int i = 0; i < pages.length; i++) {
			if(i == curPage) {
				pages[i].render(g);
			}
		}
		/*Font fnt1 = new Font("arial",1,13);
		Font fnt2 = new Font("Arial",1,40);
		g.setColor(Color.gray);
		g.fillRect(10,10,45,45);
		g.fillRect(580,10,45,45);
		g.fillRect(120,150,100,100);
		g.fillRect(260,150,100,100);
		g.fillRect(400,150,100,100);
		g.fillRect(138,265,60,60);
		g.fillRect(278,265,60,60);
		g.fillRect(418,265,60,60);
		g.setColor(Color.white);
		g.fillRect(584,14,37,37);
		g.fillRect(14,14,37,37);
		g.fillRect(127,157,86,86);
		g.fillRect(267,157,86,86);
		g.fillRect(407,157,86,86);
		g.fillRect(143,270,50,50);
		g.fillRect(283,270,50,50);
		g.fillRect(423,270,50,50);
		g.setColor(Color.black);
		g.setFont(fnt1);
		g.drawString("Upgrade",143,190);
		g.drawString("Shot Speed",133,205);
		g.drawString("Upgrade",283,190);
		g.drawString("Shot Delay",273,205);
		g.drawString("Upgrade",423,190);
		g.drawString("Shot Size",419,205);
		g.setFont(fnt2);
		g.drawString("<",20,47);
		g.drawString(">",592,47);
		g.setColor(Color.white);
		g.drawString("Money: " + gameState.getMoney() + "$",180,80);
		g.setFont(fnt1);
		g.setColor(Color.black);
		
		//Hier angeben was angezeigt werden soll
		if(upgrade1Level < upgrade1MaxLevel) {
			g.drawString("+"+(int)player.shotSpeedUpgrade,154,300);
			g.drawString("Cost: " + upgrade1Cost + "$",137,220);
		} else {
			g.drawString("max",154,300);
		}
		
		if(upgrade2Level < upgrade2MaxLevel) {
			g.drawString("-"+player.shotDelayUpgrade,296,300);
			g.drawString("Cost: " + upgrade2Cost + "$",275,220);
		} else {
			g.drawString("max",296,300);
		}
		
		if(upgrade3Level < upgrade3MaxLevel) {
			g.drawString("+"+player.shotSizeUpgrade,437,300);
			g.drawString("Cost: " + upgrade3Cost + "$",417,220);
		} else {
			g.drawString("max",437,300);
		}*/
	}
	/*
	@Override
	public void mPressed(MouseEvent e) {
		if(e.getButton() == 1) {
			//Upgrade Shot Speed
			if(mouseOver(e,120,150,100,100)) {
				if(upgrade1Level < upgrade1MaxLevel) {
					if(gameState.getMoney() >= upgrade1Cost) {
						gameState.setMoney(gameState.getMoney()-upgrade1Cost);
						upgrade1Cost += upgrade1CostIncrease;
						upgrade1Level++;
						
						//Hier effekt schreiben
						player.shotSpeedUpgrade += 1;
					}
				}
			}
			//Upgrade Shot Delay
			else if(mouseOver(e,260,150,100,100)) {
				if(upgrade2Level < upgrade2MaxLevel) {
					if(gameState.getMoney() >= upgrade2Cost) {
						gameState.setMoney(gameState.getMoney()-upgrade2Cost);
						upgrade2Cost += upgrade2CostIncrease;
						upgrade2Level++;
						
						//Hier effekt schreiben
						player.shotDelayUpgrade += 5;
					}
				}
			}
			//Upgrade Shot Size
			else if(mouseOver(e,400,150,100,100)) {
				if(upgrade3Level < upgrade3MaxLevel) {
					if(gameState.getMoney() >= upgrade3Cost) {
						gameState.setMoney(gameState.getMoney()-upgrade3Cost);
						upgrade3Cost += upgrade3CostIncrease;
						upgrade3Level++;
						
						//Hier effekt schreiben
						player.shotSizeUpgrade += 1;
					}
				}
			}
			
			
			
			
			
			//back
			else if(mouseOver(e,10,10,45,45)) {
				game.setState(StateID.Game);
			}
			//nextPage
			else if(mouseOver(e,580,10,45,45)) {
				game.setState(StateID.Shop2);
			}
		}
	}*/
	
	
	@Override
	public int getObjects() {
		return 0;
	}
}
