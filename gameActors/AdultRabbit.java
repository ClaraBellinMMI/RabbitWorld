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
	 * Renvoie un booleen indiquant si ce AdultRabbit peut se reproduire avec r
	 * 
	 * @param r 	Rabbit avec qui on teste la possibilite de se reproduire
	 * @return 		booleen de possibilite de reproduction avec r
	 */
	public boolean reproduce(Rabbit r) {
		boolean sexR = r.isMale();
		if(this.isMale() != sexR && this.age >= Constants.getAdultAge()) {
			return true;
		}
		return false;
	}

	@Override
	public Cell move() {
		this.life = this.life - 1;
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
