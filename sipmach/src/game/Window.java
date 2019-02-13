package game;

public class Window extends Thread {
	public void run() 
    { 
		System.out.println("Window" + Thread.currentThread().getId() + " is running!");
		
    } 
	
	public void update(double playerX, Point[] meteors) {
		
	}
}
