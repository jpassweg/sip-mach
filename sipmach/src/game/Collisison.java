package game;

import java.util.ArrayList;

public class Collisison {

	boolean collides(Player player, ArrayList<Meteor> meteors) {
		for (int i = 0; i < meteors.size(); i++) {
			if (meteors.get(i).y + meteors.get(i).size >= player.y ) {//- player.height) { // low enough for collision?

			}
		}
		return false;
	}

	void meteorCollisions(ArrayList<Meteor> meteors, Window window) {
		if (meteors == null) {
			return;
		}

		for (int j = 0; j < meteors.size() - 1; j++) {
			Meteor met = meteors.get(j);
			for (int i = 1; i < meteors.size(); i++) {
				if (met.x + met.size <= meteors.get(i).x - meteors.get(i).size) continue;
				if (met.x - met.size >= meteors.get(i).x + meteors.get(i).size) continue;
				if (met.y + met.size <= meteors.get(i).y - meteors.get(i).size) continue;
				if (met.y - met.size >= meteors.get(i).y + meteors.get(i).size) continue;
				
				double d = Math.sqrt(Math.pow((met.x - meteors.get(i).x), 2) + Math.pow((met.y - meteors.get(i).y), 2));
				if (d < met.size + meteors.get(i).size) {
					meteors.add(meteorBreak(met, meteors.get(i), window));
					meteors.remove(met);
					meteors.remove(i-1);
					break;
				}
			}
		}
	}

	Meteor meteorBreak(Meteor met, Meteor other, Window window) {
		Meteor m = new Meteor((int)window.getWidth(), (int)window.getHeight());
		m.size = met.size + other.size;
		return m;
	}
}
