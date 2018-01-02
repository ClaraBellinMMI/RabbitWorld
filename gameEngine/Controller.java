package gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameActors.AdultRabbit;
import gameActors.Carrot;
import gameActors.Dirt;
import gameActors.PoisonCarrot;
import gameActors.Rabbit;
import gameActors.RegularCarrot;

public class Controller {
	private static Controller INSTANCE;
	private List<Cell> rabbits = new ArrayList<>();
	private List<Cell> carrots = new ArrayList<>();
	private List<Cell> poisons = new ArrayList<>();
	private List<Cell> buffer = new ArrayList<>();
	private Grid grid;

	private Controller() {
	}

	public static Controller getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Controller();
		}
		return INSTANCE;
	}

	public void rabbitBirth(Rabbit rabbit, Cell cell) {
		rabbits.add(cell);
		cell.setContent(rabbit);
	}

	public void carrotGrowth(Carrot carrot, Cell cell) {
		carrots.add(cell);
		cell.setContent(carrot);
	}

	public void poisonGrowth(Carrot carrot, Cell cell) {
		poisons.add(cell);
		cell.setContent(carrot);
	}

	public int unsigned(String str) {
		try {
			return Integer.parseInt(str);
		} catch(NumberFormatException nfe) {
			return -1;
		}
	}

	public int inputNumber(String actor) throws IOException {
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Saisir nombre de " + actor + " : ");

		int choix;
		do {
			choix = unsigned(bufferReader.readLine());
			if(choix < 0) {
				System.out.println("Mauvaise saisie, entier positif attendu");
			}
		} while(choix < 0);

		return choix;
	}

	public void init() throws IOException {
		this.grid = new Grid(Constants.getMapWidth(), Constants.getMapHeight());

		Random r = new Random();
		int rli;
		int rco;
		int nb = getInstance().inputNumber("lapins");
		boolean placed;
		for(int i = 0; i < nb; i++) {
			placed = false;
			do {
				rli = r.nextInt(this.grid.getLi());
				rco = r.nextInt(this.grid.getCo());
				if(this.grid.getCells()[rli][rco].getContent() instanceof Dirt) {
					this.rabbitBirth(new AdultRabbit(rli, rco, r.nextBoolean()), this.grid.getCells()[rli][rco]);
					placed = true;
				}
			} while(!placed);
		}

		nb = getInstance().inputNumber("carottes");
		for (int i = 0; i < nb; i++) {
			placed = false;
			do {
				rli = r.nextInt(this.grid.getLi());
				rco = r.nextInt(this.grid.getCo());
				if (this.grid.getCells()[rli][rco].getContent() instanceof Dirt) {
					this.carrotGrowth(new RegularCarrot(rli, rco), this.grid.getCells()[rli][rco]);
					placed = true;
				}
			} while(!placed);
		}

		nb = getInstance().inputNumber("carottes empoisonnees");
		for (int i = 0; i < nb; i++) {
			placed = false;
			do {
				rli = r.nextInt(this.grid.getLi());
				rco = r.nextInt(this.grid.getCo());
				if (this.grid.getCells()[rli][rco].getContent() instanceof Dirt) {
					this.poisonGrowth(new PoisonCarrot(rli, rco), this.grid.getCells()[rli][rco]);
					placed = true;
				}
			} while(!placed);
		}

		this.grid.display();
	}

	public void rot(RegularCarrot rc) {
		int x = rc.getPosX();
		int y = rc.getPosY();
		this.grid.getCells()[x][y].setContent(new PoisonCarrot(x, y));
	}

	public void nextTurn() {
		// deplacement des lapins
		// 		possible reproduction
		// 		possible consommation
		// 		possible mort
		// vieillissement des carottes normales
		for(Cell c : this.carrots) {
			RegularCarrot carrot = ((RegularCarrot) c.getContent());
			carrot.setLife(carrot.getLife() - 1);
			if(carrot.getLife() == 0) {
				this.buffer.add(c);
			}
		}
		
		// maj des tableaux d'acteur
		this.rabbits.removeAll(this.buffer);
		this.carrots.removeAll(this.buffer);
		this.poisons.removeAll(this.buffer);
		this.buffer.clear();
		
		this.grid.display();
	}
}
