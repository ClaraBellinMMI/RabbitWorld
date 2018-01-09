package gameInterface;

import java.awt.Graphics;

import javax.swing.JPanel;

import gameActors.AdultRabbit;
import gameActors.BabyRabbit;
import gameActors.PoisonCarrot;
import gameActors.RegularCarrot;
import gameEngine.Controller;

public class Map extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controller ctrl = Controller.getInstance();

	public Map() {
		super();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(int i = 0; i < this.ctrl.getGrid().getLi(); i++) {
			for(int j = 0; j < this.ctrl.getGrid().getCo(); j++) {
				TilesetRW.getInstance().getGrass().drawTile(g, j, i);
			}
		}

		for(RegularCarrot rc : this.ctrl.getCarrots()) {
			rc.draw(g, rc.getPosCo(), rc.getPosLi());
		}

		for(PoisonCarrot pc : this.ctrl.getPoisons()) {
			pc.draw(g, pc.getPosCo(), pc.getPosLi());
		}

		for(AdultRabbit ar : this.ctrl.getAdultRabbits()) {
			ar.draw(g, ar.getPosCo(), ar.getPosLi());
		}

		for(BabyRabbit br : this.ctrl.getBabyRabbits()) {
			br.draw(g, br.getPosCo(), br.getPosLi());
		}
	}
}
