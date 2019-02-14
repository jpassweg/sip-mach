package game;

public class Player {

	int x;
	int y = 200;
	double speed;
	double acc = 0.1;
	int playerWidth = 10;
	
	Player(int screenWidth, int screenHeight){
		x = (screenWidth / 2) + (playerWidth / 2);
		y = (int) (screenHeight * 0.8);
		speed = 0;
	}
	
	void move(int i, int screenWidth) {
		x += speed;
		if(x < -10) {
			x = (int) screenWidth + 10;
		} else if( x > screenWidth + 10) {
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
