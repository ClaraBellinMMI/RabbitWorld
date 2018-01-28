package gameActors;

import java.awt.Graphics;

import gameEngine.Constants;
import gameEngine.Controller;
import gameInterface.TilesetRW;

public class PoisonCarrot extends Carrot {
	public PoisonCarrot(int x, int y) {
		super(x, y);
	}

	@Override
	public void setEaten() {
		this.setRespawnTime(Constants.getRespawnPoisonCarrot());
		Controller.getInstance().setEaten(this);
	}

	@Override
	public void display() {
		System.out.print('P');
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		TilesetRW.getInstance().getRotten().drawTile(g, x, y);
	}
}
