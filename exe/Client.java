package exe;

import gameEngine.Grid;

public class Client {

	public static void main(String[] args) {
		Grid test = new Grid(8, 4);
		test.testDisplay();
		test.display();
	}

}
