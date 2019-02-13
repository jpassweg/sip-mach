package game;

public class Main {
	public static void main(String[] args) {
		Thread game = null;
		try {
			game = new Thread(new Window());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.start();
	}
	
}
