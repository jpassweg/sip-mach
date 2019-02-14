package game;

public class Player {

	int x;
	int y = 200;
	int screenWidth;
	double speed;
	double acc = 0.1;
	int playerWidth = 10;

	
	Player(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		x = (screenWidth / 2) + (playerWidth / 2);
		y = (int) (screenHeight * 0.8);
		speed = 0;
		
	}
	
	
	void move(int movement) {
		x += speed;
		if(x < -10) {
			x = (int) screenWidth + 10;
		} else if( x > screenWidth + 10) {
			x = -10;
		}
		if(Math.abs(speed) >= 4) {
			if(speed < 0) {
				speed = -4;
			} else {
				speed = 4;
			}
		}
		speed += acc * movement;
	}
	
	//not currently used
	void setAcceleration(int movement) {
		speed += acc * movement;
	}
	
	void drawBoostCounter(Window window, int boostCounter) {
		window.setColor(255,255,255);
		double baseX = window.getWidth()*0.85;
		double baseY = window.getHeight()*0.1;
		double lenY = window.getHeight()*0.02;
		double lenX = window.getWidth()*0.1 / 5.5;
		double dis = window.getWidth()*0.001;
		window.drawRect(baseX, baseY, window.getWidth()*0.097, lenY);
		window.setColor(255,0,0);
		for(int i = 0; i < boostCounter; i++) {
			window.fillRect(baseX + lenX* 4 + dis * 5 - lenX * i  - dis * i, baseY + 0.25, lenX, lenY-0.25);
			
		}
	}
}
