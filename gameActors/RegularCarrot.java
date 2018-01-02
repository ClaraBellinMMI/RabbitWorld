package gameActors;

import gameEngine.Constants;

public class RegularCarrot extends Carrot {
	private int life;

	public RegularCarrot(int x, int y) {
		super(x, y);
		this.life = Constants.getCarrotLife();
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
		if(this.life == 0) {
			// TO DO Quand j'aurai fait le Controller
		}
	}

	@Override
	public void setEaten() {
		eaten = false;
		setRespawnTime(5);
	}

	@Override
	public void display() {
		System.out.print("C");
	}
}
