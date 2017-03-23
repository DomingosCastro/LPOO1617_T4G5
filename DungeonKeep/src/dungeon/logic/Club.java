package dungeon.logic;

//import java.util.ArrayList;
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
					l= randNum.nextInt(board.getBoard().length-2)+1; 
					c  = randNum.nextInt(board.getBoard().length-2)+1;
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