package classes;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Jumper extends Hitbox  {
	
	private float velocity = 5;
	private float x = 40;
	private float y = 40;
	private float cameraX = 10;
	private float cameraY = 10;
	private float gravity = 4;

	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	private SolidMaker solidmaker;
	
	private boolean isOnGround = false;
	private boolean leftsideHit = false;
	private boolean rightsideHit = false;
	private boolean isJumping = false;
	private float startY;

	private Animation jump, movingLeft, movingRight, jumper, front;
	private int[] duration = {60,60}; //antalet ms för varje bild som animeras
	private Image bg;
	
	
	

	private ArrayList<Solid> solids = new ArrayList<Solid>();
	
	private Hitbox hitbox;
	
	
	
	public Jumper (GameContainer gc, StateBasedGame state, Graphics g , ArrayList<Solid> solid, Hitbox hitbox, SolidMaker solidmaker ) {
		/*
		 * Nödvändiga objekt för att kunna använda slick
		 */
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.solids = solid; // för att kunna se var solider befinner sig behöver vi dess objekt
		this.hitbox = hitbox; // för att kunna lägga in postitioner i hitboxens metoder
		this.solidmaker = solidmaker;
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
		bg.draw(0 - cameraX,0);
		jumper.draw(x - cameraX, y - cameraY);
		
	}
	
	public void update(){
		
		hitbox.setPlayerX(x);//Ger hitboxen spelarens x/y värden
		hitbox.setPlayerY(y);
		
		
		moving();// Metod för all rörelse
		
		falling(); // Metod för fall / mark kollison
		for (int i = 0; i < solids.size(); i++){//Loopa igenom alla solider
		
			if (hitbox.leftSideHitTest(jumper.getHeight() , jumper.getWidth())){ // om spelaren träffar en solid i sidan
				leftsideHit = true;
				solids.get(i).leftIsSideHit = true;
				System.out.println("isSideHit");
			} else {
				solids.get(i).leftIsSideHit = false;
			}
		}
		
		for (int i = 0; i < solids.size(); i++){ //Loopa igenom alla solider
			if (hitbox.rightSideHitTest(jumper.getHeight() , jumper.getWidth())){
				rightsideHit = true;
				solids.get(i).rightIsSideHit = true;
			
				System.out.println("isSideHit");
			} else {
			
				solids.get(i).rightIsSideHit = false;
			}
		
		}
		
	}
	
	
	private void falling() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < solids.size(); i++){//Loopa igenom alla solider
			if (hitbox.groundHitTestY(x,y,solids.get(i) , jumper.getHeight(), jumper.getWidth()) &&
					hitbox.groundHitTestX(x,y,solids.get(i) , jumper.getHeight(), jumper.getWidth())){
				isOnGround = true; // om spelaren står på marken
				
				this.y -= gravity;//Normal kraft
			}
		}
		
		this.y += gravity;// Gravitation

	}


	private void moving(){
		Input inp = gc.getInput(); // Hämta input objektet till variablen inp
		
		// Kollar om spelaren kolliderar med ett objekt i sidled
		//System.out.println("isrighthit  " + rightsideHit + "      islefthit  "+ leftsideHit);
		if(!rightsideHit){
			if (inp.isKeyDown(inp.KEY_RIGHT)){
			//System.out.println("MovingRight");
			jumper = movingRight;
			x += velocity;
			cameraX += velocity;
			}
		} else {
			
			rightsideHit = false;
			for (int i = 0; i < solids.size(); i++)
				solids.get(i).rightIsSideHit = false;
		}
		if (!leftsideHit){
			if (inp.isKeyDown(inp.KEY_LEFT)){
		//	System.out.println("MovingLeft");
			jumper = movingLeft;
			x -= velocity;
			cameraX -= velocity;
			}
		} else {
			leftsideHit = false;
			for (int i = 0; i < solids.size(); i++)
				solids.get(i).leftIsSideHit = false;
		}
		
		if  (isOnGround){ // om spelaren står på marken kan man hoppa
			if (inp.isKeyDown(inp.KEY_UP)){
				jumper = jump; // sätt animationen till jump
				startY = this.y;
				isOnGround = false;
				isJumping = true;
			}
			
		}
		if (isJumping){
			
			this.y -= 12;
			if (this.y <= startY - 60){
				this.y -= 2;
			}
			if (this.y <= startY - 130 ){
				isJumping = false;
			}
		}
		if (y > gc.getHeight()){
			y = 0;//Test del så man slipper starta om
			
			//solidmaker.setMap(2); För att byta bana skriver man såhär på den plats man vill ha det
		}
	}
	
	
	


}
