package classes;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Music {

	public Music (){
		
	}
	
	public void startMusic (){
		try{
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/Djungelljud_2.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        
	        clip.start();
	        int a = clip.LOOP_CONTINUOUSLY;
	        clip.loop(a);
	    }catch(Exception ex){
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	
}
