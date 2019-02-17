package game;

import java.util.ArrayList;

class StartScreen implements Drawable {
	
	private static final String[] modes = {"Downfall", "Mayhem", "Tease"};
	private static final String[] meteors = {"DownfallMeteor", "MayhemMeteor", "TeaseMeteor"};
	
	private static double width;
	private static double height;
	
	private static double leftBoxX;
	private static double middleBoxX;
	private static double rightBoxX;
	private static double boxWidth;
	private static double boxHeight;
	private static double highestBoxY;
	public static String mode = "game.";
	
	
	
	StartScreen(Window window){
		
		window.addComponent(this);
		
		width = window.getWidth();
		height = window.getHeight();
		
		leftBoxX 	= width  * 0.1;
		middleBoxX 	= width  * 0.35;
		rightBoxX	= width  * 0.6;
		boxWidth 	= width  * 0.3;
		boxHeight 	= height * 0.05;
		highestBoxY = height * 0.5 + boxHeight/2 - (modes.length - 1) / 2 * boxHeight * 2;
		
		
		
		window.setFontSize(20);
		
		boolean left = true;
		int counter = 0;
	
		
		for(int i = 0; i < modes.length; i++) {
			if(!(i == modes.length - 1)) {
				if(left) {
					window.addComponent(new ModeBox(leftBoxX, boxY(counter), boxWidth, boxHeight, modes[i], this));
				} else {
					window.addComponent(new ModeBox(rightBoxX, boxY(counter), boxWidth, boxHeight, modes[i], this));
				}
			} else {
				if(left) {
					window.addComponent(new ModeBox(middleBoxX, boxY(counter), boxWidth, boxHeight, modes[i], this));
				} else {
					window.addComponent(new ModeBox(rightBoxX, boxY(counter), boxWidth, boxHeight, modes[i], this));
				}
			}
			left = !left;
			if(left) {
				counter++;
			}
		}	
		
		
	}
	
	@Override
	public void draw(Window window) {
		window.setColor(0,0,0);
		window.fillRect(0, 0, width, height);
	}

	private static double boxY(int counter) {
		return highestBoxY + counter * 2 * boxHeight;
	}
	
	static String getMode(Window window) {
		
		while(true) {
			if(!(mode.equals("game."))) {
				return mode;
			}
		}
	}
}
