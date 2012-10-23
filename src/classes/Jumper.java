package classes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Jumper  {
	
	private float velocity = 5;
	private float x = 40;
	private float y = 40;
	
	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;

	private Animation jump, movingLeft, movingRight, jumper, front;
	private int[] duration = {60,60}; //antalet ms för varje bild som animeras

	private float falling = 6;
	private Solid solid;
	private float leftHitPoint, rightHitPoint, bottomHitPoint, topHitPoint;
	
	private Hitbox hitbox;
	
	
	
	public Jumper (GameContainer gc, StateBasedGame state, Graphics g , Solid solid){
		/*
		 * Nödvändiga objekt för att kunna använda slick
		 */
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.solid = solid; // för att kunna se var solider befinner sig behöver vi dess objekt
		hitbox = new Hitbox();
		x = gc.getWidth() /2;
		y = gc.getHeight() / 2;
	}
	
	
	public void init (){
		/*
		 * Array med varje bild som ska animeras 
		 */
		try{
			Image[]walkRight = {new Image("res/hero.png"), new Image("res/hero.png")};
			Image[]walkLeft = {new Image("res/heroLeft.png"), new Image("res/heroLeft.png")};
			Image[]jumpImg = {new Image("res/hero.png"), new Image("res/hero.png")};
			Image[]frontImg= {new Image("res/hero.png"), new Image("res/hero.png")};
		
			jump = new Animation(jumpImg, duration, false);
			movingLeft = new Animation(walkLeft, duration, false);
			movingRight = new Animation(walkRight, duration, false);
			front = new Animation(frontImg, duration, false);
		} catch (Exception e){
			
		}
		
		jumper = front;
	
	
		
	}
	
	public void render(){
	
		jumper.draw(x, y);
		
	}
	
	public void update(){
		Input inp = gc.getInput(); // Hämta input objektet till variablen inp
	
		if (inp.isKeyDown(inp.KEY_UP)){
			jumper = jump; // sätt animationen till jump
			
		}
		if (inp.isKeyDown(inp.KEY_RIGHT)){
			jumper = movingRight;
			x += velocity;
		}
		if (inp.isKeyDown(inp.KEY_LEFT)){
			jumper = movingLeft;
			x -= velocity;
		}
		
		
		
		if (hitbox.hitTest(solid)){
			
		} else {
			//this.y += 2; 
		}
	}
	


}
