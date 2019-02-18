package game.AroundGame;

public class ModeButton extends Button {
	
	StartScreen screen;
	
	ModeButton(double x, double y, double width, double height, String text, StartScreen screen){
		super(x, y, width, height, text);
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
	public void onLeftClick(double x, double y) {
		super.onLeftClick(x, y);
		screen.mode += text;
		screen.mode += "Meteor";
	}
}
