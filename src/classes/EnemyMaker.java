package classes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class EnemyMaker {
	
	private GameContainer gc;
	private StateBasedGame state;
	private Graphics g;
	private ArrayList<Solid> solids = new ArrayList<Solid>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int map;
	
	
	
	public EnemyMaker (int map, GameContainer gc, StateBasedGame state, Graphics g , ArrayList<Solid> solid){
		this.gc = gc;
		this.state = state;
		this.g = g;
		this.solids = solid;
		this.map = map;
		makeEnemies();
	}
	
	
	private void makeEnemies (){ // Skapa fienderna
		if (map == 1){//Vilken bana
			enemies.add(new Enemy(gc, state, g, solids,1240,0));
			enemies.add(new Enemy(gc, state, g, solids,1900,0));
			enemies.add(new Enemy(gc, state, g, solids,2600,0));
			enemies.add(new Enemy(gc, state, g, solids,6200,0));
			//enemies.add(new Enemy(gc, state, g, solids,1500,200));
			//enemies.add(new Enemy(gc, state, g, solids,2000,200));
			
		} else if (map == 2){
			
		}
		
	}
	
public ArrayList<Enemy> getEnemies(){ //Få fienderna
		
		return enemies;
	}
	
}
