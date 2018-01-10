package gameInterface;

import java.awt.Image;
import java.awt.Toolkit;

import gameEngine.Constants;

public class CharsetRW implements Tileset {
	private static CharsetRW INSTANCE;
	private static Image tilesetImg = Toolkit.getDefaultToolkit().getImage(Constants.getPathTileset2());
	private static int li = 4; // nombre de lignes de tiles
	private static int co = 8; // nombre de colonnes de tiles
	private static int tileW = 32; // tile width
	private static int tileH = 32; // tile height
	private static Tile[][] tileset;

	private CharsetRW() {}

	public static CharsetRW getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new CharsetRW();

			tileset = new Tile[li][co];
			for(int i = 0; i < li; i++) {
				for(int j = 0; j < co; j++) {
					tileset[i][j] = new Tile(getInstance(), j, i);
				}
			}
		}
		return INSTANCE;
	}

	public Tile getAdultMaleUp0() {
		return tileset[0][0];
	}

	public Tile getAdultMaleUp1() {
		return tileset[0][1];
	}

	public Tile getAdultMaleDown0() {
		return tileset[0][2];
	}

	public Tile getAdultMaleDown1() {
		return tileset[0][3];
	}

	public Tile getAdultMaleLeft0() {
		return tileset[0][4];
	}

	public Tile getAdultMaleLeft1() {
		return tileset[0][5];
	}

	public Tile getAdultMaleRight0() {
		return tileset[0][6];
	}

	public Tile getAdultMaleRight1() {
		return tileset[0][7];
	}

	public Tile getAdultFemaleUp0() {
		return tileset[2][0];
	}

	public Tile getAdultFemaleUp1() {
		return tileset[2][1];
	}

	public Tile getAdultFemaleDown0() {
		return tileset[2][2];
	}

	public Tile getAdultFemaleDown1() {
		return tileset[2][3];
	}

	public Tile getAdultFemaleLeft0() {
		return tileset[2][4];
	}

	public Tile getAdultFemaleLeft1() {
		return tileset[2][5];
	}

	public Tile getAdultFemaleRight0() {
		return tileset[2][6];
	}

	public Tile getAdultFemaleRight1() {
		return tileset[2][7];
	}

	public Tile getBabyMaleUp0() {
		return tileset[1][0];
	}

	public Tile getBabyMaleUp1() {
		return tileset[1][1];
	}

	public Tile getBabyMaleDown0() {
		return tileset[1][2];
	}

	public Tile getBabyMaleDown1() {
		return tileset[1][3];
	}

	public Tile getBabyMaleLeft0() {
		return tileset[1][4];
	}

	public Tile getBabyMaleLeft1() {
		return tileset[1][5];
	}

	public Tile getBabyMaleRight0() {
		return tileset[1][6];
	}

	public Tile getBabyMaleRight1() {
		return tileset[1][7];
	}

	public Tile getBabyFemaleUp0() {
		return tileset[3][0];
	}

	public Tile getBabyFemaleUp1() {
		return tileset[3][1];
	}

	public Tile getBabyFemaleDown0() {
		return tileset[3][2];
	}

	public Tile getBabyFemaleDown1() {
		return tileset[3][3];
	}

	public Tile getBabyFemaleLeft0() {
		return tileset[3][4];
	}

	public Tile getBabyFemaleLeft1() {
		return tileset[3][5];
	}

	public Tile getBabyFemaleRight0() {
		return tileset[3][6];
	}

	public Tile getBabyFemaleRight1() {
		return tileset[3][7];
	}

	@Override
	public Image getImage() {
		return tilesetImg;
	}

	@Override
	public int getTileW() {
		return tileW;
	}

	@Override
	public int getTileH() {
		return tileH;
	}
}
