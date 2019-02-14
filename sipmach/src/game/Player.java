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
		if(speed >= 10) {
			return;
		}
		
		speed += acc * movement;
	}
	
	//not currently used
	void setAcceleration(int movement) {
		speed += acc * movement;
	}
}
