package game;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import java.lang.InterruptedException;


public class Picture implements Runnable {
	
	JPanel panel;
	private Player player;
	private LinkedList<Meteor> meteors;
	private int stepcounter = 0;
	
	
	Picture() throws InterruptedException{
		JFrame frame = new JFrame("Hello Bitches");
		meteors = new LinkedList<Meteor>();
		setup(frame);
	}
	
	void setup(JFrame frame) throws InterruptedException{
		
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
		
		while(true) {
			try {
				update();
				System.out.println("im alive");
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*window.stop() add synchronized method in window that stops it
		System.out.println("exit game from object manager");
		System.out.println("What obj manager?? o.O");
		*/
		
    } 
	
	
	public void update() {
		for(int i = 0; i < meteors.size(); i++) {
			meteors.get(i).setX(0);
			meteors.get(i).setY(0);
			meteors.get(i).update();
			if(meteors.get(i).isOut()) {
				meteors.remove(i);
				meteors.add(new Meteor());
				panel.add(meteors.getLast());
			}
		}
		if(meteors.size() < 5 && stepcounter % 50 == 0) {
			meteors.add(new Meteor());
		}
		stepcounter++;
		
		//TODO: update player
		
	}
	
	
}
