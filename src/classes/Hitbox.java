package classes;

public class Hitbox {
	private float playerX;
	private float playerY;
	private float solidWidth;
	private float solidHeight;
	private Solid solid;
	private Hitbox type;
	private float y;
	public Hitbox (){
		
	}
	
	
	
	
	public boolean groundHitTestX (float x,float y, Solid solid, float playerHeight, float playerWidth){
		/*
		 * Kolla om spelarens x värde är inom den medskickade solidens x och x+width
		 */
		
		this.solid = solid;
		if(x + playerWidth > solid.x  && x
				<= solid.x + solid.width + (playerWidth - 62)){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean groundHitTestY (float x, float y,Solid solid, float playerHeight, float playerWidth){
		/*
		 * Måste kolla så du kolliderar i y led med en solids y värde, om man
		 * gör det returnera true, måste kolla spelarens y+1, y-1 osv pågrund av 
		 * ojämna värden på fallet.
		 * Om man bara kollar ett värde finns det chans att spelaren har y 501 och soliden y 500
		 * vilket leder till en miss
		 * 
		 */
		this.solid = solid;
		this.y = y;
		
		if(this.y == this.solid.y &&  this.y <= this.solid.y + solidHeight - 10 ){
			return true;
		} else if (this.y ==this.solid.y + 1 &&  this.y <= this.solid.y + solid.height - 10){
			return true;
		} else if (this.y == this.solid.y + 2&&  this.y <= this.solid.y + solid.height - 10){
			return true;
		}else if (this.y == this.solid.y + 3 &&  this.y <= this.solid.y + solid.height - 10){
			return true;
		} else if (this.y == this.solid.y + 4 &&  this.y  <= this.solid.y + solid.height - 10){
			return true;
		}  else if (this.y == this.solid.y - 1 &&  this.y  <= this.solid.y + solid.height - 10){
			return true;
		}  else if (this.y == this.solid.y - 2 &&  this.y  <= this.solid.y + solid.height - 10){
			return true;
		}  else if (this.y == this.solid.y - 3 &&  this.y  <= this.solid.y + solid.height - 10){
			return true;
		}  else if (this.y == this.solid.y - 4 &&  this.y  <= this.solid.y + solid.height - 10){
			return true;
		} 
		else {
			
			return false;
		}
		
		
	}
	
	
	public boolean headHitTest (float x, float y,Solid solid, float playerHeight, float playerWidth){
		/*
		 * Måste kolla så du kolliderar i y led med en solids y värde, om man
		 * gör det returnera true, måste kolla spelarens y+1, y-1 osv pågrund av 
		 * ojämna värden på fallet.
		 * Om man bara kollar ett värde finns det chans att spelaren har y 501 och soliden y 500
		 * vilket leder till en miss
		 * 
		 */
		
		
		
		if(y + playerHeight == solid.y ){
			return true;
		} else if (y + playerHeight ==solid.y + 1 ){
			return true;
		} else if (y + playerHeight == solid.y + 2){
			return true;
		}else if (y + playerHeight == solid.y + 3  ){
			return true;
		} else if (y + playerHeight == solid.y + 4){
			return true;
		}  else if (y + playerHeight == solid.y - 1  ){
			return true;
		}  else if (y + playerHeight == solid.y - 2 ){
			return true;
		}  else if (y + playerHeight == solid.y - 3 ){
			return true;
		}  else if (y + playerHeight == solid.y - 4  ){
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
		if(playerX + playerWidth == solid.x && playerY <= solid.y + solid.height - 10){
			System.out.println("IS Righthit");

			return true;
		} else {
			return false;
		}
	}
	
	public boolean leftSideHitTest (float playerHeight, float playerWidt) {
		if (playerX == solid.x + solidWidth + 1 && playerY <= solid.y + solid.height - 10){
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
	//	this.solidX = solidX;
	}
	public void setSolidY (float solidY){
	//	this.solidY = solidY;
	}
	public void setSolidWidth (float width){
		this.solidWidth = width;
	}
	public void setSolidHeight(float height){
		this.solidHeight = height;
	}
	
	
	
	
	
}



