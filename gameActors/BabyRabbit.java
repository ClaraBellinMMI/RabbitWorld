package gameActors;

public class BabyRabbit extends Rabbit {
	public BabyRabbit(int x, int y, boolean isMale) {
		super(x, y, isMale);
		this.age = 0;
	}

	@Override
	public Direction move() {
		// TODO Isis
		return null; // a enlever
	}

	@Override
	public void display() {
		if(this.isMale())
			System.out.print('m');
		else
			System.out.print('f');
	}
}
