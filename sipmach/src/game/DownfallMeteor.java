package game;

public class DownfallMeteor extends Meteor {
	
	int maxRad = 48 + 15 - 1;
	
	public DownfallMeteor(int screenWidth, int screenHeight, double rate) {
		super(screenWidth, screenHeight, 0.1);
		defRadius(48, 15);
	}
}
