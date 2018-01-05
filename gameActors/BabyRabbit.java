package gameActors;

import gameEngine.Cell;

/**
 * Bebe Lapin.
 */
public class BabyRabbit extends Rabbit {
	/**
	 * Cree un BabyRabbit avec comme reference de coordonnees i, j.
	 * 
	 * @param i 		le numero de ligne de la grille ou il se trouve
	 * @param j 		le numero de colonne de la grille ou il se trouve
	 * @param isMale 	booleen indiquant s'il s'agit d'un male
	 */
	public BabyRabbit(int i, int j, boolean isMale) {
		super(i, j, isMale);
		this.age = 0;
	}

	@Override
	public Cell move() {
		this.setLife(this.life - 2);
		return nextCell();	
	}

	@Override
	public void display() {
		if(this.isMale())
			System.out.print('m');
		else
			System.out.print('f');
	}
}
