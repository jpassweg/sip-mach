package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements Runnable, KeyListener {
	
	private double x;
	private final double y = 200;	//or smth like window.height * 0.8
	private double speed;			//negative speed -> left; pos. speed -> right
	private final double acc = 0.5;
	private ObjectManager parent;
	private boolean active;
	
	Player(){
		this.x = 300;	//window width/2
		this.speed = 0;
	}
	
	//i < 0 for left acc, i = 0 for no acc, i > 0 for right acc
	public void setX(int i) {
		x += speed;
		if(i < 0) {
			speed -= acc;
		}
		if(i > 0) {
			speed += acc;
		}
	}
	
	
	public void update() {
		parent.update();
	}

	public synchronized void setParent(ObjectManager objectManager) {
		this.parent = objectManager;
	}

	@Override
	public void run() {
		active = true;
		System.out.println("player is running");
		while(active) {
			System.out.println(x);
			try {
				Thread.sleep(100);
				x += speed;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 69:
		case 88:
			System.out.println("exit game");
			active = false;
			break;
		case 37:
			System.out.println("left");
			speed--;;
			break;
		case 32:
		case 38:
			System.out.println("shoot");
			break;
		case 39:
			System.out.println("right");
			speed++;
			break;
		default:
			break;
		} 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
