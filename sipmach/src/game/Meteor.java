package game;

import java.util.Random;

public class Meteor {

	double x = 0;
	double y = 0;
	int radius = 0;
	double velX = 0;
	double velY = 0;
	
	double rate;
	
	
	Random rand = new Random();
	
	public Meteor(int screenWidth, int screenHeight, double rate) {
		this.rate = rate;
		y = - (screenHeight/5);
		x = rand.nextInt((int) (screenWidth));
		defVelY();
	}
	
	public void update() {
		x += velX;
		y += velY;
	}
	
	protected void defRadius(int var, int min) {
		radius = rand.nextInt(var) + min;
	}
	
	protected void defVelY() {
		velY = ((double) (rand.nextInt(10) + 1)) / 10;
	}
}
