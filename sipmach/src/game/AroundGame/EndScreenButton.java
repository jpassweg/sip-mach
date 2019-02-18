package game.AroundGame;

public class EndScreenButton extends Button {

	
	boolean hovered = false;
	EndScreen screen;
	
	EndScreenButton(double x, double y, double width, double height, String text, EndScreen screen){
		super(x, y, width, height, text);
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
		super.onLeftClick(x, y);
		if(text.equals("Try Again")) {
			screen.restart = true;
		} else if(text.equals(" Credits ")) {
			screen.rollCredits();
		}
	}
	
	@Override
	public void onMouseEnter() {
		hovered = true;
	}
	
	@Override
	public void onMouseExit() {
		hovered = false;
	}
}
