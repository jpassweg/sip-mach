package game;
import java.util.Random;

public class Meteor {

	double x;
	double y;
	int size;
	double velX;
	double velY;
	
	Random rand;
	
	Meteor(Window window){
		this.rand = new Random();
		this.y = - window.getHeight()/5;
		this.x = rand.nextInt((int)(window.getWidth()*1.2));
		this.size = rand.nextInt(10) + 2;
		double time = y / velY;
		this.velX = (rand.nextInt(21) - 10) / 10;
		if(velX < 0) {
			velX += 1;
		} else {
			velX -= 1;
		}
		this.velY = ((double) (rand.nextInt(10) + 1)) / 10;
		//gdgv
	}
	
	
	
	void update() {
		x += velX;
		y += velY;
	}
}
