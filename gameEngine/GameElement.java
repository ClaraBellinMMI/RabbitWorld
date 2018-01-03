package gameEngine;

public abstract class GameElement implements Displayable {
	/**
	 * Position en ligne du GameElement
	 */
	private int posLi;
	/**
	 * Position en colonne du GameElement
	 */
	private int posCo;

	public GameElement(int posX, int posY) {
		super();
		this.posLi = posX;
		this.posCo = posY;
	}

	public int getPosLi() {
		return this.posLi;
	}

	public void setPosLi(int posX) {
		this.posLi = posX;
	}

	public int getPosCo() {
		return this.posCo;
	}

	public void setPosCo(int posY) {
		this.posCo = posY;
	}
	

}
