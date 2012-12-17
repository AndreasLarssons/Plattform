package classes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SolidMaker  {
	
	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	private int map;
	private Hitbox hitbox;
	private Play play;
	private ArrayList<Solid> listofSolids = new ArrayList<Solid>();

	
	public SolidMaker(int map, Hitbox hitbox, GameContainer gc, StateBasedGame state, Graphics g, Play play){
		this.map = map;
		this.hitbox = hitbox;
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.play = play;
		makeSolids();
	}
	
	private void makeSolids(){ //Skapa solider beroende på banan
		if (map == 1){
		listofSolids.add(new Solid(1, gc, state, g, hitbox,  1000,  500, listofSolids));
    	listofSolids.add(new Solid(1, gc, state, g, hitbox,  550,  500, listofSolids));
    	listofSolids.add(new Solid(1, gc, state, g, hitbox,  1500,  500, listofSolids));
		} else if (map == 2){
			
		}
	}
	public ArrayList<Solid> getSolids(){ //Få soliderna
		
		return listofSolids;
	}
	
	public void setMap (int map){
		this.map = map;
		makeSolids();
		play.map = map;
		try {
			play.init(gc, state);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
	
	
	
	



