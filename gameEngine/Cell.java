package gameEngine;

public class Cell {
	private GameElement content;

	public Cell() {
		this.content = null;
	}

	public GameElement getContent() {
		return content;
	}

	public void setContent(GameElement content) {
		this.content = content;
	}
}
