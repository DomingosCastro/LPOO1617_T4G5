package dungeon.logic;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Hero extends Character
{
	char keyboard; // caracter lido a partir do teclado
	Scanner input = new Scanner(System.in);
	boolean armedState = false;

	/**
	 * Hero constructor - initializes the letter of the character, and its coordinates
	 * @param letter - letter that represents the character in the board
	 * @param l - line position 
	 * @param c - column position
	 */
	public Hero(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the armed state of the hero
	 * @return - true=hero armed, false=hero unarmed
	 */
	public boolean getArmedState(){
		return armedState;
	}

	/**
	 * Sets the armed state of the hero - true=hero armed, false=hero unarmed
	 */
	public void setArmedState(){
		armedState=true;
	}

	/**
	 * Moves the hero according the the user input. Checks if the hero catches the club or key/lever
	 * @param playerInput - char associated with the user input
	 * @param board - Board object
	 * @param level - current level
	 */
	public void moveHero(char playerInput, Board board, int level){

		// Estabelece direção do movimento
		movingDirection(playerInput);

		// muda coordenadas do hero:
		moveCharacter(board);

		// Verifica se chegou ao Club
		if(board.getBoard()[l][c]=='c'){
			letter='A';
			armedState=true;
		}		

		// Desbloqueio das portas
		if (catchKey(board, level))
			board.unlockDoors(level);
	}
	
	/**
	 * Checks if the hero catches the key. If he gets the key, his letter is updated
	 * @param board - Board object
	 * @param level - current level
	 * @return true=has the key, false=doesn't have the key
	 */
	public boolean catchKey(Board board, int level){
		boolean withKey=false;
		if (l==board.getLeverLine() && c==board.getLeverColumn()){	
			{withKey=true;
			if (level==2)
				letter='K';}
		}	
		return withKey;
	}

	/**
	 * Associates the user input with a specific moving direction for the hero
	 * @param playerInput 
	 */
	public void movingDirection(char playerInput){
		
		switch (playerInput){
		case 'w':
			dir = Direction.UP;
			break;
		case 's':
			dir = Direction.DOWN;
			break;
		case 'a':
			dir = Direction.LEFT;
			break;
		case 'd':
			dir = Direction.RIGHT;
			break;
		}
	}
}

