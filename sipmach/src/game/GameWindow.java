package game;

import java.util.ArrayList;
import java.util.Random;

public class GameWindow {
	Window window;
	Player player;
	ArrayList<Meteor> meteors;
	Random rand;
	int stepcounter = 0;
	int screenWidth;
	int screenHeight;
	int boostCounter;
	int timeSteps;
	boolean movable;

	public GameWindow(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		window = new Window("Pixels", screenWidth, screenHeight);
		meteors = new ArrayList<Meteor>();
		this.boostCounter = 5;
		this.timeSteps = 0;
		this.movable = true;
	}

	public void run() {
		player = new Player(screenWidth, screenHeight);
		rand = new Random();
		window.setResizable(false);
		window.open();

		int movement = 0;

		while (window.isOpen()) {

			draw();

			for (int i = 0; i < meteors.size(); i++) {
				Meteor curr = meteors.get(i);
				if (Math.sqrt(Math.pow((curr.x - player.x), 2) + Math.pow((curr.y - player.y), 2)) < curr.size) {
					System.out.println("collision: (" + curr.x + "," + curr.y + ") - " + curr.size);
				}

				if (curr.y > screenHeight) {
					meteors.remove(curr);
				} else {
					curr.update();
				}
			}
			stepcounter++;
			if (rand.nextInt(50 - Math.min(45, (int) stepcounter / 50)) == 0) {
				meteors.add(new Meteor(screenWidth, screenHeight));
			}
			if (stepcounter % 500 == 0 && boostCounter < 5) {
				boostCounter++;
			}
			if (!movable) {
				timeSteps++;
				if (timeSteps >= 200) {
					timeSteps = 0;
					movable = true;
				}
			}

			movement = 0;
			if (movable) {
				if (window.isKeyPressed("left")) {
					movement = -1;
					boostCounter--;
					movable = false;
				} else if (window.isKeyPressed("right")) {
					movement = 1;
					boostCounter--;
					movable = false;
				}
			}
			player.move(movement);

			window.refreshAndClear(5);

		}
	}

	void draw() {
		window.setColor(0, 0, 0);
		window.fillRect(0, 0, window.getWidth(), window.getHeight());
		window.setColor(139, 69, 19);
		for (int i = 0; i < meteors.size(); i++) {
			window.fillCircle(meteors.get(i).x, meteors.get(i).y, meteors.get(i).size);
		}
		window.setColor(255, 255, 255);
		window.fillRect(player.x, player.y, 10, 30);
		player.drawBoostCounter(window, boostCounter);
	}

}
