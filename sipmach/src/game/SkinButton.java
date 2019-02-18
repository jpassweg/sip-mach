package game;

public class SkinButton extends Button {

	private StartScreen screen;
	public boolean selected = false;
	
	SkinButton(double x, double y, double width, double height, String text, StartScreen screen){
		super(x, y, width, height, text);
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
		super.onLeftClick(x, y);
		if(!selected) {
			for(int i = 0; i < screen.skinButtons.size(); i++) {
				screen.skinButtons.get(i).selected = false;
			}
			selected = true;
		}
	}
}
