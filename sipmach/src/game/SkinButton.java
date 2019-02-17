package game;

public class SkinButton implements Drawable, Clickable {

	private double y;
	private double x;
	private double width;
	private double height;
	private String text;
	private int adjust = 25;
	private StartScreen screen;
	public boolean selected = false;
	
	SkinButton(double x, double y, double width, double height, String text, StartScreen screen){
		this.y = y;
		this.x = x;
		this.width = width;
		this.height = height;
		this.text = text;
		this.screen = screen;
	}

	
	@Override
	public void draw(Window window) {
		if(selected) {
			window.setColor(255, 0, 255);
		} else {
			window.setColor(255, 255, 255);
		}
		window.fillRect(x, y, width, height);
		window.setColor(0, 0, 0);
		window.drawString(text, x + 2 * adjust, y + adjust);
	}
	
	@Override
	public void onLeftClick(double x, double y) {
		if(!getBoundingBox().contains(x, y)) {
			return;
		}
		if(!selected) {
			for(int i = 0; i < screen.skinButtons.size(); i++) {
				screen.skinButtons.get(i).selected = false;
			}
			selected = true;
		}
	}
	
	@Override
	public void onRightClick(double x, double y) {}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(x, y, width, height);
	}
	
}
