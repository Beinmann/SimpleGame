package main.Adapters.Buttons;

import java.awt.Color;

import main.Adapters.MouseInput;

//voreinstellungen für Knopf oben links

public class BackButton extends MyButton {

	public BackButton() {
		super(20, 20, 45, 45, 4, 50, Color.gray, Color.white, "<", -7, 10);
	}
	public BackButton(int x, int y) {
		super(x, y, 45, 45, 4, 50, Color.gray, Color.white, "<", -7, 10);
	}
	
}
