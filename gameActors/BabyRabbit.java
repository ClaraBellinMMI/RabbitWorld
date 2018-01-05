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
		this.life = this.life - 2;
		/*
		 *
		 * ICI PROBLEME 
		 * suivant ce que j'ai fait et je ne sais pas si c'est bon
		 * setAge doit prendre 2 arguments dont un bebe lapin
		 * or ici je n'ai pas de bebes lapins dispos
		 * j'ai essayé de le mettre en argt mais je n'ai pas compris pourquoi 
		 * (probablement car c'est du polymorphisme si j'ai bien compris)
		 * et comme je ne veux pas toucher à ce que tu as fait ben je ne sais pas quoi faire
		 * 
		 */
		this.setAge(this.age + 1, r);
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
