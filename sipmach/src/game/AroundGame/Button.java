package game.AroundGame;

public class Button implements Drawable, Hoverable, Clickable {
	
	/*
	 * 		   (x, y) ---------------------------  (x+width, y)
	 * 				  |		  some text			|
	 * (x, y+height)  ---------------------------  (x+width, y+height)
	 */
	
	double x;
	double y;
	double width;
	double height;
	String text;
	int adjust = 25;
	
	Button(double x, double y, double width, double height, String text){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
	}

	@Override
	public void draw(Window window) {}
	
	@Override
	public void onMouseEnter() {}
	
	@Override
	public void onMouseExit() {}
	
	@Override
	public void onLeftClick(double x, double y) {
		if(!getBoundingBox().contains(x, y)) 
			return;
	}
	
	@Override
	public void onRightClick(double x, double y) {}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(x, y, width, height);
	}
}
