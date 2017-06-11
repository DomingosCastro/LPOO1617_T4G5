package dungeon.logic;

@SuppressWarnings("serial")
public class Rookie extends Guard implements java.io.Serializable {
	
	/**
	 * Roockie constructor - initializes the letter of the character, and its coordinates
	 * @param letter - letter that represents the character in the board
	 * @param l - line position 
	 * @param c - column position
	 */
	public Rookie(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Moves the Rookie and establishes his lethal tiles (tiles that kill the hero)
	 */
	public void moveGuard(Board board){
    	
    	//Move guarda  
    	movingDirection();
    	moveCharacter(board);    	  	
    	
    	setLethalTiles(awakeState, l, c);
	}
}
