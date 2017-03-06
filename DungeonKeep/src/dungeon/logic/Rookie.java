package dungeon.logic;

public class Rookie extends Guard {
	
	public Rookie(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	public void moveGuard(Board board){
    	
    	//Move guarda  
    	movingDirection();
    	moveCharacter(board);    	  	
    	
    	setLethalTiles(awakeState, l, c);
	}
}
