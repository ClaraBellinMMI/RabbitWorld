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

import gameEngine.Constants;
import gameEngine.Controller;


public class WindowParameters extends JFrame {
	private static final long serialVersionUID = 1L;


	public WindowParameters() {
		Controller.getInstance().setGameInited(false);
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
		this.toFront();
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
			g2.setColor(Color.red);
			f = new Font("Courier", Font.BOLD, 15);
			g2.setFont(f);
			g2.drawString("The map cannot contain more than " + 
					Constants.getMapWidth() * Constants.getMapHeight() + 
					" actors", 150, 40+hauteur);
		}
	}

	private class Panel_img_sud extends JPanel {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			String fichier = Constants.getPathRabbitGif();
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
			String fichier = Constants.getPathHoleGif();
			try {
				BufferedImage im = ImageIO.read(new File(fichier));
				g2.drawImage(im, 10, 10, 220, 220, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class Window_popup extends JFrame {
		private static final long serialVersionUID = 1L;
		public Window_popup() {
			this.setTitle("ERROR");
			this.setBounds(500, 250, 500, 280);
			Panel_popup pan_pop = new Panel_popup();
			pan_pop.setBackground(new Color(255,0,0,200));
			this.add(pan_pop);
			this.toFront();
			this.setVisible(true);
		}

		private class Panel_popup extends JPanel {
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.yellow);
				Font f = new Font("Courier", Font.BOLD, 20);
				g2.setFont(f);
				g2.drawString("ERROR, PLEASE CORRECT YOUR INPUT FIELDS", 10, 30);
				String fichier = Constants.getPathErrorGif();
				try {
					BufferedImage im = ImageIO.read(new File(fichier));
					g2.drawImage(im, 120, 40, 180, 200, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
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

		public Panel_Parameters() {
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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rabbit_param_str = nb_rabbits.getText();
				String reg_carrot_param_str = nb_regular_carrots.getText();
				String pois_carrot_param_str = nb_poison_carrots.getText();

				Integer rabbit_param = Controller.getInstance().unsigned(rabbit_param_str);
				Integer reg_carrot_param = Controller.getInstance().unsigned(reg_carrot_param_str);
				Integer pois_carrot_param = Controller.getInstance().unsigned(pois_carrot_param_str);

				Integer sumActors = rabbit_param + reg_carrot_param + pois_carrot_param;

				Controller.getInstance().setIhm(true);
				if(rabbit_param != -1 && 
						reg_carrot_param != -1 && 
						pois_carrot_param != -1 && 
						sumActors <= Constants.getMapWidth() * Constants.getMapHeight()) {
					try {
						Controller.getInstance().setNb_field_rabbits(rabbit_param);
						Controller.getInstance().setNb_field_reg_carrots(reg_carrot_param);
						Controller.getInstance().setNb_field_pois_carrots(pois_carrot_param);
						Controller.getInstance().init(true);
						Controller.getInstance().setGameInited(true);
						Controller.getInstance().setGameStarted(false);
						JFrame win = Controller.getInstance().getWindow();
						win.setBounds(0, 0, 
								Constants.getMapWidth() * 32 + 20, 
								Constants.getMapHeight() * 32 + 45);
					} catch (IOException e) {
						e.printStackTrace();
					}			
				} else {
					new Window_popup();
				}
			}	
		}
	}
}
