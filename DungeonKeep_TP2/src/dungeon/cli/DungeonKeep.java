package dungeon.cli;


import java.util.Scanner;
import dungeon.logic.Board;
import dungeon.logic.Character;
import dungeon.logic.Direction;
import dungeon.logic.Hero;
import dungeon.logic.Guard;
import dungeon.logic.Tile;

public class DungeonKeep 
{
	public static void main(String[] args)
	{
				
		Scanner input = new Scanner(System.in);
		
		
		
        char playerInput; 
		Board board = new Board(10, 10);		
		board.fillBoard() ; // preenche com as letras corretas
		System.out.println();
		ShowBoard showBoard = new ShowBoard();

		Hero hero = new Hero('H');
		hero.setLine(1);
		hero.setColumn(1);
		
		Guard guard = new Guard('G', 1, 8 );
		
		board.getBoardTiles()[hero.getLine()][hero.getColumn()].setTileLetter('H');
		board.getBoardTiles()[guard.getLine()][guard.getColumn()].setTileLetter('G');
		
		// Ciclo de jogo
		do {
			showBoard.printBoard(board);
			
			// Leitura do teclado
			do{
			 playerInput = input.next().charAt(0);			
			} while(playerInput!='w' && playerInput!='a' && playerInput!='s' && playerInput!='d');

			// Movimento HERO
			hero.movingDirection(playerInput);
			hero.moveCharacter(board.getBoardTiles());
			
			guard.setLethalTiles(board.getBoardTiles(), "null");
			// Movimento Guarda
			guard.movingDirection();
			guard.moveCharacter(board.getBoardTiles());
			guard.setLethalTiles(board.getBoardTiles(), "lethal");
			
			
			
			if (board.getBoardTiles()[hero.getLine()][hero.getColumn()].getTileType()=="lever"){
				board.unlockDoors();
			}	

			
			
		} while(board.getBoardTiles()[hero.getLine()][hero.getColumn()].getTileColumn()>0);

      //  board.showBoard();
        System.out.println("HEYEY");
	}
}


