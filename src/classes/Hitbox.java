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
			 * If-satsen nedan kollar om spelaren kolliderar med ett objekt p� de satta parametrarna
			 * om den inte kolliderar s� returneras kollisons testet falsk
			 */
		//	System.out.println("Player y  " + playerY + "Solid y  " + solidY);
		
			if(playerX + playerWidth > solidX && playerY == solidY && playerX
					<= solidX + solidWidth && playerY <= solidY + solidHeight - 10 || playerY == solidY + 2 || playerY == solidY - 2){
				System.out.println("IS hit");
				return true;
			}
			else {
			
				return false;
			}
		
	}
	
	public boolean rightSideHitTest(float playerHeight, float playerWidth){
		/*
		 * If-satsen nedan kollar om spelaren kolliderar med ett objekt i sidled
		 * 
		 */
		if(playerX + playerWidth == solidX && playerY <= solidY + solidHeight - 10 
				){
			System.out.println("IS Righthit");

			return true;
		} else {
			return false;
		}
	}
	
	public boolean leftSideHitTest (float playerHeight, float playerWidt) {
		if (playerX == solidX + solidWidth && playerY <= solidY + solidHeight - 10){
			System.out.println("IS lefthit");
			
			return true;
		}else {
			return false;
		}
	}
	/* Metoder f�r att s�tta spelaren och soliders x respektive y v�rde s� 
	 * dessa kan anv�ndas i kollisions tester. 
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



