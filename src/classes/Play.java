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
	private EnemyMaker enemymaker;
	public int map;
	public ArrayList<Solid> listofSolids = new ArrayList<Solid>();
	public ArrayList <Enemy> listofEnemies = new ArrayList<Enemy>();
	
	

	public Play (int map) throws SlickException{
		this.map = map;
		listofSolids.clear();
		listofEnemies.clear();
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
		hitbox = new Hitbox();
		

		solidmaker = new SolidMaker(map, hitbox, gc, state, g, this);
		Damage dmg = new Damage(gc, gc.getGraphics(), solidmaker, hitbox);
	    listofSolids = 	solidmaker.getSolids();
	   
	    
		
		
		enemymaker = new EnemyMaker(map, gc, state, g, listofSolids);
		listofEnemies = enemymaker.getEnemies();
		jumper = new Jumper(dmg, gc, state, gc.getGraphics(), listofSolids, listofEnemies, hitbox, solidmaker); // Skapa ny instance av jumper
		jumper.init(); //kör jumperns init metod
		
		for (int i = 0; i < listofEnemies.size(); i++){
			listofEnemies.get(i).init();
		}
		
		
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
		
		for (int i = 0; i < listofEnemies.size(); i++){
			listofEnemies.get(i).render();
		}
		
		for (int i = 0; i < listofSolids.size(); i++){
			 listofSolids.get(i).render();
			 			 
			}
		
	
	}
	@Override
	public void update(GameContainer gc, StateBasedGame state, int update)
			throws SlickException {
		jumper.update(); //kör jumperns update metod
		
		for (int i = 0; i < listofEnemies.size(); i++){
			listofEnemies.get(i).update();
		}

		for (int i = 0; i < listofSolids.size(); i++){
			 listofSolids.get(i).update();
			 
		}
		
		
	}
	@Override
	public int getID() {
		
		return 1;
	}
}
	