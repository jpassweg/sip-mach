package game;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.lang.InterruptedException;
public class Window extends Thread {
	
	Window() throws InterruptedException{
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.show();
		while(panel.isVisible()) {
			panel.wait(50000);
			
		}
	}
	
	
	
	
	public void run() 
    { 
		System.out.println("Window" + Thread.currentThread().getId() + " is running!");
		
    } 
	
	public void update(double playerX, List<Point> meteors) {
		
	}
}
