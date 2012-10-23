package classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState {

	
	private Image background;
	private Image startgame;
	private int menuX = 300;
	private int menuY = 300;
	
	
	public Menu(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
		startgame = new Image("res/startgame.png");

	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g)
			throws SlickException {
	
		startgame.draw(menuX,menuY);

	} 			

	@Override
	public void update(GameContainer gc, StateBasedGame state, int update)
			throws SlickException {

		
		
		boolean isInsideStart = false;
		boolean isInsideExit = false;
		
		
		Input inp = gc.getInput();
		int mouseX = inp.getMouseX();
		int mouseY = inp.getMouseY();
		
		/*
		 * Kolla om musen befinner sig i knappen för start
		 */

		if (mouseX > menuX && mouseY > menuY && mouseX
				<= menuX + startgame.getWidth() && mouseY <= menuY + startgame.getHeight()  ){
			startgame.setRotation(12);
			isInsideStart = true;
		} else {
			startgame.setRotation(0);
		}
		
		if (isInsideStart){
			
			if (inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				/*
				 * Om den är inne i start och musen blir klickad byt till state 2
				 */
				System.out.println("Is down");
				
				state.enterState(1);
			}
		} 

		

	}

	@Override
	public int getID() {
		//Metod för att hämta statens id
		return 0;
	}

}
