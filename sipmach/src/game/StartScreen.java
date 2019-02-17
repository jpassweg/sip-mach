package game;

import java.util.ArrayList;

class StartScreen implements Drawable {
	
	private static final String[] modes = {"Downfall", "Mayhem", "Tease"};
	private static final String[] meteors = {"DownfallMeteor", "MayhemMeteor", "TeaseMeteor"};
	public static final String[] skinspaths = {"graphics/AmericanFlagMeteor.png",
	   									   		"graphics/SwissCheeseMeteor.png"};
	private static final String[] skinnames = {"American", "Cheese"};
	
	private static double width;
	private static double height;
	
	private static double leftBoxX;
	private static double middleBoxX;
	private static double rightBoxX;
	private static double boxWidth;
	private static double boxHeight;
	private static double highestBoxY;
	public static String mode = "game.";
	
	public static ArrayList<SkinButton> skinButtons = new ArrayList<SkinButton>();
	
	
	
	StartScreen(Window window){
		
		// add startscreen to components
		
		window.addComponent(this);
		
		width = window.getWidth();
		height = window.getHeight();
		
		leftBoxX 	= width  * 0.1;
		middleBoxX 	= width  * 0.35;
		rightBoxX	= width  * 0.6;
		boxWidth 	= width  * 0.3;
		boxHeight 	= height * 0.05;
		highestBoxY = height * 0.5 + boxHeight/2 - (modes.length - 1) / 2 * boxHeight * 2;
		
		
		//add buttons for modes to components
		
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
		
		//add buttons for skins to components
		double skinY = window.getHeight() * 0.2;
		double leftX = window.getWidth() * 0.2;
		double rightX = window.getWidth() * 0.8;
		double dist = 30;
		double skinWidth = (rightX - leftX - (skinnames.length-1) * dist) / skinnames.length;
		double skinHeight = 30;
		for(int i = 0; i < skinnames.length; i++) {
			double skinX = leftX + i * skinWidth + i * dist;
			skinButtons.add(new SkinButton(skinX, skinY, skinWidth, skinHeight, skinnames[i], this));
		}
		skinButtons.get(0).selected = true;
		for(int i = 0; i < skinButtons.size(); i++) {
			window.addComponent(skinButtons.get(i));
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
