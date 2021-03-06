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
	private Image controls;
	private int menuX = 0;
	private int menuY = 0;
	
	
	public Menu(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
		background = new Image("res/background.png");
		controls = new Image("res/Controls.png");
		startgame = new Image("res/startgame.png");
		menuX = (gc.getWidth() /2) - (startgame.getWidth() / 2);
		menuY = (gc.getHeight() /2) - (startgame.getHeight() / 2);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g)
			throws SlickException {
		background.draw(0,0);
		controls.draw(gc.getWidth() - controls.getWidth(), gc.getHeight() - controls.getHeight());
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
		 * Kolla om musen befinner sig i knappen f�r start
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
				 * Om den �r inne i start och musen blir klickad byt till state 2
				 */
				System.out.println("Is down");
				
				state.enterState(2);
			}
		} 

		

	}

	@Override
	public int getID() {
		//Metod f�r att h�mta statens id
		return 0;
	}

}
