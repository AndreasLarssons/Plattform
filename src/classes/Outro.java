package classes;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Outro extends BasicGameState {
	private Image fiImg, seImg, thiImg, fourthImg;
	private Timer timer;
	private int count = 0;
	
	public Outro (int state){
		
	}
	
	
	public void countdown (){
		timer = new Timer();
		
		Task task = new Task();
		timer.scheduleAtFixedRate(task, 0, 5 * 1000);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
		
		// TODO Auto-generated method stub
		fiImg = new Image("res/Outro.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g)
			throws SlickException {
		if (count == 0){
			countdown();
		} else if (count == 1){
			fiImg.draw(0,0);
		}else if (count == 2){
			timer.cancel();
			state.enterState(0);
		}
	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int updating)
			throws SlickException {
		gc.getInput();
		// TODO Auto-generated method stub
		if (gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
			timer.cancel();
			state.enterState(0);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	class Task extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			count++;
			System.out.println(count);
		}
		
	}

}
