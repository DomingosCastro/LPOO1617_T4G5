package dungeon.logic;

import java.util.ArrayList;
import java.util.Random;

public class Club {

	boolean initialized=false;
	char letter;
	Random randNum = new Random();
	int l, c;
	public Club(char letter) {
		this.letter = letter;
		//super(heroClub);
		// TODO Auto-generated constructor stub
	}

	public void setHeroClub(Board board, int level) {

		if (level==2){
			if (initialized==false){
				do{					
					l= randNum.nextInt(board.getBoard().length-2)+1; 
					c  = randNum.nextInt(board.getBoard().length-2)+1;
				} while (board.getBoard()[l][c]!=' ');

				initialized=true;}		


			if (board.getBoard()[l][c]!='A')
				board.setChangingBoardLetter(l, c, letter);
			else letter=' ';
		}
	}	
}