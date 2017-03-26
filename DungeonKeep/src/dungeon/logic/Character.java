package dungeon.logic;

@SuppressWarnings("serial")
public abstract class Character  implements java.io.Serializable  
{  
	protected char letter;   
	protected Direction dir; 
	protected int l;
	protected int c;
	
	/**
	 * Character constructor
	 * @param letter - letter that represents the character in the board
	 * @param l - line position in the board
	 * @param c - column position in the board
	 */
	public Character(char letter,  int l, int c){
		this.letter=letter;
		this.l=l;
		this.c=c;		
	}
	
	/**
	 * Set Character line position
	 * @param newL - new line position
	 */
	public void setLine(int newL)
	{
		l = newL;
	}
	
	/**
	 * Set Character column position
	 * @param newC - new column position
	 */
	public void setColumn(int newC)
	{
		c = newC;
	}
	
	/**
	 * Return character's line position 
	 * @return line position
	 */
	public int getLine()
	{
		return l;
	}
	 /**
	  * Return character's column position 
	  * @return column position
	  */
	public int getColumn()
	{
		return c;
	}
	
	/**
	 * Set the letter that represents the character in the board
	 * @param letter - letter of the character
	 */
	public void setCharacterLetter(char letter)
	{
		this.letter = letter;
	}	
	
	/**
	 * Returns the character's letter
	 * @return character's letter
	 */
	public char getLetter(){
		return letter;
	}
	
	/**
	 * Return the moving direction of the character
	 * @return Direction enumeration element (RIGHT, LEFT, UP, DOWN)
	 */
	public Direction getDir()
	{
		return dir;
	}
	
	/**
	 * Sets the moving direction of the character
	 * @param newDir - Direction enumeration element (RIGHT, LEFT, UP, DOWN)
	 */
	public void setDir(Direction newDir)
	{
		dir = newDir;
	}
	
	/**
	 * Changes the coordinates of the characters according the moving direction, if the movement is valid
	 * @param board - current board to check if the character makes a valid move (doesn't collide with a wall,
	 *  for instance)
	 * @return return 'true' if the movement is valid, 'false' if it is invalid (in which case, the coordinates 
	 * of the character remain unchanged)
	 */
	public boolean moveCharacter(Board board){
		
		char[][] boardTiles=board.getBoard();
		boolean valid=true;
		
		switch (dir)
		{
		case UP:			
			if (boardTiles[l-1][c]!='X' && boardTiles[l-1][c]!='I'){
				l--;
			}
			else valid=false;
			break;

		case DOWN:			
			if (boardTiles[l+1][c]!='X' && boardTiles[l+1][c]!='I'){
				l++;
			}
			else valid=false;
			break;

		case LEFT:
			if (boardTiles[l][c-1]!='X' && boardTiles[l][c-1]!='I'){
				c--;
			}
			else valid=false;
			break;

		case RIGHT:
			if (boardTiles[l][c+1]!='X' && boardTiles[l][c+1]!='I'){
				c++;
			}
			else valid=false;
			break;
		}
		return valid;
	} 
}
