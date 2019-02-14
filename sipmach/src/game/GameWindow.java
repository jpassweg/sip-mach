package game;

import java.util.LinkedList;
import java.util.Random;

import java.util.ListIterator;

public class GameWindow {
	Window window;
	Player player;
	LinkedList<Meteor> meteors;
	Random rand;
	int stepcounter = 0;
	
	public GameWindow(int screenWidth, int screenHeight) {
		window = new Window("SPI-MACH", screenWidth, screenHeight);
		meteors = new LinkedList<Meteor>();
	}
	
	public void run() {
		player = new Player((int) window.getWidth(), (int) window.getHeight());
		rand = new Random();
		window.setResizable(false);
		window.open();
		
		int movement = 0;
		ListIterator<Meteor> meteorsIter = meteors.listIterator();
		 
		while(window.isOpen()) {
			
			
			draw();
			
			meteorsIter = meteors.listIterator();
			while(meteorsIter.hasNext()) {
				meteorsIter.next().update();
			}
			
			
			/*
			for(int i = 0; i < meteors.size(); i++) {
				meteors.get(i).update();
			}
			*/
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
