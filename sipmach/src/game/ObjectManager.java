package game;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ObjectManager extends Thread {
	private Window window;
	private Player player;
	private double playerX;
	private List<Meteor> meteors;
	
	
	public void run() 
    { 
        System.out.println("ObjectManager" + Thread.currentThread().getId() + " is running!");
        
        window = new Window();
		window.start();
		
		
		player = new Player();
		player.setParent(this);
		player.start();
		
    } 
	
	public void updatePlayer(double x) {
		playerX = x;
		Predicate<? super Meteor> active;
		List<Point> meteorsLoc = meteors.stream()
			    .filter(active)
			    .map(i -> i.getLocation())
			    .collect(Collectors.toList());
		window.update(playerX, meteorsLoc);
	}
	
}
