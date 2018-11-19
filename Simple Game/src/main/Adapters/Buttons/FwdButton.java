package main.Adapters.Buttons;

import java.awt.Color;

import main.Adapters.MouseInput;


//voreinstellungen für Knopf oben rechts


public class FwdButton extends MyButton{

	public FwdButton() {
		super(570, 20, 45, 45, 4, 50, Color.gray, Color.white, ">", -7, 10);
	}
	public FwdButton(int x, int y) {
		super(x, y, 45, 45, 4, 50, Color.gray, Color.white, ">", -7, 10);
	}
}
