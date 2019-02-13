package game;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ObjectManager implements Runnable {
	private Window window;
	private Player player;
	private double playerX;
	private List<Meteor> meteors;
	
	
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
	
	public void updatePlayer(double x) {
		playerX = x;
		Predicate<? super Meteor> active = null;
		List<Point> meteorsLoc = meteors.stream()
			    .filter(active)
			    .map(i -> i.getLocation())
			    .collect(Collectors.toList());
		window.update(playerX, meteorsLoc);
	}
	
}
