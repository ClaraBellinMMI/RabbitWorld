package gameActors;

import java.awt.Graphics;

import gameEngine.Cell;
import gameEngine.Constants;
import gameEngine.Controller;
import gameInterface.CharsetRW;

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

	public void setAge(int age) {
		this.age = age;
		if(this.age >= Constants.getAdultAge()) {
			Controller.getInstance().grow(this); 
		}
	}

	@Override
	public void eat(RegularCarrot c) {
		c.setEaten();
		this.setLife(this.life + 2);
	}

	@Override
	public Cell move() {
		this.setLife(this.life - 2);
		Cell next = nextCell();	
		this.setAge(this.age + 1);
		return next;
	}

	@Override
	public void display() {
		if(this.isMale())
			System.out.print('m');
		else
			System.out.print('f');
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		CharsetRW.getInstance().getAdultMaleUp1().drawTile(g, x, y);
	}
}
