package game;

import java.util.Random;

public class DownfallMeteor extends Meteor {
	public DownfallMeteor(int screenWidth, int screenHeight, double rate) {
		super(screenWidth, screenHeight, 0.1);
		Random rand = new Random();
		y = - (screenHeight/5);
		x = rand.nextInt((int) (screenWidth));
		
		radius = rand.nextInt(48) + 15;
		
		velY = ((double) (rand.nextInt(10) + 1)) / 10;
	}

}
