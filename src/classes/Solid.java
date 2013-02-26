package classes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Solid  {
	
	private Graphics g;
	private GameContainer gc;
	private StateBasedGame state;
	private int map;
	public float x;
	public float y;
	public float cameraX = 10;
	public float cameraY = 10;
	public float height;
	public float width;
	private Image img;
	private Hitbox hitbox;
	private ArrayList <Solid> listofSolids = new ArrayList();
	
	
	public boolean leftIsSideHit = false;
	public boolean rightIsSideHit = false;;
	
	public boolean isSideHit = false;
	public Solid (Image img,int map, GameContainer gc, StateBasedGame state, Graphics g , Hitbox hitbox , float x, float y , ArrayList<Solid> listofSolids){
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.map = map;
		this.hitbox = hitbox; // för att kunna lägga in solidens x/y i hitboxen
		this.x = x;
		this.y = y;
		this.listofSolids = listofSolids;
		this.img = img;

		
		
		
	}
	
	public void init (){
		
	
		this.height = img.getHeight();
		this.width = img.getWidth();
		
	}
	
	public void render (){
		
		img.draw(x - cameraX, y - cameraY);
	}
	
	
	
	public void update(){
		Input inp = gc.getInput();
		 // Kollar om man kolliderar med soliden i sidled då ska den inte röra sig i förhållande till spelaren
		
			
			
			
		
		
		}
	}
	
	
	
	



