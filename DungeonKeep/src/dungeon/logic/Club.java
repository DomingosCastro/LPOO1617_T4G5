package dungeon.logic;

import java.util.Random;

@SuppressWarnings("serial")
public class Club implements java.io.Serializable {

	boolean initialized=false;
	char letter;
	Random randNum = new Random();
	int l, c;

	/**
	 * Initializes the Club Class, that represent the Hero Club, of the second level
	 * @param letter - letter that represents the club in the board
	 */
	public Club(char letter) {
		this.letter = letter;
	}

	/**
	 * Returns the club letter
	 * @return
	 */
	
	public char getSwordLetter()
	{
		return letter;
	}
	
	/**
	 * Return the line position of the hero's club in the board
	 * @return - line position
	 */
	public int getLine(){
		return l;
	}

	/**
	 * Return the column position of the hero's club in the board
	 * @return - column position
	 */
	public int getColumn(){
		return c;
	}

	/**
	 * Places the hero club in the board of the second level
	 * @param board - board object
	 * @param level - current level
	 */
	public void setHeroClub(Board board, int level) {

		if (level==2){
			if (initialized==false){
				do{					
					l= randNum.nextInt(board.getLines()-2)+1; 
					c  = randNum.nextInt(board.getColumns()-2)+1;
				} while (board.getBoard()[l][c]!=' ');

				initialized=true;}		


			if (board.getBoard()[l][c]=='A')
				letter=' ';
			if(board.getBoard()[l][c]==' ' || board.getBoard()[l][c]=='c')
				board.setChangingBoardLetter(l, c, letter);
		}
	}	
}