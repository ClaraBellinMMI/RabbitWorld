package gameActors;

import java.awt.Graphics;

import gameEngine.Cell;
import gameEngine.Constants;
import gameEngine.Controller;
import gameInterface.CharsetRW;

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
			this.reproduced = true;
			Controller.getInstance().reproduce(this, r);
		}
	}

	@Override
	public void eat(RegularCarrot c) {
		c.setEaten();
		this.setLife(this.life + 1);
	}

	@Override
	public Cell move() {
		this.reproduced = false;
		this.setLife(this.life - 1);
		this.age++;
		return nextCell();	
	}

	@Override
	public void display() {
		if(this.isMale())
			System.out.print('M');
		else
			System.out.print('F');
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		if(this.isMale()) {
			if(this.pointingTo == Direction.DOWN) {
				CharsetRW.getInstance().getAdultMaleDown0().drawTile(g, x, y);
			} else if(this.pointingTo == Direction.UP) {
				CharsetRW.getInstance().getAdultMaleUp0().drawTile(g, x, y);
			} else if(this.pointingTo == Direction.LEFT) {
				CharsetRW.getInstance().getAdultMaleLeft0().drawTile(g, x, y);
			} else {
				CharsetRW.getInstance().getAdultMaleRight0().drawTile(g, x, y);
			}
		} else {
			if(this.pointingTo == Direction.DOWN) {
				CharsetRW.getInstance().getAdultFemaleDown0().drawTile(g, x, y);
			} else if(this.pointingTo == Direction.UP) {
				CharsetRW.getInstance().getAdultFemaleUp0().drawTile(g, x, y);
			} else if(this.pointingTo == Direction.LEFT) {
				CharsetRW.getInstance().getAdultFemaleLeft0().drawTile(g, x, y);
			} else {
				CharsetRW.getInstance().getAdultFemaleRight0().drawTile(g, x, y);
			}
		}
	}
}
