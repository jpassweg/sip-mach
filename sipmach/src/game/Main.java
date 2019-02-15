package game;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
	
	private static int SCREEN_HEIGHT;
	private static int SCREEN_LENGTH;
	
	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_HEIGHT = (int) Math.floor(screenSize.getHeight());
		SCREEN_LENGTH = (int) Math.floor(SCREEN_HEIGHT * (2.0/3.0));
		GameWindow game = new GameWindow(SCREEN_LENGTH, SCREEN_HEIGHT);
		game.run();
	}
}
