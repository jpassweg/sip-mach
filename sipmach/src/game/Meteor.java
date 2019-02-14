package game;
import java.util.Random;

public class Meteor {

	double x;
	double y;
	int size;
	double velX;
	double velY;
	
	Random rand;
	
	Meteor(int screenWidth, int screenHeight){
		this.rand = new Random();
		this.y = - (screenHeight/5);
		this.x = rand.nextInt((int) (screenWidth));
		this.size = rand.nextInt(18) + 4;
		
		this.velY = ((double) (rand.nextInt(10) + 1)) / 10;
		this.velX = (rand.nextDouble() * (2.0*velY/0.6)) - (velY/0.6);
	}
	
	void update() {
		x += velX;
		y += velY;
	}
}
