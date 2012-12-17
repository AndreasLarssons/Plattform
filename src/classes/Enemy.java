package classes;

import java.awt.print.Printable;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import src.Debug;



public class Enemy extends Hitbox {
	
	private float x = 800;
	private float y = 300;
	private float cameraX = 10;
	private float cameraY = 10;
	private float gravity = 4;

	
	
	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	private ArrayList<Solid> solids = new ArrayList<Solid>();
	
	
	private Animation jump, movingLeft, movingRight, enemy, front;
	private int[] duration = {60,60}; //antalet ms för varje bild som animeras
	private Image bg;
	
	
	private Debug debug = new Debug(); //Debug metoder

	
	public Enemy (GameContainer gc, StateBasedGame state, Graphics g , ArrayList<Solid> solid){
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.solids = solid; // för att kunna se var solider befinner sig behöver vi dess objekt
	}
	
	
	
	public void init(){
	try{
			
			//bg = new Image("res/background.png");
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
		
		enemy = front;
	} 
	
	public void render(){
		enemy.draw(x - cameraX, y - cameraY);
	}
	
	public void update(){
		Input inp = gc.getInput();
	
		falling();
		respawn();//Debug metod
		
		
			if (inp.isKeyDown(inp.KEY_RIGHT)){
			
				cameraX += 5;
			}
		
		
			if (inp.isKeyDown(inp.KEY_LEFT)){

				cameraX -= 5;
		
			}
	}
	
	private void falling (){
		
		for (int i = 0; i < solids.size(); i++){
			
			if (groundHitTestY(x,y, solids.get(i), enemy.getHeight(), enemy.getWidth())&& 
					groundHitTestX(x, y, solids.get(i), enemy.getHeight(), enemy.getWidth())){
				debug.print("HEj");
				this.y -= gravity;
				
				
			}
		}
		this.y += 5;
	}
	
	private void respawn (){ //Debug respawn
		if (this.y > gc.getHeight()){
			this.y = 10;
		}
	}
}
	



