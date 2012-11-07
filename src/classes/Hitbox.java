package classes;

public class Hitbox {
	private float playerX;
	private float playerY;
	private float solidX;
	private float solidY;
	private float solidWidth;
	private float solidHeight;
	public Hitbox (){
		
	}
	
	
	public boolean groundHitTest(Solid solid, float playerHeight, float playerWidth){
	
			//System.out.println("Player x" + playerX +"          Player Y" + playerY);
			/*
			 * If-satsen nedan kollar om spelaren kolliderar med ett objekt på de satta parametrarna
			 * om den inte kolliderar så returneras kollisons testet falsk
			 */
			
			if(playerX + playerWidth > solidX && playerY == solidY && playerX
					<= solidX + solidWidth && playerY <= solidY + solidHeight - 10){
				return true;
			}
			else {
			
				return false;
			}
		
	}
	
	public boolean sideHitTest(float playerHeight, float playerWidth){
		/*
		 * If-satsen nedan kollar om spelaren kolliderar med ett objekt i sidled
		 * 
		 */
		if(playerX + playerWidth == solidX && playerY <= solidY + solidHeight - 10 
				|| playerX - 10 == solidX + solidWidth && playerY <= solidY + solidHeight - 10){
			return true;
		}
		return false;
	}
	/* Metoder för att sätta spelaren och soliders x respektive y värde så 
	 * dessa kan användas i kollisions tester. 
	 * De uppdateras i varje objekts update metod.
	*/
	public void setPlayerX (float playerX){
		this.playerX = playerX;
	}
	public void setPlayerY (float playerY){
		this.playerY = playerY;
	}
	public void setSolidX (float solidX){
		this.solidX = solidX;
	}
	public void setSolidY (float solidY){
		this.solidY = solidY;
	}
	public void setSolidWidth (float width){
		this.solidWidth = width;
	}
	public void setSolidHeight(float height){
		this.solidHeight = height;
	}
	
	
	
	
	
}



