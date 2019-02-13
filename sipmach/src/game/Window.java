package game;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import java.lang.InterruptedException;


public class Window implements Runnable {
	
	JPanel panel;
	
	
	Window() throws InterruptedException{
		JFrame frame = new JFrame("Hello Bitches");
		setup(frame);
	}
	
	void setup(JFrame frame) throws InterruptedException{
		frame.setBackground(Color.BLACK);
		frame.setSize(600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);;
	}

	
	
	public void run() 
    { 
		System.out.println("Window" + Thread.currentThread().getId() + " is running!");
		
    } 
	
	public void update(Player playerX, LinkedList<Meteor> meteors) {
		for(int i = 0; i < meteors.size(); i++) {
			meteors.get(i).update();
			if(meteors.get(i).isOut()) {
				meteors.remove(i);
				meteors.add(new Meteor());
				panel.add(meteors.getLast());
			}
		}
		
		//TODO: update player
	}
}
