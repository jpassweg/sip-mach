package game;

public class Shot {

	double x;
	double y;
	double speed = 0.1;
	double length = 20;
	
	
	Shot(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	void draw(Window window) {
		window.setColor(255, 0, 0);
		window.drawLine(x, y, x, y - length);
		update();
	}
	
	void update() {
		y -= speed;
	}	
}
