package gameEngine;

public abstract class Rabbit {
	
	private int id; 
	private int life;
	private int age;
	private int sex; 
	
	public void eat(Carrot c){
		c.setEaten(true);
		gainLife(1);
	}
	
	public void gainLife(int nb){
		life = life + nb; 
	}
	
	public void die(){
		life = 0;
	}
	
	public int getSex() {
		return sex;
	}
	
	public boolean reproduce(Rabbit r){
		int sexR = r.getSex();
		if(this.getSex() != sexR){
			return true;
		}
		return false; 
	}
}
