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
	private boolean leftsideHit = false;
	private boolean rightsideHit = false;
	private boolean isJumping = false;
	private float startY;

	private Animation jump, movingLeft, movingRight, jumper, front;
	private int[] duration = {60,60}; //antalet ms för varje bild som animeras
	private Image bg;

	private Solid solid;

	private Hitbox hitbox;



	public Jumper (GameContainer gc, StateBasedGame state, Graphics g , Solid solid, Hitbox hitbox ) {
		/*
		 * Nödvändiga objekt för att kunna använda slick
		 */
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.solid = solid; // för att kunna se var solider befinner sig behöver vi dess objekt
		this.hitbox = hitbox; // för att kunna lägga in postitioner i hitboxens metoder
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

		hitbox.setPlayerX(x - cameraX);//Ger hitboxen spelarens x/y värden
		hitbox.setPlayerY(y - cameraY);


		moving();// Metod för all rörelse
		if (hitbox.groundHitTest(solid , jumper.getHeight(), jumper.getWidth())){
			isOnGround = true; // om spelaren står på marken

		} else {
			isOnGround = false;
			this.y += 4;// Gravitation
			//cameraY -= 0.5;
		}
		if (hitbox.leftSideHitTest(jumper.getHeight() , jumper.getWidth())){ // om spelaren träffar en solid i sidan
			leftsideHit = true;
			solid.leftIsSideHit = true;
			System.out.println("isSideHit");
		} else {
			solid.leftIsSideHit = false;
		}

		if (hitbox.rightSideHitTest(jumper.getHeight() , jumper.getWidth())){
			rightsideHit = true;
			solid.rightIsSideHit = true;
			System.out.println("isSideHit");
		} else {
			solid.rightIsSideHit = false;
		}



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
			cameraX += 5;
			}
		} else {
			rightsideHit = false;
			solid.rightIsSideHit = false;
		}
		if (!leftsideHit){
			if (inp.isKeyDown(inp.KEY_LEFT)){
		//	System.out.println("MovingLeft");
			jumper = movingLeft;
			x -= velocity;
			cameraX -= 5;
			}
		} else {
			leftsideHit = false;
			solid.leftIsSideHit = false;
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

			this.y -= 10;
			if (this.y <= startY - 60){
				this.y -= 2;
			}
			if (this.y <= startY - 130 ){
				isJumping = false;
			}
		}
	}





}