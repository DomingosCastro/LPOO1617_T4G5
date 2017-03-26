package dungeon.logic;

import java.util.Random;

@SuppressWarnings("serial")
public class Club implements java.io.Serializable {

	boolean initialized=false;
	char letter;
	Random randNum = new Random();
	int l, c;
	public Club(char letter) {
		this.letter = letter;

		// TODO Auto-generated constructor stub
	}
   public int getLine(){
	   return l;
   }
   
   public int getColumn(){
	   return c;
   }
   
	public void setHeroClub(Board board, int level) {

		if (level==2){
			if (initialized==false){
				do{					
					l= randNum.nextInt(board.getLines()-2)+1; 
					c  = randNum.nextInt(board.getColumns()-2)+1;
				} while (board.getBoard()[l][c]!=' ');

				initialized=true;}		


			//			if (board.getBoard()[l][c]!='A')
			//				if (board.getBoard()[l][c]!='$')
			//				board.setChangingBoardLetter(l, c, letter);
			//			else letter=' ';

			if (board.getBoard()[l][c]=='A')
				letter=' ';
			if(board.getBoard()[l][c]==' ' || board.getBoard()[l][c]=='c')
				board.setChangingBoardLetter(l, c, letter);
		}
	}	
}