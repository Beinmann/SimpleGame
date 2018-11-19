package main.visuals.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Textures {

	private LinkedList<BufferedImage> playerModels = new LinkedList<BufferedImage>();
	private LinkedList<LinkedList<BufferedImage>> enemyModels = new LinkedList<LinkedList<BufferedImage>>();
	private LinkedList<BufferedImage> backgrounds = new LinkedList<BufferedImage>();
	private LinkedList<BufferedImage> bulletModels = new LinkedList<BufferedImage>();
	private BufferedImageLoader loader = new BufferedImageLoader();
	
	public Textures() {
		//Create Player models
		SpriteSheet players = new SpriteSheet("/models/playerModels.png",20,20);
		//SpriteSheet enemies = new SpriteSheet("/models/enemyModels.png",40,40);
		

		playerModels.add(players.getImage(1,1));
		playerModels.add(players.getImage(2,1));
		playerModels.add(players.getImage(3,1));
		playerModels.add(players.getImage(4,1));
		playerModels.add(players.getImage(1,2));
		playerModels.add(players.getImage(2,2));
		playerModels.add(players.getImage(3,2));
		playerModels.add(players.getImage(4,2));
		
		
		
		
		
		
		//Load Backgrounds
		try {
			backgrounds.add(loader.loadImage("/backgrounds/background1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	//Getters
	public LinkedList<BufferedImage> getPlayerModels() {
		return playerModels;
	}

	public LinkedList<LinkedList<BufferedImage>> getEnemyModels() {
		return enemyModels;
	}

	public LinkedList<BufferedImage> getBackgroundModels() {
		return backgrounds;
	}

	public LinkedList<BufferedImage> getBulletModels() {
		return bulletModels;
	}
	
	
	
	
}
