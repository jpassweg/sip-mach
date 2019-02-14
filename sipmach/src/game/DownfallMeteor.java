package game;

import java.util.Random;

public class DownfallMeteor extends Meteor {

	double rate = 0.001;
	
	public DownfallMeteor(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		Random rand = new Random();
		y = - (screenHeight/5);
		x = rand.nextInt((int) (screenWidth));
		
		radius = rand.nextInt(48) + 15;
		
		velY = ((double) (rand.nextInt(10) + 1)) / 10;
	}

}
