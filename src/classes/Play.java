package classes;




import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	
	private Jumper jumper;
	private Graphics g;
	

	public Play (int state) throws SlickException{
		
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
		jumper = new Jumper(gc, state, g); // Skapa ny instance av jumper
		jumper.init(); //k�r jumperns init metod
	}
	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g)
			throws SlickException {
		this.g = g; // l�gga det specifika grafikobjektet i en variabel
		jumper.render(); //k�r jumperns render metod
		
	}
	@Override
	public void update(GameContainer gc, StateBasedGame state, int update)
			throws SlickException {
		jumper.update(); //k�r jumperns update metod
		
	}
	@Override
	public int getID() {
		
		return 1;
	}
}
	