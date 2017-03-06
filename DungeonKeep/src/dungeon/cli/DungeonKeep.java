package dungeon.cli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import dungeon.logic.Board;
import dungeon.logic.Character;
import dungeon.logic.Club;
import dungeon.logic.Direction;
import dungeon.logic.Hero;
import dungeon.logic.Ogre;
import dungeon.logic.Guard;
import dungeon.logic.Rookie;
import dungeon.logic.Drunken;
import dungeon.logic.Suspicious;


public class DungeonKeep 
{
	public static void main(String[] args)
	{	
		char[][] fixedBoard1 = {{'X','X','X','X','X','X','X','X','X','X'}, 
				{'X',' ',' ',' ','I',' ','X',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X',' ',' ','X'}, 
				{'X',' ','I',' ','I',' ','X',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X',' ',' ','X'}, 
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X','X',' ','X'}, 
				{'X',' ','I',' ','I',' ','X','k',' ','X'}, 
				{'X','X','X','X','X','X','X','X','X','X'}};

		char[][] fixedBoard2 = {{'X','X','X','X','X','X','X','X','X'}, 
				{'I',' ',' ',' ',' ',' ',' ','k','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X','X','X','X','X','X','X','X','X'}};
		
		//for debug purposes
		//int startLevel = 2;
		
		Scanner input = new Scanner(System.in);
		char playerInput; 
		
		ArrayList<Character> characters = new ArrayList<>();

		/*
		Hero hero = new Hero('H', 1, 1);
		Club club = new Club('c');
		*/

		Hero hero = null; 
		boolean heroKilled = false;
		
		/*
		Guard rookie = new Rookie('G', 1, 8 );
		Guard drunken = new Drunken('G', 1, 8 );
		Guard suspicious = new Suspicious('G', 1, 8 );
		*/
		
		/*
		// Armazena os guardas num Array:
		ArrayList<Guard> guards=new ArrayList<>();

		guards.add(rookie);
		guards.add(drunken);		
		guards.add(suspicious);
		
		
		//lista de ogres
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();  
		Ogre ogre = null;
		Ogre ogre2= null;
		Ogre ogre3= null;
		*/
		
		Guard rookie = null;
		Guard drunken = null; 
		Guard suspicious = null; 
		ArrayList<Guard> guards=new ArrayList<>();
		
		Ogre ogre1 = null;
		Ogre ogre2= null;
		Ogre ogre3=null;
		ArrayList<Ogre> ogres = new ArrayList<>();

		Club heroClub = new Club('c') ;	
		Board board = null;
		ShowBoard showBoard = new ShowBoard();
		//Lever lever = null;
		
		// Ciclo dos Níveis do Jogo
		for (int level = 2; level <= 2; level++){

			// Vai determinar qual é o guarda de vigia
			int choseGuard = 0;
						
			// Inicializacao dos Niveis:
			if (level==1){	
				board=new Board(fixedBoard1);
				hero = new Hero('H', 1, 1);
	                
	            rookie = new Rookie('G', 1, 8);
	        	drunken = new Drunken('G', 1, 8 );
	        	suspicious = new Suspicious('G', 1, 8 );

	        	// Armazena os guardas num Array

	        	guards.add(rookie);
	        	guards.add(drunken);		
	        	guards.add(suspicious);
	                
				// Renova o array de characters (para a colocaçao das persongens no board)
				characters.add(hero);
				characters.add(guards.get(0));
			}

			else if (level==2)
			{
				board=new Board(fixedBoard2);
				heroClub.setHeroClub(board, level);
				
				hero = new Hero('H', 7, 2);		

				ogre1=new Ogre('O', 1, 2 );
				ogre2=new Ogre('O', 1, 3);
				ogre3=new Ogre('O', 1, 4);

				// Armazena ogres no arrayList:
				ogres.add(ogre1);
				ogres.add(ogre2);
				ogres.add(ogre3);
				
				// Renova o array de characters (para a colocaçao das persongens no board)
				characters.removeAll(characters);
				characters.add(hero);
				characters.add(ogre1);
				characters.add(ogre2);	
				characters.add(ogre3);
			}
 
			//boolean atLeastOneStunned = false;
			
			
			// Ciclo de jogo
			
			do {
				board.setCharactersInBoard(characters, level);
				heroClub.setHeroClub(board, level);
				
				showBoard.printBoard(board, level);
				
				// Leitura do teclado
				
				do{
					playerInput = input.next().charAt(0);		
				} while(playerInput!='w' && playerInput!='a' && playerInput!='s' && playerInput!='d');

				// Movimento HERO
				hero.moveHero(playerInput, board, level);

				// Movimento dos Inimigos
				if(level==1){                    

					// Escolhe o Guarda
					if (guards.get(choseGuard).changeGuard()){						
						choseGuard=choseGuard+1;
						if (choseGuard==3)
							choseGuard=0;
					}

					// Substitui o guarda anterior no arraylist characters
					characters.set(1, guards.get(choseGuard));
					
					// Depois de escolhido o guard, move-o
					guards.get(choseGuard).moveGuard(board);
					
					// Verifica se mata o heroi
					heroKilled=guards.get(choseGuard).killHero(hero);
				}

				else if (level==2)	{
					
					for (Ogre ogre: ogres){
						// Move ogre:
						ogre.moveOgre(board,  hero);
						
						// verifica se mata o hero:
						heroKilled=ogre.killHero(hero) ;						
						if(heroKilled)
							break;
					}					
				
				}					


			} while(!heroKilled && hero.getColumn()!=0);

			board.setCharactersInBoard(characters, level);
			heroClub.setHeroClub(board, level);
			showBoard.printBoard(board, level); 

			if (heroKilled){		
				System.out.println(" GAME OVER");
				break;
			}
			else if (level==2){
				System.out.println(" WINNER!!");
			}
		}
	}
}

