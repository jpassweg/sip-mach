package game;

import java.util.Random;

public class MayhemMeteor extends Meteor {

	public MayhemMeteor(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		Random rand = new Random();
		y = - (screenHeight/5);
		x = rand.nextInt((int) (screenWidth));
		
		radius = rand.nextInt(18) + 4;
		
		double ration = (double) screenHeight / (double) screenWidth;
		velY = ((double) (rand.nextInt(10) + 1)) / 10;
		velX = (rand.nextDouble() * (2.0*velY/ration)) - (velY/ration);
	}
}
