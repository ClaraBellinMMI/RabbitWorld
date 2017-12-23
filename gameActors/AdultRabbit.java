package gameActors;

import gameEngine.Constants;

public class AdultRabbit extends Rabbit {
	public AdultRabbit(int x, int y, boolean isMale) {
		super(x, y, isMale);
		this.age = Constants.getAdultAge();
	}

	@Override
	public Direction move() {
		// TODO Isis
		return null; // a enlever
	}

	@Override
	public void display() {
		if(this.isMale())
			System.out.print('M');
		else
			System.out.print('F');
	}
}
