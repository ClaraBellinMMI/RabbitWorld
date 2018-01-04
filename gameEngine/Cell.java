package gameEngine;

import gameActors.Rabbit;

/**
 * Case de terrain de jeu
 */
public class Cell {
	private boolean empty;
	private boolean occupied;
	private GameElement content;

	/**
	 * Cree une Cell vide
	 */
	public Cell() {
		this.content = null;
		this.empty = true;
		this.occupied = false;
	}

	public boolean isEmpty() {
		return this.empty;
	}

	public boolean isOccupied() {
		return this.occupied;
	}

	/**
	 * Renvoie le GameElement present dans cette Cell
	 * 
	 * @return le GameElement present dans cette Cell
	 */
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
