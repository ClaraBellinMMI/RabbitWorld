package gameInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gameActors.Dirt;
import gameEngine.Controller;

public class WindowParameters extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public WindowParameters() {
		Panel panneau = new Panel();
		add(panneau, BorderLayout.NORTH);
		Panel_Parameters panneau_label = new Panel_Parameters();
		add(panneau_label, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private class Panel extends JPanel {
		private static final long serialVersionUID = 1L;

		public Panel(){};
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			
			//image de lapin
		}
		
	}
	
	private class Panel_Parameters extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private JTextField nb_rabbits;
		private JTextField nb_regular_carrots;
		private JTextField nb_poison_carrots;
		
		private JLabel rabbit_label;
		private JLabel regular_carrot_label;
		private JLabel poison_carrot_label;
		
		private JButton start_button;
		
		public Panel_Parameters () {
			setLayout(new FlowLayout());
			rabbit_label = new JLabel("Nombre de lapins : ");
			regular_carrot_label = new JLabel("Nombre de carottes normales : ");
			poison_carrot_label = new JLabel("Nombre de carottes empoisonnées : ");
			
			nb_rabbits = new JTextField(3);
			nb_regular_carrots = new JTextField(3);
			nb_poison_carrots = new JTextField(3);
			
			add(rabbit_label);
			add(regular_carrot_label);
			add(poison_carrot_label);
			add(nb_rabbits);
			add(nb_regular_carrots);
			add(nb_poison_carrots);
			
			start_button = new JButton("START");
			add(start_button);
			StartAction startAct = new StartAction();
			start_button.addActionListener(startAct);
		}
		
		private class StartAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				init_grid();
			}	
		}
		
		public void init_grid() {
			String rabbit_param_str = nb_rabbits.getText();
			String reg_carrot_param_str = nb_regular_carrots.getText();
			String pois_carrot_param_str = nb_poison_carrots.getText();
			
			Integer rabbit_param = Controller.getInstance().unsigned(rabbit_param_str);
			Integer reg_carrot_param = Controller.getInstance().unsigned(reg_carrot_param_str);
			Integer pois_carrot_param = Controller.getInstance().unsigned(pois_carrot_param_str);
			
			int rli;
			int rco;
			boolean placed;
			for(int i = 0; i < rabbit_param; i++) {
				placed = false;
				do {
					rli = Controller.getInstance().getRandom().nextInt(Controller.getInstance().getGrid().getLi());
					rco = Controller.getInstance().getRandom().nextInt(Controller.getInstance().getGrid().getCo());
					if(Controller.getInstance().getGrid().getCells()[rli][rco].getContent() instanceof Dirt) {
						Controller.getInstance().rabbitBirth(true, rli, rco);
						placed = true;
					}
				} while(!placed);
			}
			
			for (int i = 0; i < reg_carrot_param; i++) {
				placed = false;
				do {
					rli = Controller.getInstance().getRandom().nextInt(Controller.getInstance().getGrid().getLi());
					rco = Controller.getInstance().getRandom().nextInt(Controller.getInstance().getGrid().getCo());
					if(Controller.getInstance().getGrid().getCells()[rli][rco].getContent() instanceof Dirt) {
						Controller.getInstance().carrotGrowth(true, rli, rco);
						placed = true;
					}
				} while(!placed);
			}
			
			for (int i = 0; i < pois_carrot_param; i++) {
				placed = false;
				do {
					rli = Controller.getInstance().getRandom().nextInt(Controller.getInstance().getGrid().getLi());
					rco = Controller.getInstance().getRandom().nextInt(Controller.getInstance().getGrid().getCo());
					if (Controller.getInstance().getGrid().getCells()[rli][rco].getContent() instanceof Dirt) {
						Controller.getInstance().carrotGrowth(false, rli, rco);
						placed = true;
					}
				} while(!placed);
			}

			//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Controller.getInstance().getWindow().add(Controller.getInstance().getMap());
			Controller.getInstance().getGrid().display();
		}
		
	}
	
}
