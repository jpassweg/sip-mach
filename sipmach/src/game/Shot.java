package game;

import game.AroundGame.Window;

public class Shot {

	double x;
	double y;
	double speed = 10;
	double length = 20;
	
	
	public Shot(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Window window) {
		window.setColor(255, 0, 0);
		window.drawImageCentered("graphics/BlueShot.png", x, y - length);
		update();
	}
	
	public void update() {
		y -= speed;
	}	
}
