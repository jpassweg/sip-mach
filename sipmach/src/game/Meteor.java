package game;

public class Meteor {

	double x = 0;
	double y = 0;
	int radius = 0;
	double velX = 0;
	double velY = 0;
	
	double rate = 1;
	
	public Meteor(int screenWidth, int screenHeight, double rate) {
		this.rate = rate;
	}
	
	public void update() {
		x += velX;
		y += velY;
	}
}
