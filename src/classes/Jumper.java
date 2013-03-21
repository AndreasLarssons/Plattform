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
	private float gravity = 4;
	private float bgX = 0;

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

	private Animation jump, movingLeft, movingRight, jumper, front;
	private int[] duration = {60,60}; //antalet ms för varje bild som animeras
	private Image bg;
	
	private ArrayList <Bullet> bullets = new ArrayList<Bullet>();
	
	private ArrayList <Enemy> enemies = new ArrayList<Enemy>();

	private ArrayList<Solid> solids = new ArrayList<Solid>();
	private Damage dmg;
	private Hitbox hitbox;
	private Checkpoint checkpoint = new Checkpoint();
	
	
	
	public Jumper (Damage dmg, GameContainer gc, StateBasedGame state, Graphics g , ArrayList<Solid> solid , ArrayList<Enemy> enemies, Hitbox hitbox, SolidMaker solidmaker ) {
		/*
		 * Nödvändiga objekt för att kunna använda slick
		 */
		this.g = g;
		this.dmg = dmg;
		this.gc = gc;
		this.state = state;
		this.solids = solid; // för att kunna se var solider befinner sig behöver vi dess objekt
		this.hitbox = hitbox; // för att kunna lägga in postitioner i hitboxens metoder
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
		bg.draw(bgX - cameraX,0);
		jumper.draw(x - cameraX, y - cameraY);
		dmg.render();
	}
	
	public void update(){
		
		hitbox.setPlayerX(x);//Ger hitboxen spelarens x/y värden
		hitbox.setPlayerY(y);
		dmg.update();
		
		moving();// Metod för all rörelse
		headHit(); //Metod för kollision i huvudet
		falling(); // Metod för fall / mark kollison
		
		
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
	
		
		
	}	
	
	private void sideHit (){
		
		for (int i = 0 ; i < solids.size(); i++){
			if (hitbox.leftSideHitTest(x, y, jumper.getWidth(), jumper.getHeight(), solids.get(i) )){
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
			if (hitbox.groundHitTestY(x,y,solids.get(i) , jumper.getHeight(), jumper.getWidth()) &&
					hitbox.groundHitTestX(x,y,solids.get(i) , jumper.getHeight(), jumper.getWidth())){
				isOnGround = true; // om spelaren står på marken
				this.y -= gravity;//Normal kraft
				
			}
			
		}
		
		this.y += gravity;// Gravitation

	}
	
	private void headHit (){
		if (isJumping){
		
		for (int i = 0; i < solids.size(); i++){
			if (hitbox.headHitTest(x, y, solids.get(i), jumper.getHeight(), jumper.getWidth())&&
					hitbox.groundHitTestX(x,y,solids.get(i) , jumper.getHeight(), jumper.getWidth())){
				isJumping = false;
			//	System.out.println("wda");
			
			}
		}
		}
	}


	private void moving(){
		Input inp = gc.getInput(); // Hämta input objektet till variablen inp
		
		// Kollar om spelaren kolliderar med ett objekt i sidled
		//System.out.println("isrighthit  " + rightsideHit + "      islefthit  "+ leftsideHit);
		
			if (inp.isKeyDown(inp.KEY_RIGHT)){
			//System.out.println("MovingRight");
				jumper = movingRight;
				x += velocity;
				cameraX += velocity;
				for (int i = 0; i < solids.size(); i++){
					solids.get(i).cameraX += velocity;
				}
				
				for (int a = 0; a < enemies.size(); a++){
					enemies.get(a).cameraX += velocity;
				}
				
				
			}
	
	
			if (inp.isKeyDown(inp.KEY_LEFT)){
		//	System.out.println("MovingLeft");
				jumper = movingLeft;
				x -= velocity;
				cameraX -= velocity;
				for (int i = 0; i < solids.size(); i++){
					solids.get(i).cameraX -= 5;
				}
				
				for (int a = 0; a < enemies.size(); a++){
					enemies.get(a).cameraX -= velocity;
				}
				
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
		if (y == gc.getHeight()){//Dö när man faller ner
			dmg.takeDamage();
			isDead = true;
			//y = 0;//Test del så man slipper starta om
			
			//solidmaker.setMap(2); För att byta bana skriver man såhär på den plats man vill ha det
		}
		
		if (isDead){// Om man är död
			respawn(x,y);
		}
		
	}
	
	private void respawn(float x, float y){
		
		//this.x = checkpoint.getCheckpoint(x);
		this.x = x;
		System.out.println(checkpoint.getCheckpoint(x));
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
		Bullet bul = new Bullet(solids, hitbox, x, y, g); 
		bul.init();
		bul.render();
		//bullets.get(0).init();
		renderBullet();
		updateBullet();
	


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
