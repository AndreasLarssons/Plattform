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
	private float cameraX = 50;
	private float cameraY = 50;

	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	
	private boolean isOnGround = false;
	private boolean sideHit = false;

	private Animation jump, movingLeft, movingRight, jumper, front;
	private int[] duration = {60,60}; //antalet ms f�r varje bild som animeras
	private Image bg;

	private float falling = 6;
	private Solid solid;
	private float leftHitPoint, rightHitPoint, bottomHitPoint, topHitPoint;
	
	private Hitbox hitbox;
	
	
	
	public Jumper (GameContainer gc, StateBasedGame state, Graphics g , Solid solid, Hitbox hitbox ) {
		/*
		 * N�dv�ndiga objekt f�r att kunna anv�nda slick
		 */
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.solid = solid; // f�r att kunna se var solider befinner sig beh�ver vi dess objekt
		this.hitbox = hitbox; // f�r att kunna l�gga in postitioner i hitboxens metoder
		hitbox = new Hitbox();
		x = gc.getWidth() /2;
		y = gc.getHeight() / 2  - 40;
	}
	
	
	public void init (){
		/*
		 * Array med varje bild som ska animeras 
		 */
		try{
			
			bg = new Image("res/background.png");
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
		bg.draw(0 - cameraX, 0 - cameraY);
		jumper.draw(x - cameraX, y - cameraY);
		
	}
	
	public void update(){
		
		hitbox.setPlayerX(x - cameraX);//Ger hitboxen spelarens x/y v�rden
		hitbox.setPlayerY(y - cameraY);
		
		
		moving();// Metod f�r all r�relse
		if (hitbox.groundHitTest(solid , jumper.getHeight(), jumper.getWidth())){
			isOnGround = true; // om spelaren st�r p� marken
			
		} else {
			
			this.y += 4;// Gravitation
			//cameraY -= 0.5;
		}
		if (hitbox.sideHitTest(jumper.getHeight() , jumper.getWidth())){ // om spelaren tr�ffar en solid i sidan
			sideHit = true;
			solid.isSideHit = true;
			System.out.println("isSideHit");
		}
		
		
		
	}
	
	private void moving(){
		Input inp = gc.getInput(); // H�mta input objektet till variablen inp
		
		if(!sideHit){ // Kollar om spelaren kolliderar med ett objekt i sidled
	
			if (inp.isKeyDown(inp.KEY_RIGHT)){
			System.out.println("MovingRight");
			jumper = movingRight;
			x += velocity;
			cameraX += 5;
			}
			if (inp.isKeyDown(inp.KEY_LEFT)){
			System.out.println("MovingLeft");
			jumper = movingLeft;
			x -= velocity;
			cameraX -= 5;
			}
		
		} else {
			sideHit = false;
			solid.isSideHit = false;//G�r s� soliderna kan r�ra p� sig i f�rh�llande till spelaren om man inte krockar med dem i sidled
		}
		if  (isOnGround){ // om spelaren st�r p� marken kan man hoppa
			if (inp.isKeyDown(inp.KEY_UP)){
				jumper = jump; // s�tt animationen till jump
				this.y -= 80;
				isOnGround = false;
			}
		}
	}
	


}
