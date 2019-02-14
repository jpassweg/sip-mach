package game;

import java.util.ArrayList;
import java.util.Random;

public class GameWindow {
	Window window;
	Player player;
	ArrayList<Meteor> meteors;
	ArrayList<Shot> shots;
	Random rand;
	int stepcounter = 0;
	int screenWidth;
	int screenHeight;
	String direction;
	String imagePath = "src/alien_spaceshi.png";

	int score;
	int highscore;

	int shotCounter;
	int initialShotAmount = 5;
	int timer;
	boolean canShoot;
	boolean shoot;

	public GameWindow(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		window = new Window("Pixels", screenWidth, screenHeight);
		meteors = new ArrayList<Meteor>();
		shots = new ArrayList<Shot>();
		shotCounter = initialShotAmount;
		canShoot = true;
		shoot = false;
		this.timer = 0;

		this.score = 0;
		this.highscore = 0;
	}

	public void run() {
		player = new Player(screenWidth, screenHeight, imagePath);
		rand = new Random();
		window.setResizable(false);
		window.open();

		int movement = 0;

		while (window.isOpen()) {
			// put old version on canvas
			draw();
			// update to new version

			// collisions
			if (Collision.collides(player, meteors)) {
				// System.out.println("collision: (" + curr.x + "," + curr.y + ") - " +
				// curr.size);
				if (score > highscore)
					highscore = score;
				reset();
			}

			// update of remove if out of screen
			for (int i = 0; i < meteors.size(); i++) {
				if (meteors.get(i).y > screenHeight) {
					meteors.remove(i);
				} else {
					meteors.get(i).update();
				}

			}

			stepcounter++;
			if (rand.nextInt(50 - Math.min(45, (int) stepcounter / 50)) == 0) {
				meteors.add(new MayhemMeteor(screenWidth, screenHeight));
			}

			// Handle Boosts
			if (stepcounter % 500 == 0) {
				player.addBoost();
				if (shotCounter < 5) {
					shotCounter++;
				}
			}

			if (window.wasKeyTyped("left")) {
				movement = -1;
			} else if (window.wasKeyTyped("right")) {
				movement = 1;
			}

			// Handle shots
			if (window.wasKeyTyped("space")) {
				shoot = true;
			}
			handleShots(shoot);
			

			

		player.move(movement);
		movement = 0;
		shoot = false;
		// TODO: Meteor collisions? maybe not in O(n!)
		// Collision.meteorCollisions(meteors, window);
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
			window.fillCircle(meteors.get(i).x, meteors.get(i).y, meteors.get(i).radius);
		}

		for (int i = 0; i < shots.size(); i++) {
			shots.get(i).draw(window);
			shots.get(i).update();
			if (shots.get(i).y < 0) {
				shots.remove(i);
			}
		}
		// window.setColor(255, 255, 255);
		// window.fillRect(player.x, player.y, 10, 30);
		window.drawImageCentered("src/RoundSpaceShip.png", player.x, player.y);
		window.drawImageCentered(imagePath, player.x, player.y);
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
		window.drawString("Score: " + score, window.getWidth() * 0.45, window.getHeight() * 0.1);
		window.drawString("Highscore: " + highscore, window.getWidth() * 0.45, window.getHeight() * 0.11);
		window.drawString("Available shots: " + shotCounter, window.getWidth() * 0.1, window.getHeight() * 0.1);
	}

	void reset() {
		score = 0;
		meteors.clear();
		player.reset();
		resetShots();
		}
		
	void resetShots() {
		shots.clear();
		shotCounter = initialShotAmount;
		timer = 0;
		shoot = false;
	}
	
	void handleShots(boolean shoot) {
		if (shoot) {
			if (shotCounter > 0) {
				shots.add(new Shot(player.x, player.y));
				shotCounter--;
				canShoot = false;
			} else {
				shoot = false;
			}
		} 
	
		if (canShoot = false) {
			timer++;
		}
		if (timer >= 100) {
			canShoot = true;
			timer = 0;
		}
	}

}
