package game;

public class Player implements Runnable{
	
	private double x;
	private final double y = 200;	//or smth like window.height * 0.8
	private double speed;			//negative speed -> left; pos. speed -> right
	private final double acc = 0.5;
	ObjectManager parent;
	
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
		this.parent = objectManager;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
