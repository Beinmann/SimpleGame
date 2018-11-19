package main.Adapters.Buttons;

import java.awt.image.BufferedImage;

public class BufferedImageButton extends SimpleButton {

	private BufferedImage image;
	
	public BufferedImageButton(int x, int y, BufferedImage image) {
		super(x,y,image.getWidth(),image.getHeight(),java.awt.Color.black);
		this.image = image;
	}
	
	@Override
	public void render(java.awt.Graphics g) {
		g.drawImage(image,x,y,null);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}
