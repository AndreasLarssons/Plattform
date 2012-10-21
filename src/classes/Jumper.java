package classes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Jumper  {
	
	private float velocity;
	private float x;
	private float y;
	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	private Animation jump, movingLeft, movingRight, jumper, front;
	private int[] duration = {200,200}; //antalet ms för varje bild som animeras
	
	
	
	public Jumper (GameContainer gc, StateBasedGame state, Graphics g){
		/*
		 * Nödvändiga objekt för att kunna använda slick
		 */
		this.g = g;
		this.gc = gc;
		this.state = state;
		
		x = gc.getWidth() /2;
		y = gc.getHeight() / 2;
	}
	
	
	public void init (){
		/*
		 * Array med varje bild som ska animeras 
		 */
		Image[]walkRight = {};
		Image[]walkLeft = {};
		Image[]jumpImg = {};
		Image[]frontImg= {};
		
		jump = new Animation(jumpImg, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		front = new Animation(frontImg, duration, false);
		
		
		jumper = front;
		
	}
	
	public void render(){
		jumper.draw(x,y);
	}
	
	public void update(){
		Input inp = gc.getInput(); // Hämta input objektet till variablen inp
		if (inp.isKeyDown(inp.KEY_UP)){
			jumper = jump; // sätt animationen till jump
			
		}
		if (inp.isKeyDown(inp.KEY_RIGHT)){
			jumper = movingRight;
			x += 10;
		}
		if (inp.isKeyDown(inp.KEY_LEFT)){
			jumper = movingLeft;
			x -= 10;
		}
	}
	
	private boolean hitTest (){
		return false;
		
	}

}
