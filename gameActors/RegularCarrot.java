package gameActors;

import gameEngine.Constants;
import gameEngine.Controller;

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
		eaten = false;
		setRespawnTime(5);
	}

	@Override
	public void display() {
		System.out.print("C");
	}
}
