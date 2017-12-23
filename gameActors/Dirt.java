package gameActors;

import gameEngine.GameElement;

public class Dirt extends GameElement {
	public Dirt(int x, int y) {
		super(x, y);
	}

	@Override
	public void display() {
		System.out.print('.');
	}
}
