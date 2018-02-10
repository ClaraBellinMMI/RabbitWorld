package gameEngine;

import gameActors.Dirt;

/**
 * Case de terrain de jeu
 */
public class Cell {
	private boolean empty;
	private GameElement content;

	/**
	 * Cree une Cell vide
	 */
	public Cell() {
		this.content = null;
		this.empty = true;
	}

	public boolean isEmpty() {
		return this.empty;
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
		if(content instanceof Dirt) {
			this.empty = true;
		} else {
			this.empty = false;
		}
	}
}
