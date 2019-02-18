package game;

public class EndScreenButton implements Drawable, Hoverable, Clickable{
	
	private double x;
	private double y;
	private double width;
	private double height;
	private String text;
	double adjust = 25;
	boolean hovered = false;
	EndScreen screen;
	
	EndScreenButton(double x, double y, double width, double height, String text, EndScreen screen){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.screen = screen;
	}

	@Override
	public void draw(Window window) {
		window.setFontSize(40);
		window.setColor(255, 255, 255);
		window.drawString(text, x + adjust, y + 1.2 * adjust);
		if(hovered) {
			window.setStrokeWidth(2);
			window.drawLine(x, y + 1.5 * adjust, x + width, y + 1.5 * adjust);
		}
	}
	
	@Override
	public void onLeftClick(double x, double y) {
		if(getBoundingBox().contains(x, y)) {
			if(text.equals("Try Again")) {
				screen.restart = true;
			} else if(text.equals(" Credits ")) {
				screen.rollCredits();
			}
		}
	}
	
	@Override
	public void onRightClick(double x, double y) {
		
	}
	
	@Override
	public void onMouseEnter() {
		hovered = true;
	}
	
	@Override
	public void onMouseExit() {
		hovered = false;
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(x, y, width, height);
	}
}
