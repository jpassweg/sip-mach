package game;
import java.util.Random;
import java.util.LinkedList;
import java.util.ArrayList;

public class Main {
	
	static ArrayList<Meteor> meteors = new ArrayList<Meteor>();
	static Random rand;
	static int stepcounter = 0;
	
	public static void main(String[] args) {
		
		Window window = new Window("Try", 200, 300);
		Player player = new Player(window);
		rand = new Random();
		window.setResizable(false);
		window.open();
		while(window.isOpen()) {
			for(int i = 0; i < meteors.size(); i++) {
				meteors.get(i).update();
			}
			draw(window, player);
			if(window.isKeyPressed("left")) {
				player.move(-1, window);
			} else if(window.isKeyPressed("right")) {
				player.move(1, window);
			} else {
				player.move(0, window);
			}
			window.refreshAndClear();
			stepcounter++;
			if(rand.nextInt(50 - Math.min(49, (int) stepcounter/50)) == 0) {
				meteors.add(new Meteor(window));
			}
		}
	}
	
	static void draw(Window window, Player player) {
		window.setColor(0,0,0);
		window.fillRect(0, 0, window.getWidth(), window.getHeight());
		window.setColor(139,69,19);
		for(int i = 0; i < meteors.size(); i++) {
			window.fillCircle(meteors.get(i).x, meteors.get(i).y, meteors.get(i).size);
		}
		window.setColor(255,255,255);
		window.fillRect(player.x, player.y, 10, 30);
	}

}
