package dungeon.cli;

import dungeon.logic.Board;
import dungeon.logic.Tile;

public class ShowBoard {
	
	
	// imprime o tabuleiro!
		public void printBoard(Board board ){
			
		int	lines=board.getLines();
		int columns=board.getColumns();
		Tile[][] boardTiles=board.getBoardTiles();
		
			for (int i = 0; i < lines; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					System.out.print(boardTiles[i][j].getTileLetter()) ;
				}
				
				System.out.println();
			}
		}
}
