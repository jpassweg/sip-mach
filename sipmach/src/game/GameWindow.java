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
	String direction;
	
	int score;
	int highscore;

	public GameWindow(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		window = new Window("Pixels", screenWidth, screenHeight);
		meteors = new ArrayList<Meteor>();
		this.score = 0;
		this.highscore = 0;
	}
	
	public void run() {
		player = new Player(screenWidth, screenHeight);
		rand = new Random();
		window.setResizable(false);
		window.open();

		int movement = 0;

		while (window.isOpen()) {
			// put old version on canvas
			draw();
			// update to new version
			for (int i = 0; i < meteors.size(); i++) {
				Meteor curr = meteors.get(i);

				// collisions
				if (Math.sqrt(Math.pow((curr.x - player.x), 2) + Math.pow((curr.y - player.y), 2)) < curr.size) {
					//System.out.println("collision: (" + curr.x + "," + curr.y + ") - " + curr.size);
					if(score > highscore) highscore = score;
					reset();
				}

				// update of remove if out of screen
				if (curr.y > screenHeight) {
					meteors.remove(curr);
				} else {
					curr.update();
				}
			}

			stepcounter++;
			if (rand.nextInt(50 - Math.min(45, (int) stepcounter / 50)) == 0) {
				meteors.add(new Meteor(screenWidth, screenHeight));
			}
			if (stepcounter % 500 == 0) {
				player.addBoost();
			}

			if (window.wasKeyTyped("left")) {
				movement = -1;
			} else if (window.wasKeyTyped("right")) {
				movement = 1;
			}

			player.move(movement);
			movement = 0;
			//Collision.meteorCollisions(meteors, window);
			score++;
			drawStats();
			

			// refresh
			window.refreshAndClear(5);
		}
	}

	void draw() {
		window.setColor(0, 0, 0);
		window.fillRect(0, 0, window.getWidth(), window.getHeight());
		window.setColor(139, 69, 19);
		for (int i = 0; i < meteors.size(); i++) {
			window.fillCircle(meteors.get(i).x, meteors.get(i).y, meteors.get(i).size);
		}
		window.setColor(255, 255, 255);
		window.fillRect(player.x, player.y, 10, 30);
		drawBoostCounter();
	}

	void drawBoostCounter() {
		window.setColor(255, 255, 255);
		double baseX = window.getWidth() * 0.85;
		double baseY = window.getHeight() * 0.1;
		double lenY = window.getHeight() * 0.02;
		double lenX = window.getWidth() * 0.1 / 5.5;
		double dis = window.getWidth() * 0.001;
		window.drawRect(baseX, baseY, window.getWidth() * 0.097, lenY);
		window.setColor(255, 0, 0);
		for (int i = 0; i < player.boostCounter; i++) {
			window.fillRect(baseX + lenX * 4 + dis * 5 - lenX * i - dis * i, baseY + 0.25, lenX, lenY - 0.25);

		}
	}
	
	void drawStats() {
		window.setColor(255, 255, 255);
		window.setStrokeWidth(4);
		window.drawString("Score: " + score, window.getWidth() * 0.45, window.getHeight()*0.1);
		window.drawString("Highscore: " + highscore, window.getWidth()* 0.45, window.getHeight()*0.11);
	}
	
	void reset() {
		score = 0;
		meteors.clear();
		player.reset();
	}

}
