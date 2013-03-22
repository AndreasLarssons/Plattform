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

public class Intro extends BasicGameState {
	
	private Image fiImg, seImg, thiImg, fourthImg;
	private Timer timer;
	private int count = 0;
	
	public Intro (int state){
		countdown();
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
		fiImg = new Image("res/Intro1.png");
		seImg = new Image("res/Intro2.png");
		thiImg = new Image("res/Intro3.png");
		fourthImg = new Image("res/Intro4.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		if (count == 1){
			fiImg.draw(0,0);
		}else if (count == 2){
			seImg.draw(0,0);
		} else if (count == 3){
			thiImg.draw(0,0);
		} else if (count == 4){
			fourthImg.draw(0,0);
		} else if (count == 5){
			timer.cancel();
			state.enterState(1);
		}
	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int updating)
			throws SlickException {
		gc.getInput();
		// TODO Auto-generated method stub
		if (gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
			timer.cancel();
			state.enterState(1);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
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
