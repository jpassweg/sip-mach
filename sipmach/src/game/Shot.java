package game;

public class Shot {

	double x;
	double y;
	double speed = 1;
	double length = 20;
	
	
	Shot(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	void draw(Window window) {
		window.setColor(255, 0, 0);
		window.drawImage("graphics/BlueShot.png", x, y - length);
		update();
	}
	
	void update() {
		y -= speed;
	}	
}
