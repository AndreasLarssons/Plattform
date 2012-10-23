package classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Solid  {
	
	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	private int map;
	public float x = 300;
	public float y = 300;
	private Image img;
	
	public Solid (int map, GameContainer gc, StateBasedGame state, Graphics g){
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.map = map;
		
		
	}
	
	public void init (){
		
		try {
			img = new Image("res/solid.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void render (){
		img.draw(x,y);
	}
	
	
	
	public void update(){
		
	}
	
	

}
