package gameInterface;

import javax.swing.JFrame;

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;

	public Window(int w, int h){
		this.setTitle("RabbitWorld v0.5");
		this.setSize(w, h);
		this.setVisible(true);
	}
}
