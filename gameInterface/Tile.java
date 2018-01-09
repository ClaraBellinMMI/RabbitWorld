package gameInterface;

import java.awt.Graphics;

public class Tile {
	/**
	 * Tileset dont est issu le Tile
	 */
	private Tileset ts;
	/**
	 * Numero de position de ligne du tile dans le tileset
	 */
	private int numPosX;
	/**
	 * Numero de position de colonne du tile dans le tileset
	 */
	private int numPosY;
	/**
	 * Longueur du Tile
	 */
	private int width;
	/**
	 * Hauteur du Tile
	 */
	private int height;
	
	public Tile(Tileset t, int x, int y) {
		super();
		this.ts = t;
		this.numPosX = x;
		this.numPosY = y;
		this.width = ts.getTileW();
		this.height = ts.getTileH();
	}

	public void drawTile(Graphics g, int x, int y) {
		int tx = this.numPosX * this.width;
		int ty = this.numPosY * this.height;
		g.drawImage(this.ts.getImage(), 
				x * this.ts.getTileW(), y * this.ts.getTileH(), (x + 1) * this.ts.getTileW(), (y + 1) * this.ts.getTileH(), 
				tx , ty, tx + this.ts.getTileW(), ty + this.ts.getTileH(), 
				null);
	}
}
