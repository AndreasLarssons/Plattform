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
	public int map;
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
		makeSolids();//Skapa solider
	}
	
	private void makeSolids(){ //Skapa solider beroende på banan
		/*
		 * Solid skapandet ska ske enligt syntaxen
		 * 	listofSolids.add(new Solid(new Image("var bilden finns"),1, gc, state, g, hitbox,  x-position,  y-position, listofSolids));
		 */
		
		if (map == 1){//Första banan
			try {
		listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  700,  400, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  500,  500, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  300,  400, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  1000,  450, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  1400,  450, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  1700,  450, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  2000,  450, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  2500,  400, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  3000,  350, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  3500,  400, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  4000,  450, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  4500,  500, listofSolids));
    	listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  5000,  500, listofSolids));
			} catch (Exception e){}
		} else if (map == 2){
			try {
				listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  700,  400, listofSolids));
				listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  500,  300, listofSolids));

			} catch (SlickException e) {}
		}
	}
	
	
	public ArrayList<Solid> getSolids(){ //Få soliderna
		
		return listofSolids;
	}
	
	public void setMap (int map){//För att ändra banan anropas denna metod
		this.map = map; 
		makeSolids();
		play.map = map;
		try {
			play.init(gc, state); // Starta om play klassen vilket kommer rita om banan efter vilken siffra variabeln map är
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
	
	
	
	



