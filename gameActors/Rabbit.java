package gameActors;

import java.util.Random;

import gameEngine.*;

/**
 * Lapin.
 */
public abstract class Rabbit extends GameElement {
	private static int nb = 0;
	private static enum Direction {
		UP, 
		DOWN, 
		LEFT, 
		RIGHT
	}
	private final Direction[] directions = Direction.values();
	private final Random RANDOM = new Random();

	/* * * * * * * * * */

	private int id;
	protected int life;
	protected int age;
	private boolean male;

	/* * * * * * * * * */

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
		this.male = isMale;
	}

	public int getId() {
		return this.id;
	}

	public int getAge() {
		return this.age;
	}

	public boolean isMale() {
		return this.male;
	}

	/**
	 * Consomme la RegularCarrot et augmente la vie du Rabbit
	 * 
	 * @param c 	la RegularCarrot consommee
	 */
	public void eat(RegularCarrot c) {
		c.setEaten();
		this.setLife(this.life + 1);
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

	public void setLife(int life) {
		if(life == 0) {
			Controller.getInstance().kill(this);
		}
		this.life = life;
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

			if(direction.equals(Direction.UP) && li > 0) {
				//System.out.println("up");
				li--;
			} else if(direction.equals(Direction.DOWN) && li < Constants.getMapHeight() - 1) {
				//System.out.println("down");
				li++;
			} else if(direction.equals(Direction.LEFT) && co > 0) {
				//System.out.println("left");
				co--;
			} else if(direction.equals(Direction.RIGHT) && co < Constants.getMapWidth() - 1) {
				//System.out.println("right");
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