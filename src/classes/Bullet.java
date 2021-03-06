package classes;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet {
	

	private ArrayList<Solid> listOfSolids = new ArrayList<Solid>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private Hitbox hitbox;
	private Image img;
	private float playerX;
	private float playerY;
	private float velocity = 5;
	private float x = 100;
	private float y;
	private Graphics g;
	private Animation anim;

	public Bullet (ArrayList<Solid> solid, Hitbox hitbox, float x , float y, Graphics g){
		this.playerX = x;
		this.playerY = y;
		this.g = g;
		this.listOfSolids = solid;
		this.hitbox = hitbox;
		try {
			init();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
	public void init()
			throws SlickException {
		// TODO Auto-generated method stub
		int [] duration = {60,60};
		Image imgArray[] = {new Image("res/hero.png"),new Image("res/hero.png")};
		 anim = new Animation(imgArray, duration , false);
		 img = new Image("res/hero.png");
	}


	
	public void render()
			throws SlickException {
		// TODO Auto-generated method stub
		//img.draw(x, 300);
		//anim.draw(300,300);
		
		img.draw(0,0);
	}


	
	public void update()
			throws SlickException {
		// TODO Auto-generated method stub
	//	x += velocity;
	}


	
	
	
	
	
}
