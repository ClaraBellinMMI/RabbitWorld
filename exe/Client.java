package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gameEngine.Controller;

public class Client {
	private static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void startGame() throws IOException, InterruptedException {
		Controller.getInstance().init();

		// Temps de configuration de la disposition à l'ecran
		System.out.println("<Entrée> pour commencer.");
		bufferReader.readLine();

		while(!Controller.getInstance().gameOver()) {
			Controller.getInstance().nextTurn();
			Thread.sleep(500);
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		startGame();
		boolean restart = true;
		do {
			System.out.println("<o> pour rejouer, <q> pour quitter.");
			String choix = bufferReader.readLine();
			if((choix.toUpperCase()).equals("O")) {
				Controller.getInstance().getWindow().dispose();
				startGame();
			} else if((choix.toUpperCase()).equals("Q")) {
				restart = false;
			} else {
				System.out.println("Commande inconnue.");
			}
		} while(restart);

		System.out.println("Au revoir!");
		Controller.getInstance().getWindow().dispose();
		System.exit(0);
	}
}
