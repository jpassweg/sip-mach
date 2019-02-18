package game;


public class TeaseMeteor extends Meteor {
	
	int maxRad = 40;

	public TeaseMeteor(int screenWidth, int screenHeight, double rate, int leftPlayerX, int rightPlayerX) {
		super(screenWidth, screenHeight, rate);
		defRadius(40, 1);
		
		x = leftPlayerX - radius - 1;
		y = - (int) (screenHeight/5.0);
		velY = 2;
	}
	
	public TeaseMeteor(int screenWidth, int screenHeight, double rate) {
		super(screenWidth, screenHeight, rate);
	}
}
