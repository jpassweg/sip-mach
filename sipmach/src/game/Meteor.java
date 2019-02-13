package game;
import java.util.Random;

public class Meteor {

	int x;
	int y;
	int size;
	int velX;
	int velY;
	
	Random rand;
	
	Meteor(Window window){
		this.rand = new Random();
		this.x = rand.nextInt((int) window.getWidth());
		this.y = 0;
		this.size = rand.nextInt(10) + 2;
		this.velX = rand.nextInt(3) - 1;
		this.velY = rand.nextInt(1) + 1;
	}
	
	void update() {
		x += velX;
		y += velY;
	}
}
