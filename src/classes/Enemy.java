package classes;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;



public class Enemy extends Hitbox {
	
	protected float x;
	protected float y;
	public float cameraX = 10;
	private float cameraY = 10;
	protected float gravity = 4;
	protected int direction = 1;
	protected float velocity = 3;
	
	
	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	protected ArrayList<Solid> solids = new ArrayList<Solid>();
	
	
	private Animation jump, movingLeft, movingRight;
	public Animation enemy;
	private Animation front;
	private int[] duration = {60,60}; //antalet ms för varje bild som animeras
	private Image bg;
	
	
	//private Debug debug = new Debug(); //Debug metoder

	
	public Enemy (GameContainer gc, StateBasedGame state, Graphics g , ArrayList<Solid> solid, float x, float y){
		this.x = x;
		this.y = y;
		this.g = g;
		this.gc = gc;
		this.state = state;
		
		this.solids = solid; // för att kunna se var solider befinner sig behöver vi dess objekt
	}
	
	
	
	public void init(){
	try{
			
			//bg = new Image("res/background.png");
			Image[]walkRight = {new Image("res/Tiger_Right.png"), new Image("res/Tiger2.png")};
			Image[]walkLeft = {new Image("res/Tiger_Left.png"), new Image("res/Tiger3.png")};
			Image[]jumpImg = {new Image("res/hero.png"), new Image("res/hero.png")};
			Image[]frontImg= {new Image("res/Tiger_Right.png"), new Image("res/hero.png")};
		
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
		
		
			
	}
	
	protected void falling (){
		
		
		for (int i = 0; i < solids.size(); i++){
			
			if (groundHitTestY(x, y, solids.get(i), enemy.getHeight() - 10, enemy.getWidth()) && 
					groundHitTestX(x, y, solids.get(i), enemy.getHeight(), enemy.getWidth())){
				this.y -= gravity;
				moving();// Om den står på marken ska den röra sig
				
			}
		}
		
		this.y += gravity;
	}
	
	
	protected void moving (){
		
		for (int i = 0; i < solids.size(); i++){
			if (groundHitTestX(x, y, solids.get(i), enemy.getHeight(), enemy.getWidth())){ // Kollar om fienden
																						//finns innanför en solids x och width
				
				if (this.x < solids.get(i).x){
					direction = -1;//Gå åt höger
					enemy = movingRight;;
				}
				
				if (this.x >= solids.get(i).x + solids.get(i).width - enemy.getWidth()){
					enemy = movingLeft;
					direction = 1;//Gå åt vänster

				}
			}
		}
		
		
		this.x -= velocity * direction;	//Hastighet i x led * vilket håll hastigheten ska vara åt
	}
	

	
	
	private void respawn (){ //Debug respawn
		if (this.y > gc.getHeight()){
			this.y = 10;
		}
	}
}
	



