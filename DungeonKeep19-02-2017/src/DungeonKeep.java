import java.util.Scanner;

public class DungeonKeep 
{
	public static void main(String[] args)
	{
		//char direction;
		//Scanner input = new Scanner(System.in);
 
		Board board = new Board(10, 10);		
		//board.fillBoard(); // preenche com as letras corretas
		board.showBoard();
		
		Hero hero = new Hero("Claudia");
		hero.pos.setTileLine(1);
		hero.pos.setTileColumn(1);
		/////////////////////////////////////////////////////////////////////////
		// TESTE
		
		/*int l = 1;
		int c = 1;

		board.getBoardTiles()[l][c].setTileLetter('H');
		board.showBoard();

		do {
			direction = input.next().charAt(0);
			board.getBoardTiles()[l][c].setTileLetter(' ');	

			// TESTE
			switch (direction)
			{
			case 'w':
				if(board.getBoardTiles()[l-1][c].getTileLetter() != 'X' && board.getBoardTiles()[l-1][c].getTileLetter() != 'I')
					l--;				
				break;

			case 's':
				if(board.getBoardTiles()[l+1][c].getTileLetter() != 'X' && board.getBoardTiles()[l+1][c].getTileLetter() != 'I')
					l++;
				break;

			case 'a':
				if(board.getBoardTiles()[l][c-1].getTileLetter() != 'X' && board.getBoardTiles()[l][c-1].getTileLetter() != 'I')
					c = c-1;
				System.out.println(c);
				break;

			case 'd':
				if (board.getBoardTiles()[l][c+1].getTileLetter() != 'X' && board.getBoardTiles()[l][c+1].getTileLetter() != 'I')
					c++;				
				break;
			}
		
		////////////////////////////////////////////////////////////////////////////


		// desbloquear as portas --> alavancas
		if (board.getBoardTiles()[l][c].getTileLetter() == 'k')
			board.unlockDoors();
		
		board.getBoardTiles()[l][c].setTileLetter('H');
		board.showBoard();
		
		} while(c != 0);*/
		

	}
}


