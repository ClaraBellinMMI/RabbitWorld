package gameEngine;

public class Constants {
	private static int mapWidth = 20;
	private static int mapHeight = 20;
	private static int adultAge = 6;
	private static int carrotLife = 10;
	private static int respawnRegularCarrot = 5;
	private static String pathTileset1 = "/ressources/RW_Tileset.png";
	private static String pathTileset2 = "/ressources/RW_Charset.png";
	private static String pathRabbitGif = "/ressources/lapin_bas_param.gif";
	private static String pathHoleGif = "/ressources/lapin_terrier.gif";
	private static String pathErrorGif = "/ressources/lapin_erreur.png";

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

	public static String getPathTileset1() {
		return pathTileset1;
	}

	public static String getPathTileset2() {
		return pathTileset2;
	}

	public static String getPathRabbitGif() {
		return pathRabbitGif;
	}

	public static String getPathHoleGif() {
		return pathHoleGif;
	}

	public static String getPathErrorGif() {
		return pathErrorGif;
	}

	private Constants() {
	}
}
