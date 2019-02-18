package game;

public class HeatSeekingMissle extends Missle{

			
	HeatSeekingMissle(double x, double y, double width, double height, int playerX, int playerY){
		super(x, y, width, height);
		phi = Math.atan((playerX - x)/(playerY - y));
		
		path = "graphics/HeatSeekingMissle.png";
	}
	
	public void update(int playerX, int playerY) {
		speed = Math.min(speed + 0.1, 3);
		double newPhi = Math.atan((playerX - x)/(playerY - y));
		if(!(Math.abs(newPhi - phi) <= 2 * angle)) {
			if(newPhi < phi) {
				phi -= 2 * angle;
			} else {
				phi += 2 * angle;
			}
		}
		updatePosition();
	}
	
}
