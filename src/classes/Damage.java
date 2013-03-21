package classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Damage {
	
	private int health = 100; // vid debug öka till 100
	private Graphics g;
	private GameContainer gc;
	private Image img;
	private float x;
	private float y;
	private float cameraX;
	private SolidMaker solidmaker;
	private int map;
	private Hitbox hitbox;
	
	public Damage (GameContainer gc, Graphics g, SolidMaker solidmaker, Hitbox hitbox){
		this.gc = gc;
		this.map = solidmaker.map;
		this.g = g;
		this.hitbox = hitbox;
		this.solidmaker = solidmaker;//Behövs för att kunna rita starta om när man dör
		try {
			img = new Image("res/HeroDamage.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render();
		
		
	}
	
	public void render (){
		if (health == 3){
		img.draw(10, gc.getHeight() - 50);
		img.draw(50, gc.getHeight() - 50);
		img.draw(90, gc.getHeight() - 50);
		} else if (health == 2){
			img.draw(10, gc.getHeight() - 50);
			img.draw(50, gc.getHeight() - 50);
		} else if (health == 1){
			img.draw(10, gc.getHeight() - 50);
		}
	}
	
	public void update(){
		
	}
	
	
	
	public int getHealth(){
		return health;
	}
	
	
	
	public void takeDamage (){
		health--;
		
		if (health == 0){
			onDeath();
		}
		
	}
	
	private void onDeath (){
	//	g.drawString("YOU ARE DEAD", gc.getWidth() / 2, gc.getHeight() / 2);
		solidmaker.setMap(map);
		
	}
	
	
	
}
