package gameEngine;

public class Constants {
	private static int mapWidth = 20;
	private static int mapHeight = 20;
	private static int adultAge = 8;
	private static int carrotLife = 10;
	private static int respawnRegularCarrot = 5;
	private static int respawnPoisonCarrot = 10;
	private static String pathTileset1 = "./RW_Tileset.png";
	private static String pathTileset2 = "./RW_Charset.png";

	public static int getMapWidth() {
		return mapWidth;
	}

	public static int getMapHeight() {
		return mapHeight;
	}

	public static int getAdultAge() {
		return adultAge;
	}

	public static int getCarrotLife() {
		return carrotLife;
	}

	public static int getRespawnRegularCarrot() {
		return respawnRegularCarrot;
	}

	public static int getRespawnPoisonCarrot() {
		return respawnPoisonCarrot;
	}

	public static String getPathTileset1() {
		return pathTileset1;
	}

	public static String getPathTileset2() {
		return pathTileset2;
	}

	private Constants() {
	}
}
