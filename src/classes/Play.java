package classes;




import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	
	private Jumper jumper;
	private Graphics g;
	private Solid solid , solid2 ;
	private ArrayList<Solid> listSolid = new ArrayList<Solid>();
	private Image background;
	private Hitbox hitbox;
	

	public Play (int state) throws SlickException{
		
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
	//	background = new Image("res/background.png");
		hitbox = new Hitbox();
	
		solid = new Solid(1,gc,state, g , hitbox , 300, 500);
		
		
		jumper = new Jumper(gc, state, g, solid, hitbox); // Skapa ny instance av jumper
		jumper.init(); //kör jumperns init metod
		solid.init();
	
	}
	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g)
			throws SlickException {
	//	background.draw(0,0);
		this.g = g; // lägga det specifika grafikobjektet i en variabel
		jumper.render(); //kör jumperns render metod
		
		
		solid.render();
	
	}
	@Override
	public void update(GameContainer gc, StateBasedGame state, int update)
			throws SlickException {
		jumper.update(); //kör jumperns update metod
		solid.update();
		
	}
	@Override
	public int getID() {
		
		return 1;
	}
}
	