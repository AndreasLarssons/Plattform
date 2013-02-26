package classes;

import java.util.ArrayList;

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
		img = new Image("res/hero.png");
	}


	
	public void render()
			throws SlickException {
		// TODO Auto-generated method stub
		//System.out.println("wad");
		img.draw(x, 300);
		
	}


	
	public void update()
			throws SlickException {
		// TODO Auto-generated method stub
		x += velocity;
	}


	
	
	
	
	
}
