package exe;

import java.io.IOException;

import gameEngine.Controller;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		Controller.getInstance().init();
/*		while(true) {
			Controller.getInstance().nextTurn();
			Thread.sleep(1000);
		}*/
	}
}
