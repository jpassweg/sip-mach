package game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import game.AroundGame.EndScreen;
import game.AroundGame.StartScreen;
import game.AroundGame.Window;

import java.lang.ClassCastException;
import java.lang.System;

public class GameWindow {
	
	private static final String[] modes = {"Downfall", "Mayhem", "Tease", "AI"};
	
	private static final String spaceshipSkin = "graphics/RoundSpaceShip.png";
	private static String meteorSkin = "";
	private static final String backgroundSkin= "graphics/background1.png";
	private static final double[] scalings = {100.0/78, 73.0/100, 42.0/50, 2.65};		//only temporary
	
	private Window window;
	private Player player;
	public ArrayList<Meteor> meteors;
	public ArrayList<Missle> missles;
	public ArrayList<Shot> shots;
	
	private int stepcounter = 0; // also score
	private int highscore;
	
	private int screenWidth;
	private int screenHeight;
	
	private String meteor = "";
	private Constructor<?> meteorConstructor;
	private double meteorRate;
	private int maxRad;
	
	private Random rand;
	
	private StartScreen sc;
	private EndScreen ec;
	private int scalingIndex = 1;
	
	private long time = 0; //time for framerate

	public GameWindow(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		window = new Window("Pixels", screenWidth, screenHeight);
		window.setResizable(false);
		window.open();
		
		player = new Player(screenWidth, screenHeight, spaceshipSkin);
		
		meteors = new ArrayList<Meteor>();
		missles = new ArrayList<Missle>();
		shots = new ArrayList<Shot>();
		
		
		highscore = 0;

		rand = new Random();		
		
		sc = new StartScreen(window);
	
	}
	
	private int giveMaxRad(Meteor met) {
		try {
			scalingIndex = 0;
			return ((DownfallMeteor) met).maxRad;
		} catch (ClassCastException ex) {}
		try {
			scalingIndex = 1;
			return ((MayhemMeteor) met).maxRad;
		} catch (ClassCastException ex) {}
		try {
			scalingIndex = 2;
			return ((TeaseMeteor) met).maxRad;
		} catch (ClassCastException ex) {}
		try {
			scalingIndex = 3;
			return ((AIMeteor) met).maxRad;
		} catch (ClassCastException ex) {}
		return 1;
	}

	public void run() {

		/*
		 * Set settings -> not best place, should relocate
		 */
		
		assert(window.isOpen());
		while(sc.mode.equals("game.")) {
			window.refresh();
		}
		meteor += sc.mode;
		
		for(int i = 0; i < sc.skinButtons.size(); i++) {
			if(sc.skinButtons.get(i).selected) {
				meteorSkin = sc.skinspaths[i];
				break;
			}
		}
		window.removeAllComponents();
		
		
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
		
		while (window.isOpen()) {
			
			handleCollisions();
			handleMeteors();
			handleMissles(time);
			handleTime();
			time = System.currentTimeMillis();
			handlePlayer();
			handleShots();
			draw();
			
			stepcounter++;
			window.refreshAndClear(100);
			
		}
	}
	
	private void handleTime() {
		window.setFontSize(10);
		window.drawString("FPS: " + Long.toString(1000 / (System.currentTimeMillis() - time)), window.getWidth() * 0.9, window.getHeight() * 0.05);
	}

	private void handleCollisions() {
		if (Collision.collides(player, meteors)) {
			if (stepcounter > highscore)
				highscore = stepcounter;
			reset();
		}
		switch(meteor) {
		case "game.AIMeteor":
			Collision.shotMeteorC(shots, meteors, window);
			break;
		default:
			Collision.shotMeteor(shots, meteors);
			break;
		}
	}
	
	private void handleMeteors() {
		// update or remove if out of screen
		for (int i = 0; i < meteors.size(); i++) {
			if (meteors.get(i).y - meteors.get(i).radius > screenHeight) {
				meteors.remove(i);
			} else {
				meteors.get(i).update();
			}
		}

		if (rand.nextDouble() < meteorRate / 5.0) {
			try {
				Class<?> meteorClass;
				switch (meteor) {
				case "game.TeaseMeteor":
					meteorClass = Class.forName(meteor);
					meteorCreation(meteorClass, 1, player.x - (player.playerWidth/2), 5);
					break;
				case "game.AIMeteor":
					meteorClass = Class.forName(meteor);
					meteorCreation(meteorClass, 0.01, player.x, player.y);
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

	private void meteorCreation(Class<?> meteorClass, double rate, int x, int other) throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		meteorConstructor = meteorClass.getConstructor(Integer.TYPE, Integer.TYPE, Double.TYPE, Integer.TYPE, Integer.TYPE);
		meteors.add((Meteor) meteorConstructor.newInstance(screenWidth, screenHeight, rate, x, other));
	}
	
	private void handleMissles(long time) {
		for(int i = 0; i < missles.size(); i++) {
			//missles.get(i).lifespan -= time / 1000;
			if(missles.get(i).lifespan <= 0) {
				missles.remove(i);
				System.out.println("hello");
				i--;
			} else {
				missles.get(i).update();
			}
		}
		if(stepcounter % 200 == 0) {
			missles.add(new HeatSeekingMissle(window.getWidth()/2, window.getHeight()/2, 5, 15, player.x, player.y));
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

	private void handleShots() {
		if (window.wasKeyTyped("space")) {
			if (player.shotCounter > 0) {
				shots.add(new Shot(player.x, player.y));
				player.shotCounter--;
			} 
		}
	}

	private void draw() {
		window.setColor(0, 0, 0);
		window.fillRect(0, 0, window.getWidth(), window.getHeight());
		window.drawImage(backgroundSkin, 0, 0, (double) screenWidth/200.0);
		window.setColor(139, 69, 19);
		
		/*	FOR TEASE METEOR ONLY:
		 * 		apparently scaling was radius / maxRadius * 42/50 but don't ask me why. Be happy it works
		 * 
		 *		works for:
		 *  	-> AmericanFlagMeteor
		 *  	-> SwissCheeseMeteor
		 *  	
		 *  	does not work for
		 *  	-> meteorite1	(not even close)
		 *  
		 *  FOR ACTUAL GAME:
		 *  	Scaling is 50/38. Why? Ask god once you are dead. 
		 *  	
		 *  	Note: 
		 *  	It's not pixel-perfect, but actually good enough. 
		 *  	If you don't trust me: check by enabling drawCircle and running it before and after drawImage
		 *  
		 *  UPDATE: 
		 *  	Above is a lie. 50/38 scaling only works for downfall, not for mayhem
		 *  
		 *  	TODO: fix it for good
		 */
		
		for (int i = 0; i < meteors.size(); i++) {
			
			window.drawImageCentered(meteorSkin, meteors.get(i).x, meteors.get(i).y, (double) meteors.get(i).radius / maxRad * scalings[scalingIndex]);
			window.drawCircle(meteors.get(i).x, meteors.get(i).y, meteors.get(i).radius);
		}

		for(int i = 0; i < missles.size(); i++) {
			window.setColor(255, 255, 255);
			window.fillRect(missles.get(i).x, missles.get(i).y, missles.get(i).width, missles.get(i).height);
			window.drawLine(player.x, player.y, player.x - Math.cos(missles.get(i).angle) * 100, player.y - Math.sin(missles.get(i).angle) * 100);
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

	private void drawBoostCounter() {
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

	private void drawStats() {
		window.setColor(255, 255, 255);
		window.setFontSize(12);
		window.drawString("Score: " + stepcounter, window.getWidth() * 0.45, window.getHeight() * 0.1);
		window.drawString("Highscore: " + highscore, window.getWidth() * 0.45, window.getHeight() * 0.11);
		window.drawString("Available shots: " + player.shotCounter, window.getWidth() * 0.1, window.getHeight() * 0.1);
	}

	private void reset() {
		stepcounter = 0;
		meteors.clear();
		missles.clear();
		player.reset();
		shots.clear();
		
		playEndSequence();
	}
	
	private void playEndSequence() {
		ec = new EndScreen(window);
		while(!ec.restart) {
			window.refresh(20);
		}
		window.removeAllComponents();
	}
}
