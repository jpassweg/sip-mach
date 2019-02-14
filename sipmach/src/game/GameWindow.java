package game;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;
import java.lang.ClassCastException;

public class GameWindow {
	
	String spaceshipSkin = "graphics/RoundSpaceShip.png";
	String meteorSkin = "graphics/SwissCheeseMeteor.png";
	String backgroundSkin= "graphics/background1.png";
	
	private Window window;
	private Player player;
	public ArrayList<Meteor> meteors;
	public ArrayList<Shot> shots;
	
	int stepcounter = 0; // also score
	int highscore;
	
	int screenWidth;
	int screenHeight;
	
	String meteor = "game.TeaseMeteor";
	Constructor<?> meteorConstructor;
	double meteorRate;
	int maxRad;
	
	Random rand;

	public GameWindow(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		window = new Window("Pixels", screenWidth, screenHeight);
		window.setResizable(false);
		window.open();
		
		player = new Player(screenWidth, screenHeight, spaceshipSkin);
		
		meteors = new ArrayList<Meteor>();
		shots = new ArrayList<Shot>();
		
		try {
			Class<?> meteorClass = Class.forName(meteor);
			meteorConstructor = meteorClass.getConstructor(Integer.TYPE, Integer.TYPE, Double.TYPE);
			Meteor instance = (Meteor) meteorConstructor.newInstance(screenWidth, screenHeight, 1);
			meteorRate = instance.rate;
			maxRad = giveMaxRad(instance);
		} catch (Exception e) {
			e.printStackTrace();
			meteorRate = 1;
		}
		
		highscore = 0;

		rand = new Random();		
	}
	
	int giveMaxRad(Meteor met) {
		try {
			return ((MayhemMeteor) met).maxRad;
		} catch (ClassCastException ex) {}
		try {
			return ((TeaseMeteor) met).maxRad;
		} catch (ClassCastException ex) {}
		try {
			return ((DownfallMeteor) met).maxRad;
		} catch (ClassCastException ex) {}
		return 1;
	}

	public void run() {

		while (window.isOpen()) {
			draw();
			handleCollisions();
			handleMeteors();
			handlePlayer();
			handleShots();
			
			stepcounter++;
			window.refreshAndClear(10);
		}
	}

	private void handleCollisions() {
		if (Collision.collides(player, meteors)) {
			if (stepcounter > highscore)
				highscore = stepcounter;
			reset();
		}
		Collision.shotMeteorB(shots, meteors);
	}
	
	private void handleMeteors() {
		// update of remove if out of screen
		for (int i = 0; i < meteors.size(); i++) {
			if (meteors.get(i).y > screenHeight) {
				meteors.remove(i);
			} else {
				meteors.get(i).update();
			}
		}

		if (rand.nextDouble() < meteorRate / 5.0) {
			try {
				switch (meteor) {
				case "game.TeaseMeteor":
					Class<?> meteorClass = Class.forName(meteor);
					meteorConstructor = meteorClass.getConstructor(Integer.TYPE, Integer.TYPE, Double.TYPE, Integer.TYPE, Integer.TYPE);
					meteors.add((Meteor) meteorConstructor.newInstance(screenWidth, screenHeight, 1, player.x - (player.playerWidth/2), 5));
					break;
				default:
					meteors.add((Meteor) meteorConstructor.newInstance(screenWidth, screenHeight, 1));
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				meteors.add(new MayhemMeteor(screenWidth, screenHeight, 1));
			}
		}
	}
	
	private void handlePlayer() {
		int movement = 0;
		if (stepcounter % 500 == 0) {
			player.addBoost();
			player.addShot();
		}

		if (window.wasKeyTyped("left")) {
			movement = -1;
		} else if (window.wasKeyTyped("right")) {
			movement = 1;
		}

		player.move(movement);
	}

	void handleShots() {
		if (window.wasKeyTyped("space")) {
			if (player.shotCounter > 0) {
				shots.add(new Shot(player.x, player.y));
				player.shotCounter--;
			} 
		}
	}

	void draw() {
		window.setColor(0, 0, 0);
		window.fillRect(0, 0, window.getWidth(), window.getHeight());
		window.drawImage(backgroundSkin, 0, 0, (double) screenWidth/200.0);
		window.setColor(139, 69, 19);
		
		for (int i = 0; i < meteors.size(); i++) {
			//TODO better image scaling
			window.drawImageCentered(meteorSkin, meteors.get(i).x, meteors.get(i).y, 1.0/(maxRad) * meteors.get(i).radius);
			//window.fillCircle(meteors.get(i).x, meteors.get(i).y, meteors.get(i).radius);
		}

		for (int i = 0; i < shots.size(); i++) {
			shots.get(i).draw(window);
			shots.get(i).update();
			if (shots.get(i).y < 0) {
				shots.remove(i);
			}
		}
		
		window.drawImageCentered(spaceshipSkin, player.x, player.y);
		
		drawBoostCounter();
		drawStats();
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
		window.drawString("Score: " + stepcounter, window.getWidth() * 0.45, window.getHeight() * 0.1);
		window.drawString("Highscore: " + highscore, window.getWidth() * 0.45, window.getHeight() * 0.11);
		window.drawString("Available shots: " + player.shotCounter, window.getWidth() * 0.1, window.getHeight() * 0.1);
	}

	void reset() {
		stepcounter = 0;
		meteors.clear();
		player.reset();
		shots.clear();
	}

}
