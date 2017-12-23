package gameActors;

import gameEngine.*;

public abstract class Rabbit extends GameElement {
	private static int nb = 0;
	private int id;
	private int life;
	protected int age;
	private boolean male;

	public enum Direction {
		UP, 
		DOWN, 
		LEFT, 
		RIGHT
	}

	public abstract Direction move();
	/* * 
	 * pseudo code de move() - diminuer une quantite de vie selon si adulte ou
	 * baby - choisir une direction au hasard (j'ai rajoute un enum des Directions
	 * dans la classe, tu peux y acceder avec Direction.UP, ...) - renvoyer la
	 * direction choisie
	 * */

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
		return male;
	}

	public void eat(Carrot c) {
		c.setEaten();
		gainLife(1);
	}

	public void gainLife(int nb) {
		life = life + nb;
	}

	public void die() {
		life = 0;
	}

	public boolean reproduce(Rabbit r) {
		boolean sexR = r.isMale();
		if(this.isMale() != sexR && this.age >= Constants.getAdultAge()) {
			return true;
		}
		return false;
	}
}
