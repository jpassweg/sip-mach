package game;

public class Player {

	int x;
	int y = 200;
	int screenWidth;
	double speed;
	double acc = 0.1;
	int playerWidth = 10;
	int boostCounter;
	
	Player(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		x = (screenWidth / 2) + (playerWidth / 2);
		y = (int) (screenHeight * 0.8);
		speed = 0;
		this.boostCounter = 5;
	}
	
	
	void move(int movement) {
		x += speed;
		if(x < -10) {
			x = (int) screenWidth + 10;
		} else if( x > screenWidth + 10) {
			x = -10;
		}
		if(speed >= 4) {
			speed = 4;
		}
		speed += acc * movement;
	}
	
	//not currently used
	void setAcceleration(int movement) {
		speed += acc * movement;
	}
	
	void drawBoostCounter(Window window) {
		window.setColor(255,255,255);
		window.drawRect(window.getWidth()*0.9, window.getHeight()*0.1, window.getWidth()*0.98, window.getHeight()*0.02);
		
	}
}
