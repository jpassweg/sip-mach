package game;

import java.util.LinkedList;

public class ObjectManager implements Runnable {
	private Window window;
	private Player player;
	private LinkedList<Meteor> meteors;
	
	
	public void run() 
    { 
        System.out.println("ObjectManager" + Thread.currentThread().getId() + " is running!");
        
        Thread tWindow;
        try {
        	window = new Window();
			tWindow = new Thread(window);
			tWindow.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
        Thread tPlayer;
        player = new Player();
		player.setParent(this);
		tPlayer = new Thread(player);
		tPlayer.start();
		
		while(tPlayer.isAlive()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//window.stop() add synchronized method in window that stops it
		System.out.println("exit game from object manager");
		
    } 
	
	public void update() {
		window.update(player, meteors);
	}
	
}
