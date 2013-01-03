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
	public float x = 300;
	public float y = 500;
	private float cameraX = 10;
	private float cameraY = 10;
	public float height;
	public float width;
	private Image img;
	private Hitbox hitbox;
	private ArrayList <Solid> listofSolids = new ArrayList();
	
	
	public boolean leftIsSideHit = false;
	public boolean rightIsSideHit = true;
	
	public Solid (int map, GameContainer gc, StateBasedGame state, Graphics g , Hitbox hitbox , float x, float y , ArrayList<Solid> listofSolids){
		this.g = g;
		this.gc = gc;
		this.state = state;
		this.map = map;
		this.hitbox = hitbox; // för att kunna lägga in solidens x/y i hitboxen
		this.x = x;
		this.y = y;
		this.listofSolids = listofSolids;

		
		
		
	}
	
	public void init (){
		
		try {
			img = new Image("res/solid.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.height = img.getHeight();
		this.width = img.getWidth();
		
	}
	
	public void render (){
		img.draw(x - cameraX, y - cameraY);
	}
	
	
	
	public void update(){
		Input inp = gc.getInput();
		 // Kollar om man kolliderar med soliden i sidled då ska den inte röra sig i förhållande till spelaren
		if (!rightIsSideHit) {
			if (inp.isKeyDown(inp.KEY_RIGHT)){
			
				cameraX += 5;
			}
		}
		if (!leftIsSideHit){
			if (inp.isKeyDown(inp.KEY_LEFT)){

				cameraX -= 5;
			}
		}
		for(int i = 0; i < listofSolids.size(); i++){
		hitbox.setSolidX(x - cameraX);
		hitbox.setSolidY(y - cameraY);
		hitbox.setSolidWidth(img.getWidth());
		hitbox.setSolidHeight(img.getHeight());
		}
		}
	}
	
	
	
	



