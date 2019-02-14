package game;

import java.util.ArrayList;

public class Collision {

	static boolean collides(Player player, ArrayList<Meteor> meteors) {
		for(int i = 0; i < meteors.size(); i++) {
			if(Math.pow((meteors.get(i).x - player.x), 2) + Math.pow((meteors.get(i).y - player.y), 2)
			   <= Math.pow((player.playerWidth/2 + meteors.get(i).radius), 2)) return true;
		}
		return false;
	}
	
	
	//deletes shot and meteor
	/*
	 * TODO: add animation
	 * -> let meteor stay inplace until end of animation
	 * -> only then delete meteor
	 */
	
	static void shotMeteor(ArrayList<Shot> shots, ArrayList<Meteor> meteors) {
		for(int i = 0; i < shots.size(); i++) {
			for(int j = 0; j < meteors.size(); j++) {
				if(shots.get(i).x < meteors.get(j).x - meteors.get(j).radius) continue;
				if(shots.get(i).x > meteors.get(j).x + meteors.get(j).radius) continue;
				if(shots.get(i).y - shots.get(i).length > meteors.get(j).y + meteors.get(j).radius) continue;
				if(shots.get(i).y < meteors.get(j).y - meteors.get(j).radius) continue;

				shots.remove(i);
				meteors.remove(j);
				i--;
				j--;
				break;
			}
		}
	}
	
	
	//deletes only meteor -> shot can destroy several meteors
	
	static void shotMeteorB(ArrayList<Shot> shots, ArrayList<Meteor> meteors) {
		for(int i = 0; i < shots.size(); i++) {
			for(int j = 0; j < meteors.size(); j++) {
				if(shots.get(i).x < meteors.get(j).x - meteors.get(j).radius) continue;
				if(shots.get(i).x > meteors.get(j).x + meteors.get(j).radius) continue;
				if(shots.get(i).y - shots.get(i).length > meteors.get(j).y + meteors.get(j).radius) continue;
				if(shots.get(i).y < meteors.get(j).y - meteors.get(j).radius) continue;
				if(!(Math.pow((shots.get(i).x - meteors.get(j).x), 2) + 
					 Math.pow((shots.get(i).y + shots.get(i).length - meteors.get(j).y), 2) 
					<= Math.pow(meteors.get(i).radius, 2))) continue;
				meteors.remove(j);
				j--;
			}
		}
	}
	
	
	//breaks meteor in half -> shot gets deleted
	
	static void shotMeteorC(ArrayList<Shot> shots, ArrayList<Meteor> meteors, Window window) {
		for(int i = 0; i < shots.size(); i++) {
			for(int j = 0; j < meteors.size(); j++) {
				if(shots.get(i).x < meteors.get(j).x - meteors.get(j).radius) continue;
				if(shots.get(i).x > meteors.get(j).x + meteors.get(j).radius) continue;
				if(shots.get(i).y - shots.get(i).length > meteors.get(j).y + meteors.get(j).radius) continue;
				if(shots.get(i).y < meteors.get(j).y - meteors.get(j).radius) continue;

				meteors.addAll(breakMeteor(meteors.get(j), window));
				meteors.remove(j);
				shots.remove(i);
				i--;
				j--;
				
			}
		}
	}
	
	static ArrayList<Meteor> breakMeteor(Meteor met, Window window){
		
		ArrayList<Meteor> m = new ArrayList<Meteor>();
		m.add(new Meteor((int) window.getWidth(), (int) window.getHeight(), 1));
		m.add(new Meteor((int) window.getWidth(), (int) window.getHeight(), 1));
		
		m.get(0).radius = met.radius * 3/8;
		m.get(0).x = met.x + met.radius;
		m.get(0).y = met.y;
		m.get(0).velX = met.velX * 1.5;
		m.get(0).velY = met.velY;
		
		m.get(1).radius = met.radius * 3/8;
		m.get(1).x = met.x - met.radius;
		m.get(1).y = met.y;
		m.get(1).velX = met.velX * 0.5;
		m.get(1).velY = met.velY;
		
		return m;
	}
	
	//O(n!) :/

	static void meteorCollisions(ArrayList<Meteor> meteors, Window window) {
		if (meteors == null) {
			return;
		}

		for (int j = 0; j < meteors.size() - 1; j++) {
			Meteor met = meteors.get(j);
			for (int i = 1; i < meteors.size(); i++) {
				if (met.x + met.radius <= meteors.get(i).x - meteors.get(i).radius) continue;
				if (met.x - met.radius >= meteors.get(i).x + meteors.get(i).radius) continue;
				if (met.y + met.radius <= meteors.get(i).y - meteors.get(i).radius) continue;
				if (met.y - met.radius >= meteors.get(i).y + meteors.get(i).radius) continue;
				
				double d = Math.sqrt(Math.pow((met.x - meteors.get(i).x), 2) + Math.pow((met.y - meteors.get(i).y), 2));
				if (d < met.radius + meteors.get(i).radius) {
					//meteors.add(meteorFusion(met, meteors.get(i), window));
					meteors.addAll(doubleMeteorBreak(met, meteors.get(i), window));
					meteors.remove(met);
					meteors.remove(i-1);
					break;
				}
			}
		}
	}

	static Meteor meteorFusion(Meteor met, Meteor other, Window window) {
		Meteor m = new Meteor((int)window.getWidth(), (int)window.getHeight(), 1);
		m.x = (met.x + other.x) / 2;
		m.y = (met.y + other.y)/ 2; 
		m.radius = met.radius + other.radius;
		return m;
	}
	
	static ArrayList<Meteor> doubleMeteorBreak(Meteor met, Meteor other, Window window){
		ArrayList<Meteor> m = new ArrayList<Meteor>();
		Meteor oneone = new Meteor((int) window.getWidth(), (int) window.getHeight(), 1);
		Meteor onetwo = new Meteor((int) window.getWidth(), (int) window.getHeight(), 1);
		Meteor twoone = new Meteor((int) window.getWidth(), (int) window.getHeight(), 1);
		Meteor twotwo = new Meteor((int) window.getWidth(), (int) window.getHeight(), 1);
		System.out.println("im here? so this method is shit");
		giveStats(oneone, onetwo, twoone, twotwo, met, other);
		m.add(oneone);
		m.add(onetwo);
		m.add(twoone);
		m.add(twotwo);
		
		return m;
	}
	
	static void giveStats(Meteor oo, Meteor ot, Meteor to, Meteor tt, Meteor met, Meteor other) {
		oo.x = met.x + met.radius * 3/8;
		oo.y = met.y;
		oo.velX = met.velX;
		oo.velY = met.velY;
		oo.radius = Math.max(met.radius/2, 1);
		
		ot.x = met.x - met.radius * 3/8;
		ot.y = met.y;
		ot.velX = -met.velX;
		ot.velY = met.velY;
		ot.radius = Math.max(met.radius/2, 1);
		
		to.x = other.x + other.radius * 3/8;
		to.y = other.y;
		to.velX = other.velX;
		to.velY = other.velY;
		to.radius = Math.max(other.radius/2, 1);
		
		tt.x = other.x - other.radius * 3/8;
		tt.y = other.y;
		tt.velX = -other.velX;
		tt.velY = other.velY;
		tt.radius = Math.max(other.radius/2, 1);
	}
	
	
}
