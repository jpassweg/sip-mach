package game;

public class StartScreen {
	
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
	private static int scale = 25;
	
	static void draw(Window window) {
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
					drawBox(leftBoxX, counter, i, window);
				} else {
					drawBox(rightBoxX, counter, i, window);
				}
			} else {
				if(left) {
					drawBox(middleBoxX, counter, i, window);
				} else {
					drawBox(rightBoxX, counter, i, window);
				}
			}
			left = !left;
			if(left) {
				counter++;
			}
		}	
		window.refresh();
	}
	
	static void drawBox(double boxX, int counter, int i, Window window) {
		window.setColor(150, 150, 150);
		window.fillRect(boxX, highestBoxY + counter * 2 * boxHeight, boxWidth, boxHeight);
		window.setColor(255, 0, 0);
		window.drawString(modes[i], boxX + 2 * scale, highestBoxY + counter * 2 * boxHeight + scale);
	}
	
	static String getMode(Window window) {
		double mouseX = 0;
		double mouseY = 0;
		
		while(true) {
			if(window.wasLeftMouseButtonClicked()) {
				System.out.println("hello");
				mouseX = window.getMouseX();
				mouseY = window.getMouseY();
				System.out.println(window.getMouseX() + " " + mouseY);
				boolean left = true;
				boolean clicked = false;
				int counter = 0; 
				
				for(int i = 0; i < modes.length; i++) {
					if(!(i == modes.length - 1)) {
						if(left) {
							clicked = wasInBox(mouseX, mouseY, leftBoxX, highestBoxY + counter * 2 * boxHeight);
						} else {
							clicked = wasInBox(mouseX, mouseY, rightBoxX, highestBoxY + counter * 2 * boxHeight);
						}
					} else {
						if(left) {
							clicked = wasInBox(mouseX, mouseY, middleBoxX, highestBoxY + counter * 2 * boxHeight);
						} else {
							clicked = wasInBox(mouseX, mouseY, rightBoxX, highestBoxY + counter * 2 * boxHeight);
						}
					}
					
					if(clicked) {
						return meteors[i];
					}
					left = !left;
					
					if(left) {
						counter++;
					}
				}
			}
		}
	}
	
	static boolean wasInBox(double mouseX, double mouseY, double boxX, double boxY) {
		if(mouseX < boxX) {
			return false;
		}
		if(mouseX > boxX + boxWidth) {
			return false;
		}
		if(mouseY < boxY) {
			return false;
		}
		if(mouseY > boxY + boxHeight) {
			return false;
		}

		return true;
	}

}
