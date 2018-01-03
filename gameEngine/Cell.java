package gameEngine;

import gameActors.Rabbit;

public class Cell {
	private boolean empty;
	private boolean occupied;
	private GameElement content;

	public Cell() {
		this.content = null;
	}

	public boolean isEmpty() {
		return this.empty;
	}

	public boolean isOccupied() {
		return this.occupied;
	}
	
	public GameElement getContent() {
		return this.content;
	}

	public void setContent(GameElement content) {
		this.content = content;
		this.occupied = false;
	}
	
	public void setContent(Rabbit content) {
		this.content = content;
		this.occupied = true;
	}
}
