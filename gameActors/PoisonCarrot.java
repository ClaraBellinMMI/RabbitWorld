package gameActors;

import gameEngine.Controller;

public class PoisonCarrot extends Carrot {
	public PoisonCarrot(int x, int y) {
		super(x, y);
	}

	@Override
	public void setEaten() {
		this.setRespawnTime(10);
		Controller.getInstance().setEaten(this);
	}

	@Override
	public void display() {
		System.out.print('P');
	}
}
