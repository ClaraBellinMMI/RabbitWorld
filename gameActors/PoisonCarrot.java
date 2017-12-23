package gameActors;

public class PoisonCarrot extends Carrot {
	public PoisonCarrot(int x, int y) {
		super(x, y);
	}

	@Override
	public void setEaten() {
		// TODO Isis
	}

	@Override
	public void display() {
		System.out.print('P');
	}
}
