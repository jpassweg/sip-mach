package game;

public class Credits implements Drawable {

	private String text = "Creators: Jonas & Jonas";
	private double x;
	private double y;
	private double speed;
	
	Credits(double x, double y){
		this.x = x;
		this.y = y;
		this.speed = 0.5;
	}
	
	@Override
	public void draw(Window window) {
		window.setColor(255,255,255);
		window.setFontSize(20);
		window.drawString(text, x, y);
		y -= speed;
	}
	
	public boolean done() {
		if(y < 0) {
			return true;
		}
		return false;
	}
}
