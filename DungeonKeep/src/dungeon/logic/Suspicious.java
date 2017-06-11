package dungeon.logic;

@SuppressWarnings("serial")
public class Suspicious extends Guard {
	
	/**
	 * Suspicious constructor - initializes the letter of the character, and its coordinates
	 * @param letter - letter that represents the character in the board
	 * @param l - line position 
	 * @param c - column position
	 */
	public Suspicious(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Moves the Suspicious, and randomly makes him switch direction for a while (the probability of taking 
	 * the normal route is higher than the probability of taking the inverse route) 
	 */
	public void moveGuard(Board board){		
			
		// Faz com que mude a rota durante um bocado, e depois volta ao normal
		if (normalRoute)
		randomRouteChange(4); // se esta a fazer a rota normal, tem pouca probabilidade (25%) de inverter
		else randomRouteChange(2); // se esta a fazer a rota normal tem 50% probabilidade de voltar ao normal
				
    	movingDirection();
    	moveCharacter(board);    	  	
    	
  	setLethalTiles(awakeState, l, c);

	}
}