package game;

import java.util.ArrayList;
import java.util.Random;

public class GameWindow {
	Window window;
	ArrayList<Meteor> meteors = new ArrayList<Meteor>();
	Random rand;
	int stepcounter = 0;
	
	public GameWindow(int screenWidth, int screenHeight) {
		window = new Window("SPI-MACH", screenWidth, screenHeight);
		meteors = new ArrayList<Meteor>();
	}
	
	public void run() {
		Player player = new Player((int) window.getWidth(), (int) window.getHeight());
		rand = new Random();
		window.setResizable(false);
		window.open();
		
		int width = (int) window.getWidth();
		int movement = 0;
		while(window.isOpen()) {
			for(int i = 0; i < meteors.size(); i++) {
				meteors.get(i).update();
			}
			draw(window, player);
			if(window.isKeyPressed("left")) {
				movement = -1;
			} else if(window.isKeyPressed("right")) {
				movement = 1;
			} else {
				movement = 0;
			}
			player.move(movement, width);
			window.refreshAndClear();
			stepcounter++;
			if(rand.nextInt(50 - Math.min(49, (int) stepcounter/50)) == 0) {
				meteors.add(new Meteor(window));
			}
		}
	}
	
	void draw(Window window, Player player) {
		window.setColor(0,0,0);
		window.fillRect(0, 0, window.getWidth(), window.getHeight());
		window.setColor(139,69,19);
		for(int i = 0; i < meteors.size(); i++) {
			window.fillCircle(meteors.get(i).x, meteors.get(i).y, meteors.get(i).size);
		}
		window.setColor(255,255,255);
		window.fillRect(player.x, player.y, 10, 30);
	}

}
