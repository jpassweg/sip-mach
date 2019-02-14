package game;

import java.util.ArrayList;
import java.util.Random;

public class GameWindow {
	Window window;
	Player player;
	ArrayList<Meteor> meteors;
	Random rand;
	int stepcounter = 0;
	int screenWidth;
	int screenHeight;
	
	public GameWindow(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		window = new Window("SPI-MACH", screenWidth, screenHeight);
		meteors = new ArrayList<Meteor>();
	}
	
	public void run() {
		player = new Player(screenWidth, screenHeight);
		rand = new Random();
		window.setResizable(false);
		window.open();
		
		int movement = 0;
		 
		while(window.isOpen()) {
			
			
			draw();
			
			
			
			for(int i = 0; i < meteors.size(); i++) {
				Meteor curr = meteors.get(i);
				if(curr.y > screenHeight) {
					meteors.remove(curr);
				} else {
					curr.update();
				}
			}
			movement = 0;
			if(window.isKeyPressed("left")) {
				movement = -1;
			} else if(window.isKeyPressed("right")) {
				movement = 1;
			} //add down-key to stop moving
			player.move(movement);
			
			
			window.refreshAndClear();
			
			stepcounter++;
			if(rand.nextInt(50 - Math.min(45, (int) stepcounter/50)) == 0) {
				meteors.add(new Meteor(window));
			}
		}
	}

	void draw() {
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
