package game;

import java.util.ArrayList;

public class Collision {

	static boolean collides(Player player, ArrayList<Meteor> meteors) {
		for(int i = 0; i < meteors.size(); i++) {
			if(Math.pow((meteors.get(i).x - player.x), 2) + Math.pow((meteors.get(i).y - player.y), 2)
			   <= Math.pow((player.playerWidth + meteors.get(i).size), 2)) return true;
		}
		return false;
	}

	static void meteorCollisions(ArrayList<Meteor> meteors, Window window) {
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
					//meteors.add(meteorFusion(met, meteors.get(i), window));
					meteors.addAll(meteorBreak(met, meteors.get(i), window));
					meteors.remove(met);
					meteors.remove(i-1);
					break;
				}
			}
		}
	}

	static Meteor meteorFusion(Meteor met, Meteor other, Window window) {
		Meteor m = new Meteor((int)window.getWidth(), (int)window.getHeight());
		m.x = (met.x + other.x) / 2;
		m.y = (met.y + other.y)/ 2; 
		m.size = met.size + other.size;
		return m;
	}
	
	static ArrayList<Meteor> meteorBreak(Meteor met, Meteor other, Window window){
		ArrayList<Meteor> m = new ArrayList<Meteor>();
		Meteor oneone = new Meteor((int) window.getWidth(), (int) window.getHeight());
		Meteor onetwo = new Meteor((int) window.getWidth(), (int) window.getHeight());
		Meteor twoone = new Meteor((int) window.getWidth(), (int) window.getHeight());
		Meteor twotwo = new Meteor((int) window.getWidth(), (int) window.getHeight());
		System.out.println("im here? so this method is shit");
		giveStats(oneone, onetwo, twoone, twotwo, met, other);
		m.add(oneone);
		m.add(onetwo);
		m.add(twoone);
		m.add(twotwo);
		
		return m;
	}
	
	static void giveStats(Meteor oo, Meteor ot, Meteor to, Meteor tt, Meteor met, Meteor other) {
		oo.x = met.x + met.size * 3/8;
		oo.y = met.y;
		oo.velX = met.velX;
		oo.velY = met.velY;
		oo.size = Math.max(met.size/2, 1);
		
		ot.x = met.x - met.size * 3/8;
		ot.y = met.y;
		ot.velX = -met.velX;
		ot.velY = met.velY;
		ot.size = Math.max(met.size/2, 1);
		
		to.x = other.x + other.size * 3/8;
		to.y = other.y;
		to.velX = other.velX;
		to.velY = other.velY;
		to.size = Math.max(other.size/2, 1);
		
		tt.x = other.x - other.size * 3/8;
		tt.y = other.y;
		tt.velX = -other.velX;
		tt.velY = other.velY;
		tt.size = Math.max(other.size/2, 1);
	}
	
	
}
