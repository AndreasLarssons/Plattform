package classes;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Jumper  {
	
	private float velocity = 5;
	private float x = 40;
	private float y = 40;
	private float cameraX = 10;
	private float cameraY = 10;
	private float gravity = 5;
	private float bgX = - 650;
	private float jumpspeed = 10;
	private float jumpheight = 200; // Ju mer desto h�gre

	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	private SolidMaker solidmaker;
	
	private boolean isOnGround = false;
	private boolean leftsideHit = false;
	private boolean rightsideHit = false;
	private boolean isJumping = false;
	private float startY;
	private boolean isDead = false;

	private Animation jumpleft,jumpright, movingLeft, movingRight, jumper, front;
	private int[] duration = {60,60}; //antalet ms f�r varje bild som animeras
	private Image bg;
	
	private ArrayList <Bullet> bullets = new ArrayList<Bullet>();
	
	private ArrayList <Enemy> enemies = new ArrayList<Enemy>();

	private ArrayList<Solid> solids = new ArrayList<Solid>();
	private Damage dmg;
	private Hitbox hitbox;
	private Checkpoint checkpoint = new Checkpoint();
	
	
	
	public Jumper (Damage dmg, GameContainer gc, StateBasedGame state, Graphics g , ArrayList<Solid> solid , ArrayList<Enemy> enemies, Hitbox hitbox, SolidMaker solidmaker ) {
		/*
		 * N�dv�ndiga objekt f�r att kunna anv�nda slick
		 */
		this.g = g;
		this.dmg = dmg;
		this.gc = gc;
		this.state = state;
		this.solids = solid; // f�r att kunna se var solider befinner sig beh�ver vi dess objekt
		this.hitbox = hitbox; // f�r att kunna l�gga in postitioner i hitboxens metoder
		this.solidmaker = solidmaker;
		this.enemies = enemies;
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
			Image[]walkRight = {new Image("res/Jumper_Right.png"), new Image("res/Jumper_Right.png")};
			Image[]walkLeft = {new Image("res/Jumper_Left.png"), new Image("res/Jumper_Left.png")};
			Image[]jumpLeft = {new Image("res/Jumper_jumpleft.png"), new Image("res/Jumper_jumpleft.png")};
			Image[]jumpRight= {new Image("res/Jumper_jumpright.png"), new Image("res/Jumper_jumpright.png")};
		
			jumpleft = new Animation(jumpLeft, duration, false);
			movingLeft = new Animation(walkLeft, duration, false);
			movingRight = new Animation(walkRight, duration, false);
			jumpright = new Animation(jumpRight, duration, false);
		} catch (Exception e){
			
		}
		
		jumper = movingRight;
	
	
		
	}
	
	public void render(){
		bg.draw(bgX - cameraX,0);
		jumper.draw(x - cameraX, y - cameraY);
		dmg.render();
	}
	
	public void update(){
		
		hitbox.setPlayerX(x);//Ger hitboxen spelarens x/y v�rden
		hitbox.setPlayerY(y);
		dmg.update();
		
		moving();// Metod f�r all r�relse
		headHit(); //Metod f�r kollision i huvudet
		falling(); // Metod f�r fall / mark kollison
		
		
		if (gc.getInput().isKeyDown(gc.getInput().KEY_SPACE)){
			try {
				shoot();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sideHit();
		enemyHit();
		if (this.x > 8100){
			outro();
		}
		
		
	}	
	
	private void sideHit (){
		
		for (int i = 0 ; i < solids.size(); i++){
			if (hitbox.leftSideHitTest(x, y, jumper.getHeight(), solids.get(i) )){
				this.x += velocity;
				this.cameraX += velocity;
				
				for (int x = 0; x < solids.size(); x++){
					solids.get(x).cameraX += velocity;
				}
				
				for (int a = 0; a < enemies.size(); a++){
					enemies.get(a).cameraX += velocity;
				}
				
				
				//solids.get(i).cameraX += 5 / solids.size();
				//solids.get(i).isSideHit = true;
				
				
			
				
			} else if (hitbox.rightSideHitTest(x, y, jumper.getWidth(), jumper.getHeight(), solids.get(i))){
				this.x -= velocity;
				this.cameraX -= velocity;
				//solids.get(i).cameraX -= 5 / solids.size();
				//solids.get(i).isSideHit = true;
				
				for (int x = 0; x < solids.size(); x++){
					solids.get(x).cameraX -= velocity;
				}
				
				for (int a = 0; a < enemies.size(); a++){
					enemies.get(a).cameraX -= velocity;
				}
				
				
				
			}
		}
		
		
			
	}
	
	
	private void falling() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < solids.size(); i++){//Loopa igenom alla solider
			if (hitbox.groundHitTestY(x,y,solids.get(i) , jumper.getHeight() - 10, jumper.getWidth()) &&
					hitbox.groundHitTestX(x,y,solids.get(i) , jumper.getHeight(), jumper.getWidth())){
				isOnGround = true; // om spelaren st�r p� marken
				this.y -= gravity;//Normal kraft
				
			}
			
		}
		
		this.y += gravity;// Gravitation

	}
	
	private void headHit (){
		if (isJumping && !isOnGround){
			
		for (int i = 0; i < solids.size(); i++){
			
			if (hitbox.headHitTest(x, y, solids.get(i), jumper.getHeight(), jumper.getWidth())&&
					hitbox.groundHitTestX(x,y,solids.get(i) , jumper.getHeight(), jumper.getWidth())){
				
				isJumping = false;
				
				
				System.out.println("wda");
			
			}
		}
		}
	}
	
	private void outro (){
		state.enterState(3);
	}


	private void moving(){
		Input inp = gc.getInput(); // H�mta input objektet till variablen inp
		
		// Kollar om spelaren kolliderar med ett objekt i sidled
		//System.out.println("isrighthit  " + rightsideHit + "      islefthit  "+ leftsideHit);
		
			if (inp.isKeyDown(inp.KEY_RIGHT)){
			//System.out.println("MovingRight");
				
				x += velocity;
				cameraX += velocity;
				for (int i = 0; i < solids.size(); i++){
					solids.get(i).cameraX += velocity;
				}
				
				for (int a = 0; a < enemies.size(); a++){
					enemies.get(a).cameraX += velocity;
				}
				
				if (isJumping){
					jumper = jumpright;
				} else {
					jumper = movingRight;
				}
				
				
			}
	
	
			if (inp.isKeyDown(inp.KEY_LEFT)){
		//	System.out.println("MovingLeft");
				jumper = movingLeft;
				x -= velocity;
				cameraX -= velocity;
				for (int i = 0; i < solids.size(); i++){
					solids.get(i).cameraX -= velocity;
				}
				
				for (int a = 0; a < enemies.size(); a++){
					enemies.get(a).cameraX -= velocity;
				}
				if (isJumping){
					jumper = jumpleft;
				} else {
					jumper = movingLeft;
				}
				
			}
		
		
		if  (isOnGround){ // om spelaren st�r p� marken kan man hoppa
			if (inp.isKeyDown(inp.KEY_UP)){
				
				startY = this.y;
				isOnGround = false;
				isJumping = true;
				
			}
			
		}
		if (isJumping){
			
			this.y -= 12;
			if (this.y <= startY - 60){
				//this.y -= -2;
			}
			if (this.y <= startY - jumpheight ){
				isJumping = false;
				isOnGround = false;
				
			}
		}
		if (y > gc.getHeight()){//D� n�r man faller ner
			dmg.takeDamage();
			isDead = true;
			//this.y = 0;//Test del s� man slipper starta om
			
			//solidmaker.setMap(2); F�r att byta bana skriver man s�h�r p� den plats man vill ha det
		}
		
		if (isDead){// Om man �r d�d
			respawn(x);
		}
		
	}
	
	private void respawn(float x){
		
		//this.x = checkpoint.getCheckpoint(x);
		this.x = x;
		this.y = 0;
		isDead = false;
	}
	
	private void enemyHit () {//Kolla om du kolliderar med fiender
		for (int i = 0; i < enemies.size(); i++){
			if (hitbox.enemyHitTest( x, y, enemies.get(i), jumper.getWidth(), jumper.getHeight())){
				dmg.takeDamage();//Ta skada
				isDead = true;
			}
		}
	}
	
	private void shoot() throws SlickException{
			
		//System.out.println("awda");
		Bullet bul = new Bullet(solids, hitbox, x, y, gc.getGraphics()); 
		bul.init();
		bul.render();
		//bullets.get(0).init();
	//	renderBullet();
		//updateBullet();
	


	}
	
	private void renderBullet (){
		for (int i = 0; i < bullets.size(); i++){
			try {
				bullets.get(i).render();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void updateBullet (){
		for (int i = 0; i < bullets.size(); i++){
			try {
				bullets.get(i).update();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
