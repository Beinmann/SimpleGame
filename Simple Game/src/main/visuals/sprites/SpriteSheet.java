package main.visuals.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage spriteSheet;
	private int gridWidth;
	private int gridHeight;
	
	public SpriteSheet(String path, int gridWidth, int gridHeight) {
		try {
			this.spriteSheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setGrid(gridWidth, gridHeight);
	}
	
	public SpriteSheet(BufferedImage spriteSheet, int gridWidth, int gridHeight) {
		this.spriteSheet = spriteSheet;
		setGrid(gridWidth, gridHeight);
	}
	
	private void setGrid(int gridWidth, int gridHeight) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
	}
	
	public BufferedImage getImage(int column, int row) {
		return spriteSheet.getSubimage((column-1)*gridWidth,(row-1)*gridHeight,gridWidth,gridHeight);
	}
	
	public BufferedImage getImage(int column, int row, int width, int height) {
		return spriteSheet.getSubimage((column-1)*gridWidth,(row-1)*gridHeight,width,height);
	}
	
}
