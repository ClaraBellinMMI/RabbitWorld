package gameActors;

import java.awt.Graphics;

import gameEngine.Constants;
import gameEngine.Controller;
import gameInterface.TilesetRW;

public class RegularCarrot extends Carrot {
	/**
	 * Vie de la carotte
	 */
	private int life;

	public RegularCarrot(int x, int y) {
		super(x, y);
		this.life = Constants.getCarrotLife();
	}

	public int getLife() {
		return life;
	}

	/**
	 * Affecte une nouvelle valeur de vie a la carotte. Si vie vaut 0, elle devient empoisonnee.
	 * 
	 * @param life nouvelle vie a affecter
	 */
	public void setLife(int life) {
		this.life = life;
		if(this.life == 0) {
			// empoisonnement de la carotte pris en charge par le controlleur
			Controller.getInstance().rot(this);
		}
	}

	@Override
	public void setEaten() {
		this.setRespawnTime(5);
		Controller.getInstance().setEaten(this);
	}

	@Override
	public void display() {
		System.out.print("C");
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		TilesetRW.getInstance().getCarrot().drawTile(g, x, y);
	}
}
