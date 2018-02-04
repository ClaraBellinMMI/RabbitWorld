package gameEngine;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import gameInterface.Map;
import gameInterface.Window;

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
	 * Les Carrot disparues pendant ce tour de jeu (mangees ou devenues empoisonnees).
	 */
	private List<Carrot> deadCarrot = new ArrayList<>();
	/**
	 * Les Carrot encore sous terre (qui vont etre bientot presentes en jeu).
	 */
	private List<Carrot> underground = new ArrayList<>();
	/**
	 * List de GameElement servant a mettre a jour d'autre List d'acteurs.
	 */
	private List<GameElement> buffer = new ArrayList<>();
	/**
	 * La Grid sur laquelle le Controller agit.
	 */
	private Grid grid = new Grid(Constants.getMapWidth(), Constants.getMapHeight());
	/**
	 * Fenetre de l'IHM.
	 */
	private Window window;
	
	public Window getWindow() {
		return window;
	}

	/**
	 * La Map utilisee pour l'IHM.
	 */
	private Map map;
	
	public Map getMap() {
		return map;
	}

	Random random = new Random();
	
	public Random getRandom() {
		return random;
	}

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

	public List<AdultRabbit> getAdultRabbits() {
		return adultRabbits;
	}

	public List<BabyRabbit> getBabyRabbits() {
		return babyRabbits;
	}

	public List<RegularCarrot> getCarrots() {
		return carrots;
	}

	public List<PoisonCarrot> getPoisons() {
		return poisons;
	}

	/**
	 * Fait apparaitre un Rabbit dans la Grid a la Cell specifiee par les parametres 
	 * li pour la ligne et co pour la colonne.
	 * 
	 * @param adult fait apparaitre un AdultRabbit ou BabyRabbit pour respectivement true et false.
	 * @param li 	indice de ligne de la Grid ou faire apparaitre le Rabbit.
	 * @param co 	indice de colonne de la Grid ou faire apparaitre le Rabbit.
	 */
	public void rabbitBirth(boolean adult, int li, int co) {
		if(0 <= li && li < Constants.getMapHeight() && 0 <= co && co < Constants.getMapWidth()) {
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

	/**
	 * Fait apparaitre une Carrot dans la Grid a la Cell specifiee par les parametres 
	 * li pour la ligne et co pour la colonne.
	 * 
	 * @param regular 	fait apparaitre un RegularCarrot ou PoisonCarrot pour respectivement true et false.
	 * @param li 		indice de ligne de la Grid ou faire apparaitre la Carrot.
	 * @param co 		indice de colonne de la Grid ou faire apparaitre la Carrot.
	 */
	public void carrotGrowth(boolean regular, int li, int co) {
		if(0 <= li && li < Constants.getMapHeight() && 0 <= co && co < Constants.getMapWidth()) {
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

	/**
	 * Renvoie l'entier naturel correspondant a la valeur numerique representee 
	 * par la Srting passee en parametre.
	 * 
	 * @param str 	String convertie.
	 * @return 		un entier naturel correspondant a la valeur numerique representee par str 
	 * 				ou -1 si la String n'en represente pas un.
	 */
	public int unsigned(String str) {
		try {
			return Integer.parseInt(str);
		} catch(NumberFormatException nfe) {
			return -1;
		}
	}

	/**
	 * Invite l'utilisateur a saisir dans le terminal, une valeur entiere positive qui representera 
	 * le nombre d'acteur du type renseigne par la String passee en parametre (utilise unsigned()).
	 * 
	 * @param actor String informant pour quel acteur l'utilisateur est en train de saisir le nombre.
	 * @return 		le nombre saisi si c'est un entier naturel, -1 sinon.
	 */
	private int inputNumber(String actor) throws IOException {
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

	/**
	 * Initialise la grille de jeu.
	 */
	private int nb_field_rabbits;
	private int nb_field_reg_carrots;
	private int nb_field_pois_carrots;
	private boolean ihm = false;
	
	public boolean isIhm() {
		return ihm;
	}

	public void setIhm(boolean ihm) {
		this.ihm = ihm;
	}

	public void setNb_field_rabbits(int nb_field_rabbits) {
		this.nb_field_rabbits = nb_field_rabbits;
	}

	public void setNb_field_reg_carrots(int nb_field_reg_carrots) {
		this.nb_field_reg_carrots = nb_field_reg_carrots;
	}

	public void setNb_field_pois_carrots(int nb_field_pois_carrots) {
		this.nb_field_pois_carrots = nb_field_pois_carrots;
	}

	public void init(boolean ihm) throws IOException {
		if(!isIhm()) {
			int rli;
			int rco;
			int nb = getInstance().inputNumber("lapins");
			boolean placed;
			for(int i = 0; i < nb; i++) {
				placed = false;
				do {
					rli = this.random.nextInt(this.grid.getLi());
					rco = this.random.nextInt(this.grid.getCo());
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
					rli = this.random.nextInt(this.grid.getLi());
					rco = this.random.nextInt(this.grid.getCo());
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
					rli = this.random.nextInt(this.grid.getLi());
					rco = this.random.nextInt(this.grid.getCo());
					if (this.grid.getCells()[rli][rco].getContent() instanceof Dirt) {
						this.carrotGrowth(false, rli, rco);
						placed = true;
					}
				} while(!placed);
			}
	
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.window = new Window(screenSize.width, screenSize.height);
			this.map = new Map();
			this.window.add(this.map);
	
			this.grid.display();
		}else {
			int rli;
			int rco;
			boolean placed;
			for(int i = 0; i < nb_field_rabbits; i++) {
				placed = false;
				do {
					rli = this.random.nextInt(this.grid.getLi());
					rco = this.random.nextInt(this.grid.getCo());
					if(this.grid.getCells()[rli][rco].getContent() instanceof Dirt) {
						this.rabbitBirth(true, rli, rco);
						placed = true;
					}
				} while(!placed);
			}
			for (int i = 0; i < nb_field_reg_carrots; i++) {
				placed = false;
				do {
					rli = this.random.nextInt(this.grid.getLi());
					rco = this.random.nextInt(this.grid.getCo());
					if(this.grid.getCells()[rli][rco].getContent() instanceof Dirt) {
						this.carrotGrowth(true, rli, rco);
						placed = true;
					}
				} while(!placed);
			}
			for (int i = 0; i < nb_field_pois_carrots; i++) {
				placed = false;
				do {
					rli = this.random.nextInt(this.grid.getLi());
					rco = this.random.nextInt(this.grid.getCo());
					if (this.grid.getCells()[rli][rco].getContent() instanceof Dirt) {
						this.carrotGrowth(false, rli, rco);
						placed = true;
					}
				} while(!placed);
			}
	
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.window = new Window(screenSize.width, screenSize.height);
			this.map = new Map();
			this.window.add(this.map);
	
			this.grid.display();
		}
	}

	/**
	 * Tue le Rabbit r passe en parametre. Necessite une MAJ des tableaux de Rabbit en fonction 
	 * de deadRabbits.
	 * 
	 * @param r le Rabbit a tuer.
	 */
	public void kill(Rabbit r) {
		this.deadRabbits.add(r);
		int li = r.getPosLi();
		int co = r.getPosCo();
		this.grid.getCells()[li][co].setContent(new Dirt(li, co));
	}

	/**
	 * Prepare la Carrot c a sa suppression suite a sa consommation. Necessite une MAJ des tableaux 
	 * des Carrot en fonction de deadCarrot.
	 * 
	 * @param c la Carrot qui a ete consommee.
	 */
	public void setEaten(Carrot c) {
		this.deadCarrot.add(c);
	}

	/**
	 * Fait pourrir la RegularCarrot rc passee en parametre. Necessite une MAJ des tableaux 
	 * des Carrot en fonction de deadCarrot.
	 * 
	 * @param rc la RegularCarrot qui a pourri.
	 */
	public void rot(RegularCarrot rc) {
		this.deadCarrot.add(rc);
		int x = rc.getPosLi();
		int y = rc.getPosCo();
		PoisonCarrot newPoison = new PoisonCarrot(x, y);
		this.poisons.add(newPoison);
		this.grid.getCells()[x][y].setContent(newPoison);
	}

	/**
	 * Fait grandir le BabyRabbit r passe en parametre. 
	 * 
	 * @param r le BabyRabbit qui a grandi.
	 */
	public void grow(BabyRabbit r) {
		int x = r.getPosLi();
		int y = r.getPosCo();
		this.babyRabbits.remove(r);
		AdultRabbit newAdult = new AdultRabbit(x, y, r.isMale());
		this.adultRabbits.add(newAdult);
	}

	/**
	 * Gere la naissance des BabyRabbit suite a la reproduction des Rabbits passes en parametre.
	 * 
	 * @param ar 	le premier Rabbit 
	 * @param r 	le deuxieme Rabbit 
	 */
	public void reproduce(Rabbit ar, Rabbit r) {
		if(!r.hasReproduced()) {
			r.setReproduced(true);
			int nbBabyLeft = 4;
			int x = ar.getPosLi();
			int y = ar.getPosCo();
			int i, j;
			for(i = x - 1; nbBabyLeft > 0 && i <= x + 1 && 0 < i && i < Constants.getMapWidth(); i++) {
				for(j = y - 1; nbBabyLeft > 0 && j <= y + 1 && 0 < j && j < Constants.getMapHeight(); j++) {
					if(this.grid.getCells()[i][j].isEmpty()) {
						this.rabbitBirth(false, i, j);
						nbBabyLeft--;
					}
				}
			}
		}
	}

	/**
	 * Passe un tour de jeu et actualise la Grid en consequence.
	 */
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
			/* * * possible passage a l'age adulte * * */
			Cell direction = r.move();
			if(r.getLife() > 0) {
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
		}

		/* * * MAJ lapins vivants (move et eat) * * */
		this.adultRabbits.removeAll(this.deadRabbits);
		this.babyRabbits.removeAll(this.deadRabbits);
		this.deadRabbits.clear();

		/* * * MAJ des carottes + preparation des nouvelles a venir (eat) * * */
		for(Carrot c : this.deadCarrot) {
			int respawnTime = c.getRespawnTime();
			int rli = this.random.nextInt(this.grid.getLi());
			int rco = this.random.nextInt(this.grid.getCo());
			Carrot young = new RegularCarrot(rli, rco);
			young.setRespawnTime(respawnTime);
			this.underground.add(young);
		}
		this.carrots.removeAll(this.deadCarrot);
		this.poisons.removeAll(this.deadCarrot);
		this.deadCarrot.clear();

		/* * * Reproduction des lapins adultes * * */
		for(AdultRabbit r : this.adultRabbits) {
			if(!r.isMale()) {
				int x = r.getPosLi();
				int y = r.getPosCo();
				List<Cell> adjCells = new ArrayList<>();
				// haut
				if((x - 1) >= 0) {
					adjCells.add(this.grid.getCells()[x - 1][y]);
				}
				// bas
				if((x + 1) < Constants.getMapHeight()) {
					adjCells.add(this.grid.getCells()[x + 1][y]);
				}
				// gauche
				if((y - 1) >= 0) {
					adjCells.add(this.grid.getCells()[x][y - 1]);
				}
				// droite
				if((y + 1) < Constants.getMapWidth()) {
					adjCells.add(this.grid.getCells()[x][y + 1]);
				}

				for(Cell cell : adjCells) {
					if(!r.hasReproduced()) {
						GameElement adj = cell.getContent();
						int indexRabbit = this.adultRabbits.indexOf(adj);
						if(indexRabbit != -1) {
							r.reproduce(this.adultRabbits.get(indexRabbit));
						}
					}
				}
			}
		}

		/* * * MAJ des carottes normales : Vieillissement des carottes normales * * */
		for(RegularCarrot c : this.carrots) {
			c.setLife(c.getLife() - 1);
		}
		this.carrots.removeAll(this.deadCarrot);
		this.deadCarrot.clear();

		/* * * MAJ des carottes normales : Pousse * * */
		for(Carrot c : this.underground) {
			int respawnTime = c.getRespawnTime();
			if(respawnTime == 0) {
				this.carrotGrowth(true, c.getPosLi(), c.getPosCo());
				this.buffer.add(c);
			} else {
				c.setRespawnTime(respawnTime - 1);
			}
		}
		this.underground.removeAll(this.buffer);
		this.buffer.clear();

		this.grid.display();
	}

	
}
