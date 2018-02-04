package gameInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gameEngine.Controller;


public class WindowParameters extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public WindowParameters() {
		setBounds(100,100, 750, 500);
		setTitle("RabbitWorld GAME");
		Panel panneau= new Panel();
		panneau.setBackground(Color.ORANGE);
		panneau.setPreferredSize(new Dimension(750, 70));
		add(panneau, BorderLayout.NORTH);
		
		Panel_Parameters pann_param = new Panel_Parameters();
		pann_param.setBackground(Color.ORANGE);
		pann_param.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		pann_param.setPreferredSize(new Dimension(100,100));
		add(pann_param, BorderLayout.CENTER);
		
		Panel_img_sud pann_sud = new Panel_img_sud();
		pann_sud.setPreferredSize(new Dimension(750, 150));
		pann_sud.setBackground(Color.ORANGE);
		add(pann_sud, BorderLayout.SOUTH);
		
		Panel_img_ouest pann_ouest = new Panel_img_ouest();
		pann_ouest.setPreferredSize(new Dimension(260, 450));
		pann_ouest.setBackground(Color.ORANGE);
		add(pann_ouest, BorderLayout.WEST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private class Panel extends JPanel {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(Color.yellow);
			Font f = new Font("Courier", Font.BOLD, 20);
			g2.setFont(f);
			g2.drawString("WELCOME TO RABBITWORLD GAME !!!!", 150, 20);
			FontMetrics fm = g.getFontMetrics();
			int hauteur = fm.getHeight();
			g2.drawString("Enter rabbits' number & carrots' number then CLICK START ! ", 20, 20+hauteur);
		}
	}
	
	private class Panel_img_sud extends JPanel {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			String fichier = "C:/Users/isis/Downloads/lapin_bas_param.gif";
			try {
				BufferedImage im = ImageIO.read(new File(fichier));
				g2.drawImage(im, 150, 10, 400, 140, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class Panel_img_ouest extends JPanel {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			String fichier = "C:/Users/isis/Downloads/lapin_terrier.gif";
			try {
				BufferedImage im = ImageIO.read(new File(fichier));
				g2.drawImage(im, 10, 10, 220, 220, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			GridLayout gl = new GridLayout(4,1);
			this.setLayout(gl);
			gl.setHgap(40);
			gl.setVgap(50);
			
			rabbit_label = new JLabel("Rabbits' number : ");
			regular_carrot_label = new JLabel("Regular carrots' number : ");
			poison_carrot_label = new JLabel("Poisoned carrots' number : ");
			
			nb_rabbits = new JTextField(3);
			nb_regular_carrots = new JTextField(3);
			nb_poison_carrots = new JTextField(3);
			
			add(rabbit_label);
			add(nb_rabbits);
			add(regular_carrot_label);
			add(nb_regular_carrots);
			add(poison_carrot_label);
			add(nb_poison_carrots);
			
			start_button = new JButton("START");
			
			add(start_button, BorderLayout.SOUTH);
			StartAction startAct = new StartAction();
			start_button.addActionListener(startAct);
		}
		
		private class StartAction implements ActionListener {
			String rabbit_param_str = nb_rabbits.getText();
			String reg_carrot_param_str = nb_regular_carrots.getText();
			String pois_carrot_param_str = nb_poison_carrots.getText();
			
			Integer rabbit_param = Controller.getInstance().unsigned(rabbit_param_str);
			Integer reg_carrot_param = Controller.getInstance().unsigned(reg_carrot_param_str);
			Integer pois_carrot_param = Controller.getInstance().unsigned(pois_carrot_param_str);
			
		//	Controller.getInstance().setNb_field_rabbits(rabbit_param);
		//	Controller.getInstance().setNb_field_reg_carrots(reg_carrot_param);
		//	Controller.getInstance().setNb_field_pois_carrots(pois_carrot_param);
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rabbit_param != -1 || reg_carrot_param != -1 || pois_carrot_param != -1) {
					JFrame pop_error = new JFrame();
					pop_error.setTitle("Erreur de saisie");
					pop_error.setBounds(500, 250, 100, 50);
					Graphics g = getGraphics();
					g.drawString("ERREUR DE SAISIE, VEUILLEZ RECOMMENCER", 10, 10);
				} else {
					try {
						boolean ihm = true;
						Controller.getInstance().init(ihm);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}	
		}
		
	}
/**		public void init_grid(int rabbit_param, int reg_carrot_param, int pois_carrot_param) {
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

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.window = new Window(screenSize.width, screenSize.height);
			this.map = new Map();
			this.window.add(this.map);

			this.grid.display();
		}*/
	}

