package game;

public class Player {

	int x;
	int y = 200;
	int screenWidth;
	double speed;
	double acc = 1;
	int playerWidth = 10;
	int plyerHeight = 10;
	int boostCounter;

	
	Player(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		x = (screenWidth / 2) + (playerWidth / 2);
		y = (int) (screenHeight * 0.8);
		speed = 0;
		boostCounter = 5;
	}
	
	void move() {
		x += speed;
		if(x < -10) {
			x = screenWidth + 9;
		} else if( x > screenWidth + 10) {
			x = -9;
		}
	}
	void move(int movement) {
		x += speed;
		if(x < -10) {
			x = screenWidth + 9;
		} else if( x > screenWidth + 10) {
			x = -9;
		}
		if(movement != 0 && boostCounter > 0) {
			boostCounter--;
			if(Math.abs(speed) <= 4) {
				speed += acc * movement;
			} else {
				if(speed < 0) {
					speed = -3.5;
				} else {
					speed = 3.5;
				}
			}
		}
		
	}


	public void addBoost() {
		if(boostCounter < 5) boostCounter++;
	}
	
}
