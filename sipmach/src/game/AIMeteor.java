package game;

public class AIMeteor extends Meteor{

	int maxRad = 80 + 50 - 1;
	int screenWidth;
	int screenHeight;
	
	
	public AIMeteor(int screenWidth, int screenHeight, double rate, int playerX, int playerY){
		super(screenWidth, screenHeight, 0.05);
		defRadius(80, 50);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		targetPlayerSurrounding(playerX, playerY);
	}
	
	public AIMeteor(int screenWidth, int screenHeight, double rate){
		super(screenWidth, screenHeight, 0.05);
	}
	
	private void targetPlayerSurrounding(int playerX, int playerY) {
		double surL = playerX - screenWidth * 0.2;
		double surR = playerX + screenWidth * 0.2;
		double time = screenHeight * 1.1 / velY;
		double velXmin = (surL - x) / time;
		double velXmax = (surR - x) / time;
		velX = rand.nextDouble() * (velXmax - velXmin) + velXmin;
	}
}
