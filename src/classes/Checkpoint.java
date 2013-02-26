package classes;

public class Checkpoint {
	
	private int[] checkpoint;

	public Checkpoint(){
		checkpoint = new int [10];
		setCheckpoint();
	}
	
	
	private void setCheckpoint(){
		checkpoint[0] = 100;
		checkpoint[1] = 300;
		checkpoint[2] = 500;
		checkpoint[3] = 800;
	}
	
	public float getCheckpoint (float x){
		float checkpointX = 100;
		
		if (x > checkpoint[0] && x < checkpoint[1]){
			checkpointX = checkpoint[0];
		}else if (x > checkpoint[1] && x < checkpoint[2] ){
			checkpointX = checkpoint[1];
		}
		
		return checkpointX;
	}
	
}
