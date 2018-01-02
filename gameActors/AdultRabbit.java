package gameActors;

import gameEngine.Constants;

public class AdultRabbit extends Rabbit {
	public AdultRabbit(int x, int y, boolean isMale) {
		super(x, y, isMale);
		this.age = Constants.getAdultAge();
	}

	public boolean reproduce(AdultRabbit r) {
		boolean sexR = r.isMale();
		if(this.isMale() != sexR && this.age >= Constants.getAdultAge()) {
			return true;
		}
		return false;
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
