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

		Hero hero = new Hero('H', 1, 1);
		Guard guard = new Guard('G', 1, 8 );


		// Ciclo dos Níveis do Jogo:
		for (int level=1; level<=2; level++){

			Board board = new Board(level);						
			ShowBoard showBoard = new ShowBoard();


			// Colocação das personagens no tabuleiro para um dado nível:
			if (level==1){			
				board.getBoardTiles()[hero.getLine()][hero.getColumn()].setTileLetter('H');
				board.getBoardTiles()[guard.getLine()][guard.getColumn()].setTileLetter('G');}

			else if (level==2){
				hero.setColumn(2);			
				hero.setLine(7);
				board.getBoardTiles()[hero.getLine()][hero.getColumn()].setTileLetter('H');

				// OGRE
			}


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



				// Movimento dos Inimigos
				if(level==1){
					// Movimento Guarda
					guard.setLethalTiles(board.getBoardTiles(), "null");			
					guard.movingDirection();
					guard.moveCharacter(board.getBoardTiles());
					guard.setLethalTiles(board.getBoardTiles(), "lethal");}

				else if (level==2){ 
					// Movimento do Ogre

				}

				// Desbloqueio das portas
				if (board.getBoardTiles()[hero.getLine()][hero.getColumn()].getTileState()=="lever"){
					board.unlockDoors(level);
				}	



			} while(hero.isALive(board)&& hero.getColumn()!=0);

			showBoard.printBoard(board); 
			
			if (hero.isALive(board))
				System.out.println("LEVEL 2");
			else {
				System.out.println("GAME OVER");
				break;
				}
			

		}

	}

}



