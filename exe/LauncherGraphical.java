package exe;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import gameEngine.Controller;
import gameInterface.WindowParameters;

public class LauncherGraphical extends Launcher {
	private boolean closed = false;

	@Override
	protected void startGame() throws InterruptedException, IOException {
		while(!Controller.getInstance().isGameStarted()) {
			Thread.sleep(1000);
		}

		while(!Controller.getInstance().gameOver()) {
			Controller.getInstance().nextTurn();
			Thread.sleep(1000);
		}
	}

	@Override
	public void launch() throws InterruptedException, IOException {
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			try {
				startGame();
			} catch (InterruptedException | IOException e1) {
				e1.printStackTrace();
			}
		} while(!closed);
	}
}
