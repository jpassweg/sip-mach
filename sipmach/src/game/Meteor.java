package game;

public class Meteor {

	double x = 0;
	double y = 0;
	int radius = 0;
	double velX = 0;
	double velY = 0;
	
	public Meteor(int screenWidth, int screenHeight) {}
	
	void update() {
		x += velX;
		y += velY;
	}
}
