package game;

public class ObjectManager extends Thread {
	private Window window;
	private Player player;
	private double playerX;
	private Meteor[] meteors;
	
	
	public void run() 
    { 
        System.out.println("ObjectManager" + Thread.currentThread().getId() + " is running!");
        
        window = new Window();
		window.start();
		
		/*
		player = new Player();
		player.setParent(this);
		player.start();
		*/
		
    } 
	
	public void updatePlayer(double x) {
		playerX = x;
		window.update(playerX, meteors.translate());
	}
	
}
