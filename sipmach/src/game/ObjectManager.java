package game;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ObjectManager implements Runnable {
	private Thread window;
	private Thread player;
	private double playerX;
	private List<Meteor> meteors;
	
	
	public void run() 
    { 
        System.out.println("ObjectManager" + Thread.currentThread().getId() + " is running!");
        
        try {
			window = new Thread(new Window());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window.start(); 
		
		
		player = new Thread(new Player());
		//player.setParent(this);
		player.start();
		
    } 
	
	public void updatePlayer(double x) {
		playerX = x;
		Predicate<? super Meteor> active = null;
		List<Point> meteorsLoc = meteors.stream()
			    .filter(active)
			    .map(i -> i.getLocation())
			    .collect(Collectors.toList());
		//window.update(playerX, meteorsLoc);
	}
	
}
