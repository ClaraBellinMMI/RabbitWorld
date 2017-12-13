package gameEngine;

public class Grid implements Displayable {
	private int li;
	private int co;
	private Cell cells[][] = null;

	public Grid(int width, int height) { 
		this.li = width;
		this.co = height;
		this.cells = new Cell[width][height];

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				this.cells[i][j] = new Cell();
			}
		}
	}

	public void testDisplay() {
		for(int i = 0; i < this.li; i++) {
			for(int j = 0; j < this.co; j++) {
				this.cells[i][j].setContent(new TestDirt());
			}
		}
	}
	
	@Override
	public void display() {
		for(int i = 0; i < this.li; i++) {
			for(int j = 0; j < this.co; j++) {
				System.out.print(" ");
				this.cells[i][j].getContent().display();
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
