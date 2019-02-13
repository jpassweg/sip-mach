package game;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import java.lang.InterruptedException;


public class Window implements Runnable {
	
	JPanel panel;
	private Player player;
	private LinkedList<Meteor> meteors;
	
	
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

	
	
	public void run() { 
		
        Thread tPlayer;
        player = new Player();
		tPlayer = new Thread(player);
		tPlayer.start();
		
		while(tPlayer.isAlive()) {
			try {
				update();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//window.stop() add synchronized method in window that stops it
		System.out.println("exit game from object manager");
		System.out.println("What obj manager?? o.O");
		
    } 
	
	
	public void update() {
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
	
	public void paint(Graphics g) {
		
	}
}
