package gameEngine;

public abstract class Carrot {

	private int id;
	private boolean eaten;
	private int respawnTime;
	
	public boolean isEaten() {
		return eaten;
	}
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	} 
	
}
