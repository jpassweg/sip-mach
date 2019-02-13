package game;

public class Main {
	public static void main(String[] args) {
		Thread game = null;
		try {
			game = new Thread(new Picture());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.start();
	}
	
}
