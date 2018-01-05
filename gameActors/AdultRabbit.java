package gameActors;

import gameEngine.Cell;
import gameEngine.Constants;

/**
 * Lapin Adulte.
 */
public class AdultRabbit extends Rabbit {
	/**
	 * Cree un AdultRabbit avec comme reference de coordonnees i, j.
	 * 
	 * @param i 		le numero de ligne de la grille ou il se trouve
	 * @param j 		le numero de colonne de la grille ou il se trouve
	 * @param isMale 	booleen indiquant s'il s'agit d'un male
	 */
	public AdultRabbit(int i, int j, boolean isMale) {
		super(i, j, isMale);
		this.age = Constants.getAdultAge();
	}

	/**
	 * Fait se reproduire le AdultRabbit avec r si possible
	 * 
	 * @param r 	Rabbit avec qui on veut reproduire
	 */
	public void reproduce(Rabbit r) {
		boolean sexR = r.isMale();
		if(this.isMale() != sexR && this.age >= Constants.getAdultAge()) {
			// reproduce(
			// Controller.getInstance().reproduce(this, r);
		}
	}

	@Override
	public Cell move() {
		this.age++;
		this.setLife(this.life - 1);;
		return nextCell();	
	}

	@Override
	public void display() {
		if(this.isMale())
			System.out.print('M');
		else
			System.out.print('F');
	}
}
