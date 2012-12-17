package classes;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame implements Runnable {

	public static final String gameName = "Plattform"; // Spelets namn
	public static final int menu = 0; //Olika states v�rde
	public static final int game = 1;

	public Game(String gameName) throws SlickException {
		super(gameName);
		//L�gg till states (Denna k�rs vid start av programmet)
		this.addState(new Menu(menu));
		this.addState(new Play(game));
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppGameContainer appgc;
		//Inst�llningar f�r f�nstret t.ex. uppl�sning/framerate
		try {
			appgc = new AppGameContainer(new Game(gameName));
			appgc.setDisplayMode(1280, 720, false);
			appgc.setTargetFrameRate(60);
			appgc.setVSync(true);
			appgc.start();
			

		} catch (SlickException e) {

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		//L�gga upp states i minnet f�r senare anv�ndning 
		this.getState(menu).init(gc, this);
	//	this.getState(game).init(gc, this);
		this.enterState(menu); // Best�mma start staten

	}

}