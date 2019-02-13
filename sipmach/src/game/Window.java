package game;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import java.lang.InterruptedException;
public class Window implements Runnable {
	
	Window() throws InterruptedException{
		JFrame frame = new JFrame("Hello Bitches");
		setup(frame);
	}
	
	void setup(JFrame frame) throws InterruptedException{
		frame.setBackground(Color.BLACK);
		frame.setSize(600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panels(frame);
	}
	
	void panels(JFrame frame) throws InterruptedException {
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1);
		Meteor met = new Meteor();
		panel1.add(met);
		frame.setVisible(true);
		
		while(true) {
			met.update();
			panel1.repaint();
			try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	
	public void run() 
    { 
		System.out.println("Window" + Thread.currentThread().getId() + " is running!");
		
    } 
	
	public void update(Player playerX, LinkedList<Meteor> meteors) {
		
	}
}
