package exe;

import java.io.IOException;

import gameEngine.Controller;
import gameInterface.WindowParameters;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		new WindowParameters();
		boolean ihm = true;
		Controller.getInstance().init(ihm);
/*		while(true) {
			Controller.getInstance().nextTurn();
			Thread.sleep(1000);
		}*/
	}
}
