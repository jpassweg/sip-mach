package game;

import java.util.Random;

public class Missle {

	double x;
	double y;
	double width;
	double height;
	double speed;
	double phi;
	double maxSpeed = 3;
	double lifespan;
	
	String path = "graphics/DefaultMissle.png";
	Random rand = new Random();
	final double angle = 2 * Math.PI / 360;
	
	public Missle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = 0;
		this.phi = rand.nextDouble() * 2 * Math.PI;
		this.lifespan = 4;
	}
	
	public void update() {
		updatePosition();
		randomAcc();
	}
	
	protected void updatePosition() {
		x += speed * Math.cos(phi);
		y += speed * Math.sin(phi);
	}
	
	protected void randomAcc() {
		speed += Math.min((maxSpeed - speed) / 10 , 0.3);
		if(rand.nextDouble() >= 0.7) {
			phi -= angle;
		} else {
			phi += angle;
		}
	}
}
