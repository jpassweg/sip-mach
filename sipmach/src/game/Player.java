package game;


public class Player {

	static int x;
	static final int y = 200;
	double speed;
	double acc = 0.1;
	
	Player(Window window){
		this.x = (int) window.getWidth()/2;
		this.speed = 0;
	}
	
	void move(int i, Window window) {
		x += speed;
		if(x < -10) {
			x = (int) window.getWidth() + 10;
		} else if( x > window.getWidth() + 10) {
			x = -10;
		}
		if(speed >= 10) {
			return;
		}
		
		if(i < 0) {
			speed -= acc;
		}
		if(i > 0) {
			speed += acc;
		}
	}
}
