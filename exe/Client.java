package exe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameEngine.Constants;
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
	/*
	private class ChooseMode extends JFrame {
		private static final long serialVersionUID = 1L;

		public ChooseMode() {
			JFrame f = new JFrame();
			f.setTitle("Choose your mode");
			f.setBounds(500, 250, 500, 280);
			PanelChooseMode pan = new PanelChooseMode();
			f.add(pan);
			f.toFront();
			f.setVisible(true);
		}
		
		private class PanelChooseMode extends JPanel {
			private static final long serialVersionUID = 1L;
			
			JButton console_button;
			JButton ihm_button;
			public PanelChooseMode() {
				console_button = new JButton("CONSOLE");
				ihm_button = new JButton("GRAPHICAL MODE");

				add(console_button, BorderLayout.SOUTH);
				add(ihm_button, BorderLayout.SOUTH);
				ConsoleAction consAct = new ConsoleAction();
				console_button.addActionListener(consAct);
				IhmAction ihmAct = new IhmAction();
				ihm_button.addActionListener(ihmAct);
			}
			
			private class ConsoleAction implements ActionListener  {
				String choice;
				boolean restart = true;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						startGameConsole();
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
					do {
						System.out.println("<o> to play again, <q> to exit.");
						try {
							choice = bufferReader.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if((choice.toUpperCase()).equals("O")) {
							try {
								startGameConsole();
							} catch (InterruptedException | IOException e) {
								e.printStackTrace();
							}
						} else if((choice.toUpperCase()).equals("Q")) {
							restart = false;
						} else {
							System.out.println("Unknown.");
						}
					} while(restart);

					System.out.println("Bye!");
					System.exit(0);
				}
			}
			
			private class IhmAction implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent arg0) {
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
							startGameGraphical();
						} catch (InterruptedException | IOException e1) {
							e1.printStackTrace();
						}
					} while(!closed);
				}
			}
			
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.yellow);
				Font f = new Font("Courier", Font.BOLD, 20);
				g2.setFont(f);
				g2.drawString("Choose your mode", 10, 30);
			}
		}
	}*/
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//new ChooseMode();
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
