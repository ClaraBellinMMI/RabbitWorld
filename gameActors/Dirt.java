package gameActors;

import java.awt.Graphics;
import java.io.IOException;

import gameEngine.GameElement;
import gameInterface.TilesetRW;

public class Dirt extends GameElement {
	public Dirt(int x, int y) {
		super(x, y);
	}

	@Override
	public void display() {
		System.out.print('.');
	}

	@Override
	public void draw(Graphics g, int x, int y) throws IOException {
		TilesetRW.getInstance().getGrass().drawTile(g, x, y);
	}
}
