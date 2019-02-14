package game;

import java.util.Random;

public class MayhemMeteor extends Meteor {

	public MayhemMeteor(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		Random rand = new Random();
		y = - (screenHeight/5);
		x = rand.nextInt((int) (screenWidth));
		
		size = rand.nextInt(18) + 4;
		
		velY = ((double) (rand.nextInt(10) + 1)) / 10;
		velX = (rand.nextDouble() * (2.0*velY/0.6)) - (velY/0.6);
	}
}
