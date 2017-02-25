package dungeon.cli;

import java.util.Scanner;
import dungeon.logic.Board;
import dungeon.logic.Character;
import dungeon.logic.Direction;
import dungeon.logic.Hero;
import dungeon.logic.Ogre;
import dungeon.logic.Guard;
import dungeon.logic.Tile;


public class DungeonKeep 
{
	public static void main(String[] args)
	{				
		Scanner input = new Scanner(System.in);		
		char playerInput; 
		boolean leverCaught = false;
		
		Hero hero = new Hero('H', 1, 1);
		Guard guard = new Guard('G', 1, 8 );
		Ogre ogre = null;
		Board board = null;
		ShowBoard showBoard = null;

		// Ciclo dos Níveis do Jogo:
		for (int level=1; level<=2; level++){

			board = new Board(level);						
			showBoard = new ShowBoard();

			// Colocação das personagens no tabuleiro para um dado nível:
			if (level==1)
			{			
				hero.setCharacterLetter('H');			// reset da letra do hero
				board.getBoardTiles()[hero.getLine()][hero.getColumn()].setTileLetter('H');
				board.getBoardTiles()[guard.getLine()][guard.getColumn()].setTileLetter('G');
			}

			else if (level==2)
			{
				ogre = new Ogre('O', 1, 6, board.getLeverLine(), board.getLeverColumn());
				hero.setCharacterLetter('H');			// reset da letra do hero
				hero.setColumn(2);			
				hero.setLine(7);
				board.getBoardTiles()[hero.getLine()][hero.getColumn()].setTileLetter('H');
				// OGRE
				board.getBoardTiles()[ogre.getLine()][ogre.getColumn()].setTileLetter('0');
			}

			// Ciclo de jogo
			do 
			{
				
				showBoard.printBoard(board);

				// Leitura do teclado
				do
				{
					playerInput = input.next().charAt(0);			
				} while(playerInput!='w' && playerInput!='a' && playerInput!='s' && playerInput!='d');

				// Movimento HERO
				hero.movingDirection(playerInput);
				hero.moveCharacter(board.getBoardTiles());


				// Movimento dos Inimigos
				if(level==1)
				{
					// Movimento Guarda
					guard.setLethalTiles(board.getBoardTiles(), "null"); // faz o reset dos lethal tiles devido posicao anterior do guarda				
					guard.movingDirection();
					guard.moveCharacter(board.getBoardTiles());
					guard.setLethalTiles(board.getBoardTiles(), "lethal");
					
					// Desbloqueio das portas
					if (board.getBoardTiles()[hero.getLine()][hero.getColumn()].getTileState() == "lever")
					{
						board.unlockDoors(level);
					}	
				}

				else if (level==2)
				{ 
					// Movimento do Ogre
					ogre.setLethalTiles(board.getBoardTiles(), "null");	// faz o reset dos lethal tiles devido posicao anterior do guarda		
					ogre.movingDirection();
					// tem de se alterar aqui a letra do ogre de modo a que esta seja atualizada no metodo moveCharacter (para a atualizar no momento em que o o ogre acaba de se deslocar para a posicao da lever)
					// logo desenvolvi uma funcao que prevê a quadricula para onde o ogre se irá mover, de modo a, que caso corresponda à da lever
					// alterar a letra do ogre para $
					ogre.moveCharacter(board.getBoardTiles(), board.getLeverLine(), board.getLeverColumn());
					ogre.setLethalTiles(board.getBoardTiles(), "lethal");
					
					if (board.getBoardTiles()[ogre.getLine()][ogre.getColumn()].getTileState() == "lever")
					{
						//ogre.setCharacterLetter('$');
						board.getBoardTiles()[ogre.getLine()][ogre.getColumn()].setTileLetter('$');		// serve para alterar a letra no momento em que o ogre se move para a posicao da lever
					}
					else
					{
						//ogre.setCharacterLetter('O');
						if (hero.getLine() == board.getLeverLine() && hero.getColumn() == board.getLeverColumn())
							board.getBoardTiles()[board.getLeverLine()][board.getLeverColumn()].setTileLetter('K');
						else if (leverCaught)
						{
							board.getBoardTiles()[board.getLeverLine()][board.getLeverColumn()].setTileLetter(' ');
							board.getBoardTiles()[board.getLeverLine()][board.getLeverColumn()].setTileState("null");		// ja nao existe chave, logo fica a null
						}
						else if (!leverCaught)
						{
							board.getBoardTiles()[board.getLeverLine()][board.getLeverColumn()].setTileLetter('k');
						}	
					}				

				// Desbloqueio das portas
				if (board.getBoardTiles()[hero.getLine()][hero.getColumn()].getTileState()=="lever")
				{
					board.unlockDoors(level);
					hero.setCharacterLetter('K');
					leverCaught = true;
					//board.getBoardTiles()[hero.getLine()][hero.getColumn()].setTileLetter('K');
				}
				
			}
		} while(hero.isALive(board)&& hero.getColumn()!=0);

			showBoard.printBoard(board);  
			
			if (hero.isALive(board))
				System.out.println("LEVEL 2");
			else 
			{
				System.out.println("GAME OVER");
				break;
			}
			

		}

	}

}



