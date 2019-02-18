package game.AroundGame;

import java.util.ArrayList;

public class EndScreen extends Screen {
	
	private final String[] options = {"Try Again", " Credits "};
	public ArrayList<EndScreenButton> list = new ArrayList<EndScreenButton>();
	
	public boolean restart = false;
	Window window;

	public EndScreen(Window window){
		super(window);
		
		
		this.window = window;
		
		double x = window.getWidth() * 0.3;
		double maxY = window.getHeight() * 0.4;
		double dist = window.getHeight() * 0.2;
		
		for(int i = 0; i < options.length; i++) {
			list.add(new EndScreenButton(x, maxY + i * dist, window.getWidth() * 0.35, 40, options[i], this));
		}
		for(int i = 0; i < list.size(); i++) {
			window.addComponent(list.get(i));
		}
	}
	
	public void rollCredits() {
		for(int i = 0; i < list.size(); i++) {
			window.removeComponent(list.get(i));
		}
		
		window.refreshAndClear();
		
		double x = window.getWidth() * 0.3;
		double y = window.getHeight() * 1.1;
		
		Credits cred = new Credits(x, y);
		window.addComponent(cred);
		
		while(!cred.done()) {
			window.refreshAndClear(20);
		}
		window.removeComponent(cred);
		
		for(int i = 0; i < list.size(); i++) {
			window.addComponent(list.get(i));
		}
	}
}
