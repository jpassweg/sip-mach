package game;

public class Player extends Thread{
	
	private double x;
	private final double y = 200;	//or smth like window.height * 0.8
	private double speed;			//negative speed -> left; pos. speed -> right
	private final double acc = 0.5;
	
	
	Player(){
		this.x = 300;	//window width/2
		this.speed = 0;
	}
	
	//i < 0 for left acc, i = 0 for no acc, i > 0 for right acc
	public void setX(int i) {
		x += speed;
		if(i < 0) {
			speed -= acc;
		}
		if(i > 0) {
			speed += acc;
		}
	}
	
	
	public void update() {
		//ObjectManager.setlocation(x);
	}

	public void setParent(ObjectManager objectManager) {
		// TODO Auto-generated method stub
		
	}
	

}
