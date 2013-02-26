package classes;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class EnemyNoStop extends Enemy {
	
	
	
	private int randDirection;
	public  EnemyNoStop(GameContainer gc, StateBasedGame state, Graphics g,
			ArrayList<Solid> solid, float x, float y) {
		super(gc, state, g, solid, x, y);
		Random rand = new Random ();
		int num =	rand.nextInt(10);
		if (num > 5){
			randDirection = 1;
		} else if (num <= 5 ){
			randDirection = -1;
		}
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void moving (){
		for (int i = 0; i < solids.size(); i++){
			
			
				if (leftSideHitTest(x, y, enemy.getWidth(), enemy.getHeight(), solids.get(i) )){
					System.out.println("HEj");
					randDirection = -1;
					
				} else if (rightSideHitTest(x, y, enemy.getWidth(), enemy.getHeight(), solids.get(i))){
					System.out.println("HEj");
					randDirection = 1;
					
				}
		}
		this.x -= velocity * randDirection;	//Sätt rörelsen beroende på om den

	}
	
	@Override 
	protected void falling(){
		for (int i = 0; i < solids.size(); i++){
			
			if (groundHitTestY(x, y, solids.get(i), enemy.getHeight(), enemy.getWidth()) && 
					groundHitTestX(x, y, solids.get(i), enemy.getHeight(), enemy.getWidth())){
				this.y -= gravity;
				
				
			}
		}
		moving();
		this.y += gravity;
	}

}
