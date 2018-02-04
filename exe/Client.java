package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gameEngine.Controller;
import gameInterface.WindowParameters;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Input <1> for console mode");
		System.out.println("Input <2> for graphical mode");
		
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		String choix;
		do {
			choix = bufferReader.readLine();
			if(choix.equals("1")) { // mode console
				Controller.getInstance().init(false);

				// Temps de configuration de la fenetre console
				System.out.println("Press <Enter> to start.");
				bufferReader.readLine();

				while(true) {
					Controller.getInstance().nextTurn();
					Thread.sleep(1000);
				}
			} else if(choix.equals("2")) { // mode IHM
				new WindowParameters();
				
				// Temps laisse a l'utilisateur pour disposer des fenetres
				System.out.println("Press <Enter> to start.");
				bufferReader.readLine();

				while(true) {
					Controller.getInstance().nextTurn();
					Thread.sleep(1000);
				}
			} else {
				System.out.println("Unknown input.");
			}
		} while(!choix.equals("1") && !choix.equals("2"));
	}
}
