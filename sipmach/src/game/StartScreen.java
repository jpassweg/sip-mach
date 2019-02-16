package game;

public class StartScreen {
	
	private static final String[] modes = {"Downfall", "Mayhem", "Tease"};
	
	private static double width;
	private static double height;
	
	private static double leftBoxX;
	private static double middleBoxX;
	private static double rightBoxX;
	private static double boxWidth;
	private static double boxHeight;
	private static double highestBoxY;
	private static int scale = 10;
	
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
		
		boolean left = true;
		int counter = 0;
		window.setColor(220, 220, 220);
		
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
		}	
		window.refresh();
	}
	
	static void drawBox(double boxX, int counter, int i, Window window) {
		window.fillRect(boxX, highestBoxY + counter * 2 * boxHeight, boxWidth, boxHeight);
		window.drawString(modes[i], boxX + scale, highestBoxY + counter * 2 + scale);
	}

}
