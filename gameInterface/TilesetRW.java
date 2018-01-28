package gameInterface;

import java.awt.Image;
import java.awt.Toolkit;

import gameEngine.Constants;

public class TilesetRW implements Tileset {
	private static TilesetRW INSTANCE;
	private static Image tilesetImg = Toolkit.getDefaultToolkit().getImage(Constants.getPathTileset1());
	private static int li = 2; // nombre de lignes de tiles
	private static int co = 2; // nombre de colonnes de tiles
	private int tileW = 32; // tile width
	private int tileH = 32; // tile height
	private static Tile[][] tileset;

	private TilesetRW() {}

	public static TilesetRW getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new TilesetRW();

			tileset = new Tile[li][co];
			for(int i = 0; i < li; i++) {
				for(int j = 0; j < co; j++) {
					tileset[i][j] = new Tile(getInstance(), i, j);
				}
			}
		}
		return INSTANCE;
	}

	public Tile getGrass() {
		return tileset[0][0];
	}

	public Tile getCarrot() {
		return tileset[1][0];
	}

	public Tile getSemiRotten() {
		return tileset[0][1];
	}

	public Tile getRotten() {
		return tileset[1][1];
	}

	@Override
	public int getTileW() {
		return this.tileW;
	}
	
	@Override
	public int getTileH() {
		return this.tileH;
	}
	
	@Override
	public Image getImage() {
		return tilesetImg;
	}
}
