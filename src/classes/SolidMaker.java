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
	
	private ArrayList<Solid> listOfSolids = new ArrayList<Solid>();
	
	
	public SolidMaker(Solid solid){
		listOfSolids.add(solid);
	}
	
	
	private void createSolids (){
		while (!listOfSolids.isEmpty()){
			
		}
	}

}
	
	
	
	



