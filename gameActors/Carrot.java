package gameActors;

import gameEngine.*;

public abstract class Carrot extends GameElement {
	private static int nb = 0;
	protected int id;
	private boolean eaten;
	private int respawnTime;

	protected Carrot(int x, int y) {
		super(x, y);
		this.id = Carrot.nb++;
		this.respawnTime = 0;
	}

	public abstract void setEaten();
	/* *
	 * pseudo code de setEaten() - changer le booleen - changer le respawn time
	 * selon la classe
	 * */

	public int getId() {
		return this.id;
	}

	public boolean isEaten() {
		return this.eaten;
	}

	public int getRespawnTime() {
		return this.respawnTime;
	}
}
