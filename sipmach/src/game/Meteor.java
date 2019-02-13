package game;

import javax.swing.*;
import java.util.Random;

public class Meteor extends JComponent {
	
	private double x;
	private double y;
	private double speed;
	private double phi;
	private int size;
	private static final int sizeX = 600;
	private static final int sizeY = 800;
	Random rand = new Random();
	
	Meteor(){
		this.x = rand.nextInt(sizeX * (int) 1.4) - sizeX * 0.2;
		this.y = rand.nextInt(sizeY / 5) + sizeY;
		this.speed = rand.nextDouble() * 20;
		this.phi = rand.nextInt(90) - 45;
		this.size = rand.nextInt(50);
	}
	
	void update() {
		this.x += speed * Math.sin(phi);
		this.y += speed * Math.cos(phi);
		this.repaint();
	}

	
	public Point getLoc() {
		return new Point(x,y);
	}
	
}
