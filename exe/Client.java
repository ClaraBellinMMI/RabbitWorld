package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gameEngine.Controller;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		Controller.getInstance().init();

		// Temps de configuration de la disposition à l'ecran
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("<Entrée> pour commencer.");
		bufferReader.readLine();

		while(!Controller.getInstance().gameOver()) {
			Controller.getInstance().nextTurn();
			Thread.sleep(1000);
		}
	}
}
