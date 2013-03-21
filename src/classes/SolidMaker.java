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
	
	private void makeSolids(){ //Skapa solider beroende p� banan
		/*
		 * Solid skapandet ska ske enligt syntaxen
		 * 	listofSolids.add(new Solid(new Image("var bilden finns"),1, gc, state, g, hitbox,  x-position,  y-position, listofSolids));
		 */
		
		if (map == 1){//F�rsta banan
			try {
				//listofSolids.add(new Solid(new Image("res/solidblock.png"),1, gc, state, g, hitbox,  1000,  400, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w1024.png"),1, gc, state, g, hitbox,  0,  400, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  1240,  250, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  1900,  260, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  2600,  710, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  2600,  710, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w256.png"),1, gc, state, g, hitbox,  3200,  650, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w256.png"),1, gc, state, g, hitbox,  3550,  500, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w256.png"),1, gc, state, g, hitbox,  3880,  400, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  4200,  280, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_h512.png"),1, gc, state, g, hitbox,  4800,  50, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  4400,  650, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w1024.png"),1, gc, state, g, hitbox,  4400,  650, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  4400,  650, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  5600,  550, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  6200,  380, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w512.png"),1, gc, state, g, hitbox,  6900,  250, listofSolids));
				listofSolids.add(new Solid(new Image("res/Solid_w1024.png"),1, gc, state, g, hitbox,  7500,  380, listofSolids));
				
			} catch (Exception e){}
		} else if (map == 2){
			try {
				listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  700,  400, listofSolids));
				listofSolids.add(new Solid(new Image("res/solidBlock.png"),1, gc, state, g, hitbox,  500,  300, listofSolids));

			} catch (SlickException e) {}
		}
	}
	
	
	public ArrayList<Solid> getSolids(){ //F� soliderna
		
		return listofSolids;
	}
	
	public void setMap (int map){//F�r att �ndra banan anropas denna metod
		listofSolids.clear();
		this.map = map; 
		makeSolids();
		play.map = map;
		try {
			play.init(gc, state); // Starta om play klassen vilket kommer rita om banan efter vilken siffra variabeln map �r
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
	
	
	
	



