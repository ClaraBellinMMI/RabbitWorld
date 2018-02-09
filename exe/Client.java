package exe;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gameEngine.Controller;
import gameInterface.WindowParameters;

public class Client {
	private static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
	private static boolean closed = false;

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

	private static void startGameGraphical() throws InterruptedException, IOException {
		while(!Controller.getInstance().isGameStarted()) {
			Thread.sleep(1000);
		}

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
				WindowParameters winParam = new WindowParameters();
				winParam.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						closed = true;
					}
				});
				
				do {
					// Attente de la saisie des parametres initiaux
					while(!Controller.getInstance().gameIsInit()) {
						Thread.sleep(1000);
					}
					startGameGraphical();
				} while(!closed);
			} else {
				System.out.println("Unknown input.");
			}
		} while(!choice.equals("1") && !choice.equals("2"));
	}
}
