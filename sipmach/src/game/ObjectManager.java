package game;

import java.util.LinkedList;
import java.util.stream.Collectors;

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
    } 
	
	public void update() {
		window.update(player, meteors);
	}
	
}
