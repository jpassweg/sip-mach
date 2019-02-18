package game.AroundGame;

public class Screen implements Drawable {
	
	Screen(Window window){
		window.addComponent(this);
	}

	@Override
	public void draw(Window window) {
		window.setColor(0, 0, 0);
		window.fillRect(0, 0, window.getWidth(), window.getHeight());
	}
}
