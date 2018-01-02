package exe;

import java.io.IOException;

import gameEngine.Controller;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		Controller.getInstance().init();
		for(int i = 0; i < 10; i++) {
			Controller.getInstance().nextTurn();
			Thread.sleep(1000);
		}
	}
}
