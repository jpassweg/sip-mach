package game;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Meteor extends JPanel {
	
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
		this.speed = rand.nextDouble() * 2;
		this.phi = rand.nextInt(90) - 45;
		this.size = rand.nextInt(50);
	}
	
	boolean isOut() {
		return y > sizeY + size;
	}
	
	void update() {
		this.x += speed * Math.sin(phi);
		this.y += speed * Math.cos(phi);
		this.repaint();
		System.out.println("x: " + this.x + "y: " + this.y);
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.green);
		g.fillOval((int) this.x, (int) this.y,  this.size, this.size);
	
		
	}
	void setX(int x) {
		this.x = x;
	}
	
	void setY(int y) {
		this.y = y;
	}
	
}
