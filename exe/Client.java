package exe;

import java.io.IOException;

import gameActors.AdultRabbit;
import gameActors.PoisonCarrot;
import gameActors.RegularCarrot;
import gameEngine.Constants;
import gameEngine.Grid;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		int x = Constants.getMapWidth();
		int y = Constants.getMapHeight();
		Grid g = new Grid(x, y);
		g.getCells()[x/2 + 1][y/2].setContent(new AdultRabbit(x/2 + 1, y/2, true));
		g.getCells()[x/2 - 1][y/2].setContent(new AdultRabbit(x/2 - 1, y/2, false));
		g.getCells()[x/2][y/2 + 1].setContent(new PoisonCarrot(x/2, y/2 + 1));
		g.getCells()[x/2][y/2 - 1].setContent(new RegularCarrot(x/2, y/2 - 1));
		g.display();
	}
}
