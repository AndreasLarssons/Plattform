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
	private Image background;
	private Hitbox hitbox;
	private SolidMaker solidmaker;
	public int map;
	public ArrayList<Solid> listofSolids = new ArrayList<Solid>();
	public ArrayList <Enemy> listofEnemys = new ArrayList<Enemy>();
	private Enemy enemy;
	

	public Play (int map) throws SlickException{
		this.map = map;
		listofSolids.clear();
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
		hitbox = new Hitbox();
		solidmaker = new SolidMaker(map, hitbox, gc, state, g, this);
	    listofSolids = 	solidmaker.getSolids();
	    	
	    
	    
		
		jumper = new Jumper(gc, state, g, listofSolids, hitbox, solidmaker); // Skapa ny instance av jumper
		jumper.init(); //kör jumperns init metod
		
		enemy = new Enemy(gc, state, g, listofSolids);
		enemy.init();
		
		for (int i = 0; i < listofSolids.size(); i++){
			listofSolids.get(i).init();
		 
		}
		
	
	}
	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g)
			throws SlickException {
	//	background.draw(0,0);
		this.g = g; // lägga det specifika grafikobjektet i en variabel
		jumper.render(); //kör jumperns render metod
		enemy.render();
		for (int i = 0; i < listofSolids.size(); i++){
			 listofSolids.get(i).render();
			 			 
			}
	
	
	}
	@Override
	public void update(GameContainer gc, StateBasedGame state, int update)
			throws SlickException {
		jumper.update(); //kör jumperns update metod
		enemy.update();
		for (int i = 0; i < listofSolids.size(); i++){
			 listofSolids.get(i).update();
			 
		}
	}
	@Override
	public int getID() {
		
		return 1;
	}
}
	