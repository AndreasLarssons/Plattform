package classes;

public class Hitbox {
	private float playerX;
	private float playerY;
	private float solidX;
	private float solidY;
	private float solidWidth;
	private float solidHeight;
	private Solid solid;
	public Hitbox (){
		
	}
	
	
	
	
	public boolean groundHitTestX (Solid solid, float playerHeight, float playerWidth){
		this.solid = solid;
		if(playerX + playerWidth > solid.x  && playerX
				<= solid.x + solidWidth - 40 ){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean groundHitTestY (Solid solid, float playerHeight, float playerWidth){
		this.solid = solid;
		if(playerY == solid.y &&  playerY <= solid.y + solidHeight - 10 ){
			return true;
		} else if (playerY == solid.y + 1 &&  playerY <= solid.y + solidHeight - 10){
			return true;
		} else if (playerY == solid.y + 2&&  playerY <= solid.y + solidHeight - 10){
			return true;
		}else if (playerY == solid.y + 3 &&  playerY <= solid.y + solidHeight - 10){
			return true;
		} else if (playerY == solid.y + 4 &&  playerY  <= solid.y + solidHeight - 10){
			return true;
		}  else if (playerY == solid.y - 1 &&  playerY  <= solid.y + solidHeight - 10){
			return true;
		}  else if (playerY == solid.y - 2 &&  playerY  <= solid.y + solidHeight - 10){
			return true;
		}  else if (playerY == solid.y - 3 &&  playerY  <= solid.y + solidHeight - 10){
			return true;
		}  else if (playerY == solid.y - 4 &&  playerY  <= solid.y + solidHeight - 10){
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
	//System.out.println("solidX  " + solidX);
		//System.out.print("playerY  " + playerY + "  ");
		//System.out.println("solidY  " + (solidY + solidHeight - 10));
		if(playerX + playerWidth == solid.x && playerY <= solid.y + solidHeight - 10){
			System.out.println("IS Righthit");

			return true;
		} else {
			return false;
		}
	}
	
	public boolean leftSideHitTest (float playerHeight, float playerWidt) {
		if (playerX == solid.x + solidWidth + 1 && playerY <= solid.y + solidHeight - 10){
			System.out.println("IS lefthit");
			
			return true;
		}else {
			return false;
		}
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



