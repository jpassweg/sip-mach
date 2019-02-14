package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	int x;
	int y = 200;
	int screenWidth;
	double speed;
	double acc = 1;
	int playerWidth = 20;
	int playerHeight = 20;
	int boostCounter;

	Player(int screenWidth, int screenHeight, String imgSource) {
		BufferedImage bimg;
		try {
			bimg = ImageIO.read(new File(imgSource));
			playerWidth = bimg.getWidth();
			playerHeight = bimg.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.screenWidth = screenWidth;
		x = (screenWidth / 2) + (playerWidth / 2);
		y = (int) (screenHeight * 0.8);
		speed = 0;
		boostCounter = 5;
	}

	void move() {
		x += speed;
		if (x < -10) {
			x = screenWidth + 9;
		} else if (x > screenWidth + 10) {
			x = -9;
		}
	}

	void move(int movement) {
		x += speed;
		if (x < -10) {
			x = screenWidth + 9;
		} else if (x > screenWidth + 10) {
			x = -9;
		}
		if (movement != 0 && boostCounter > 0) {
			boostCounter--;
			if (Math.abs(speed) <= 4) {
				speed += acc * movement;
			} else {
				if (speed < 0) {
					speed = -3.5;
				} else {
					speed = 3.5;
				}
			}
		}

	}

	public void addBoost() {
		if (boostCounter < 5)
			boostCounter++;
	}

	void reset() {
		x = (screenWidth / 2) + (playerWidth / 2);
		speed = 0;
		boostCounter = 5;
	}

}
