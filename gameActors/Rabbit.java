package gameActors;

import java.util.Random;

import gameEngine.*;

/**
 * Lapin.
 */
public abstract class Rabbit extends GameElement {
	public static enum Direction {
		UP, 
		DOWN, 
		LEFT, 
		RIGHT
	}
	private static int nb = 0;
	private final Direction[] directions = Direction.values();
	private final Random RANDOM = new Random();

	/* * * * * * * * * */

	private int id;
	protected int life;
	protected int age;
	/**
	 * Direction dans laquelle regarde le Rabbit.
	 */
	protected Direction pointingTo;
	protected boolean moving;
	private boolean male;
	/**
	 * Booleen indiquant si ce Rabbit s'est reproduit.
	 */
	protected boolean reproduced;

	/* * * * * * * * * */

	/**
	 * Consomme la RegularCarrot et augmente la vie du Rabbit
	 * 
	 * @param c 	la RegularCarrot consommee
	 */
	public abstract void eat(RegularCarrot c);

	/**
	 * Renvoie la Cell vers laquelle le Rabbit va se deplacer
	 * 
	 * @return 	la Cell destination
	 */
	public abstract Cell move();

	/**
	 * Cree un Rabbit avec comme reference de coordonnees i, j.
	 * 
	 * @param i 		le numero de ligne de la grille ou il se trouve
	 * @param j 		le numero de colonne de la grille ou il se trouve
	 * @param isMale 	booleen indiquant s'il s'agit d'un male
	 */
	protected Rabbit(int x, int y, boolean isMale) {
		super(x, y);
		this.id = Rabbit.nb++;
		this.life = 10;
		this.pointingTo = Direction.DOWN;
		this.moving = false;
		this.male = isMale;
		this.reproduced = false;
	}

	public int getId() {
		return this.id;
	}

	public int getAge() {
		return this.age;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isMale() {
		return this.male;
	}

	public boolean hasReproduced() {
		return this.reproduced;
	}

	public void setReproduced(boolean r) {
		this.reproduced = r;
	}

	/**
	 * Consomme la PoisonCarrot et fait mourir le Rabbit
	 * 
	 * @param c 	la PoisonCarrot consommee
	 */
	public void eat(PoisonCarrot c) {
		c.setEaten();
		this.setLife(0);
	}

	public int getLife() {
		return this.life;
	}

	public void setLife(int life) {
		this.life = life;
		if(this.life <= 0) {
			Controller.getInstance().kill(this);
		}
	}

	/**
	 * Renvoie la prochaine Cell vers laquelle le Rabbit veut se deplacer aleatoirement. 
	 * Si la Cell initialement choisie est occupee, la prochaine Cell disponible dans 
	 * l'ordre de parcours de directions est renvoyee. Si aucune Cell n'est disponible,
	 * la Cell dans courante du Rabbit est renvoyee.
	 * 
	 * @return 	prochaine Cell
	 */
	protected Cell nextCell() {
		int li = this.getPosLi();
		int co = this.getPosCo();
		Cell c = Controller.getInstance().getGrid().getCells()[li][co];
		int init = RANDOM.nextInt(directions.length); // indice Direction initiale
		int i = init; // indice Direction random
		boolean found = false; 

		do {
			li = this.getPosLi();
			co = this.getPosCo();
			Direction direction = directions[i];
			this.pointingTo = direction;

			if(direction.equals(Direction.UP) && li > 0) {
				li--;
			} else if(direction.equals(Direction.DOWN) && li < Constants.getMapHeight() - 1) {
				li++;
			} else if(direction.equals(Direction.LEFT) && co > 0) {
				co--;
			} else if(direction.equals(Direction.RIGHT) && co < Constants.getMapWidth() - 1) {
				co++;
			}

			Cell test = Controller.getInstance().getGrid().getCells()[li][co];
			if(!test.isOccupied()) {
				found = true;
				c = test;
			}
			i = (i + 1) % directions.length;
		} while(i != init && !found);

		if(found) {
			this.setPosLi(li);
			this.setPosCo(co);
		}
		return c;
	}
}
