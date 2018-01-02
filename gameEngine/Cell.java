package gameEngine;

public class Cell {
	private boolean empty;
	private GameElement content;

	public Cell() {
		this.content = null;
	}

	public boolean isEmpty() {
		return this.empty;
	}

	public GameElement getContent() {
		return this.content;
	}

	public void setContent(GameElement content) {
		this.content = content;
		if(this.content == null) {
			this.empty = true;
		} else {
			this.empty = false;
		}
	}
}
