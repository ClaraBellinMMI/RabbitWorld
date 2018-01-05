package gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameActors.AdultRabbit;
import gameActors.BabyRabbit;
import gameActors.Carrot;
import gameActors.Dirt;
import gameActors.PoisonCarrot;
import gameActors.Rabbit;
import gameActors.RegularCarrot;

public class Controller {
	private static Controller INSTANCE;
	/**
	 * Les AdultRabbit presents en jeu.
	 */
	private List<AdultRabbit> adultRabbits = new ArrayList<>();
	/**
	 * Les BabyRabbit presents en jeu.
	 */
	private List<BabyRabbit> babyRabbits = new ArrayList<>();
	/**
	 * Les Rabbit mort pendant le tour courant de jeu.
	 */
	private List<Rabbit> deadRabbits = new ArrayList<>();
	/**
	 * Les RegularCarrot presentes en jeu.
	 */
	private List<RegularCarrot> carrots = new ArrayList<>();
	/**
	 * Les PoisonCarrot presentes en jeu.
	 */
	private List<PoisonCarrot> poisons = new ArrayList<>();
	/**
	 * Le Carrot encore sous terre (qui vont etre bientot presentes en jeu).
	 */
	private List<Carrot> underground = new ArrayList<>();
	/**
	 * Les GameElements mis en tampon pour divers traitements.
	 */
	private List<GameElement> buffer = new ArrayList<>();
	/**
	 * La Grid sur laquelle le Controller agit.
	 */
	private Grid grid = new Grid(Constants.getMapWidth(), Constants.getMapHeight());

	private Controller() {
	}

	public static Controller getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Controller();
		}
		return INSTANCE;
	}

	public Grid getGrid() {
		return this.grid;
	}

	public void rabbitBirth(boolean adult, int li, int co) {
		if(0 < li && li < Constants.getMapHeight() && 0 < co && co < Constants.getMapWidth()) {
			Random rd = new Random();
			Rabbit r;
			if(adult) {
				r = new AdultRabbit(li, co, rd.nextBoolean());
				this.adultRabbits.add((AdultRabbit)r);
			} else {
				r = new BabyRabbit(li, co, rd.nextBoolean());
				this.babyRabbits.add((BabyRabbit)r);
			}
			this.grid.getCells()[li][co].setContent(r);
		}
	}

	public void carrotGrowth(boolean regular, int li, int co) {
		if(0 < li && li < Constants.getMapHeight() && 0 < co && co < Constants.getMapWidth()) {
			Carrot c;
			if(regular) {
				c = new RegularCarrot(li, co);
				this.carrots.add((RegularCarrot)c);
			} else {
				c = new PoisonCarrot(li, co);
				this.poisons.add((PoisonCarrot)c);
			}
			this.grid.getCells()[li][co].setContent(c);
		}
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
					this.rabbitBirth(true, rli, rco);
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
				if(this.grid.getCells()[rli][rco].getContent() instanceof Dirt) {
					this.carrotGrowth(true, rli, rco);
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
					this.carrotGrowth(false, rli, rco);
					placed = true;
				}
			} while(!placed);
		}

		this.grid.display();
	}

	public void kill(Rabbit r) {
		this.deadRabbits.add(r);
		int li = r.getPosLi();
		int co = r.getPosCo();
		this.grid.getCells()[li][co].setContent(new Dirt(li, co));
	}

	public void rot(RegularCarrot rc) {
		int x = rc.getPosLi();
		int y = rc.getPosCo();
		this.grid.getCells()[x][y].setContent(new PoisonCarrot(x, y));
	}
	
	public void grow(BabyRabbit r) {
		int x = r.getPosLi();
		int y = r.getPosCo();
		this.babyRabbits.remove(r);
		//pour avoir male/femelle aleatoire
		Random rand = new Random();
		boolean randM = rand.nextBoolean();
		AdultRabbit newAdult = new AdultRabbit(x, y, randM);
		this.adultRabbits.add(newAdult);
		this.grid.getCells()[x][y].setContent(newAdult);	
	}
	
	public void reproduce(AdultRabbit ar, Rabbit r) {
		System.out.println("BR");
	}

	public void nextTurn() {
		// Tableau temporaire pour traitement commun adultes et bebes
		ArrayList<Rabbit> rabbs = new ArrayList<>();
		rabbs.addAll(this.adultRabbits);
		rabbs.addAll(this.babyRabbits);

		/* * * deplacement des lapins * * */
		for(Rabbit r : rabbs) {
			int x = r.getPosLi();
			int y = r.getPosCo();
			this.grid.getCells()[x][y].setContent(new Dirt(x, y));
			Cell direction = r.move();
			GameElement content = direction.getContent();
			direction.setContent(r);
			/* * * possible consommation * * */
			int indexCarrot = this.carrots.indexOf(content);
			if(indexCarrot != -1) {
				r.eat(this.carrots.get(indexCarrot));
			} else if((indexCarrot = this.poisons.indexOf(content)) != -1) {
				r.eat(this.poisons.get(indexCarrot));
			}
		}

		/* * * MAJ lapins vivants (move et eat) * * */
		this.adultRabbits.removeAll(this.deadRabbits);
		this.babyRabbits.removeAll(this.deadRabbits);
		this.deadRabbits.clear();

		/* * * MAJ des carottes consommees + preparation des nouvelles a venir (eat) * * *
		 * TODO Alex
		 * */

		/* * * Reproduction des lapins adultes * * *
		 * Pseudo-pseudo-code : 
		 * 		pour chaque lapin adulte
		 * 			regarder autour du lapin 
		 * 			si une case adjacente a un autre lapin adulte (eviter instanceof, privilegier le schema possible consommation dans le deplacement)
		 * 				faire se reproduire le lapin
		 * TODO Isis (Si possible. Met surtout la priorite sur le passage a l'age adulte qui est plus simple)
		 */
		
		for(AdultRabbit r : this.adultRabbits) {
			if(!r.isMale()) {
				int x = r.getPosLi();
				int y = r.getPosCo();
				int i, j;
				for(i = x-1; i <= x+1 && 0 < i && i < 20; i++) {
					for(j = y-1; j <= y+1 && 0 < j && j < 20; j++) {
						GameElement neighbour = this.grid.getCells()[i][j].getContent();
						int indexRabbit = this.adultRabbits.indexOf(neighbour);
						if(indexRabbit != -1) {
							r.reproduce(this.adultRabbits.get(indexRabbit));
						}
					}
				}
			}
		}

		/* * * Passage a l'age adulte pour les bebes concernes * * *
		 * Traitement quasi-similaire au vieillissement des carottes normales
		 * test a faire en fonction de la constante adultAge de la classe Constants
		 * 
		 * TODO Isis
		 * */
		
		
		/* * * MAJ des carottes normales : Vieillissement des carottes normales * * */
		for(RegularCarrot c : this.carrots) {
			c.setLife(c.getLife() - 1);
			if(c.getLife() == 0) {
				this.buffer.add(c);
			}
		}
		this.carrots.removeAll(this.buffer);
		this.poisons.removeAll(this.buffer);
		this.buffer.clear();
		
		/* * * MAJ des carottes normales : Pousse * * *
		 * TODO Alex
		 * */

		this.grid.display();
	}
}
