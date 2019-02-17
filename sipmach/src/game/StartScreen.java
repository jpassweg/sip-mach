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
	
	private ArrayList<ModeBox> boxes = new ArrayList<ModeBox>();
	
	
	@Override
	public void draw(Window window) {
		width = window.getWidth();
		height = window.getHeight();
		
		leftBoxX 	= width  * 0.1;
		middleBoxX 	= width  * 0.35;
		rightBoxX	= width  * 0.6;
		boxWidth 	= width  * 0.3;
		boxHeight 	= height * 0.05;
		highestBoxY = height * 0.5 + boxHeight/2 - (modes.length - 1) / 2 * boxHeight * 2;
		
		
		window.setColor(0,0,0);
		window.fillRect(0, 0, width, height);
		window.setFontSize(20);
		
		boolean left = true;
		int counter = 0;
	
		
		for(int i = 0; i < modes.length; i++) {
			if(!(i == modes.length - 1)) {
				if(left) {
					boxes.add(new ModeBox(leftBoxX, boxY(counter), boxWidth, boxHeight, modes[i], this));
				} else {
					boxes.add(new ModeBox(rightBoxX, boxY(counter), boxWidth, boxHeight, modes[i], this));
				}
			} else {
				if(left) {
					boxes.add(new ModeBox(middleBoxX, boxY(counter), boxWidth, boxHeight, modes[i], this));
				} else {
					boxes.add(new ModeBox(rightBoxX, boxY(counter), boxWidth, boxHeight, modes[i], this));
				}
			}
			left = !left;
			if(left) {
				counter++;
			}
		}	
		
		for(int i = 0; i < boxes.size(); i++) {
			window.addComponent(boxes.get(i));
		}
		window.refresh(20);
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
