package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gameEngine.Controller;
import gameInterface.WindowParameters;

public class Client {
	private static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

	private static void startGameConsole() throws InterruptedException, IOException {
		Controller.getInstance().init(false);

		// Temps de configuration de la fenetre console
		System.out.println("Press <Enter> to start.");
		bufferReader.readLine();

		while(!Controller.getInstance().gameOver()) {
			Controller.getInstance().nextTurn();
			Thread.sleep(1000);
		}
	}

	private static void startGameGraphical(WindowParameters winParams) throws InterruptedException, IOException {
		//attente de la saisie des parametres dans la fenetre
		while(!winParams.gameHasStarted()) {
			Thread.sleep(1000);
		}

		// Temps de configuration de la fenetre IHM, TODO a adapter graphiquement
		System.out.println("Press <Enter> to start.");
		bufferReader.readLine();

		while(!Controller.getInstance().gameOver()) {
			Controller.getInstance().nextTurn();
			Thread.sleep(1000);
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Input <1> for console mode");
		System.out.println("Input <2> for graphical mode");

		String choice;
		boolean restart = true;
		do {
			choice = bufferReader.readLine();
			if(choice.equals("1")) { 							// mode console
				startGameConsole();

				do {
					System.out.println("<o> to play again, <q> to exit.");
					choice = bufferReader.readLine();
					if((choice.toUpperCase()).equals("O")) {
						startGameConsole();
					} else if((choice.toUpperCase()).equals("Q")) {
						restart = false;
					} else {
						System.out.println("Unknown.");
					}
				} while(restart);

				System.out.println("Bye!");
				System.exit(0);
			} else if(choice.equals("2")) { 						// mode IHM
				startGameGraphical(new WindowParameters());
			} else {
				System.out.println("Unknown input.");
			}
		} while(!choice.equals("1") && !choice.equals("2"));
	}
}
