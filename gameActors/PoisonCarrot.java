package gameActors;

public class PoisonCarrot extends Carrot {
	public PoisonCarrot(int x, int y) {
		super(x, y);
	}

	@Override
	public void setEaten() {
		this.eaten = true;
		setRespawnTime(10);
	}

	@Override
	public void display() {
		System.out.print('P');
	}
}
