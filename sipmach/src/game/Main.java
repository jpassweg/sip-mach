package game;

public class Main {
	public static void main(String[] args) {
		Thread objman = new Thread(new ObjectManager());
		objman.start();
	}
	
}
