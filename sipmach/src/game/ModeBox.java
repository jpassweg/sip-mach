package game;

public class ModeBox implements Drawable, Hoverable, Clickable {

	/*
	 * 		   (x,y)  ---------------------------  (x+width, y)
	 * 				  |		  some text			|
	 * (x,y + height) ---------------------------  (x+width, y + height)
	 */
	
	double x;
	double y;
	double width;
	double height;
	String text;
	int adjust = 25;
	StartScreen screen;
	
	ModeBox(double x, double y, double width, double height, String text, StartScreen screen){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.screen = screen;
	}
	
	
	@Override
	public void draw(Window window) {
		window.setColor(150, 150, 150);
		window.fillRect(x, y, width, height);
		window.setColor(255, 0, 0);
		window.drawString(text, x + 2 * adjust, y + adjust);
	}
	
	@Override
	public void onMouseEnter() {
		
	}
	
	@Override
	public void onMouseExit() {
		
	}
	
	@Override
	public void onLeftClick(double x, double y) {
		if(getBoundingBox().contains(x, y)) {
			StartScreen.mode += text;
			StartScreen.mode += "Meteor";
		}
	}
	
	@Override
	public void onRightClick(double x, double y) {
		
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(x, y, width, height);
	}
}
