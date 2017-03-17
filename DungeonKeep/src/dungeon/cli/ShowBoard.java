package dungeon.cli;

import dungeon.logic.Board;

public class ShowBoard 
{
	    // imprime o tabuleiro!
		public void printBoard(Board board, int level){
			
		int	lines=board.getLines();
		int columns=board.getColumns();
		char[][] boardTiles=board.getBoard();
		//System.out.println(" LEVEL " + level) ;
		
			for (int i = 0; i < lines; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					System.out.print(' ');
					System.out.print(boardTiles[i][j]) ;					
				}
				
				System.out.println();
			}
		}
}
