package game;

public class MayhemMeteor extends Meteor {
	
	int maxRad = 18 + 20 - 1;

	public MayhemMeteor(int screenWidth, int screenHeight, double rate) {
		super(screenWidth, screenHeight, 1.0);
		defRadius(18, 20);
		
		double ration = (double) screenHeight / (double) screenWidth;
		velX = (rand.nextDouble() * (2.0*velY/ration)) - (velY/ration);
	}
}
