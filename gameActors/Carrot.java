package gameActors;

import gameEngine.*;

public abstract class Carrot extends GameElement {
	private static int nb = 0;
	protected int id;
	private int respawnTime;

	protected Carrot(int x, int y) {
		super(x, y);
		this.id = Carrot.nb++;
		this.respawnTime = 0;
	}

	public abstract void setEaten();

	public int getId() {
		return this.id;
	}

	public int getRespawnTime() {
		return this.respawnTime;
	}

	public void setRespawnTime(int respawnTime) {
		this.respawnTime = respawnTime;
	}


}
