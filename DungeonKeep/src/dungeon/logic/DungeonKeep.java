package dungeon.logic;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Scanner;

import dungeon.cli.ShowBoard;
import dungeon.logic.Board;
import dungeon.logic.Character;
import dungeon.logic.Club;
//import dungeon.logic.Direction;
import dungeon.logic.Hero;
import dungeon.logic.Ogre;
import dungeon.logic.Guard;
import dungeon.logic.Rookie;
import dungeon.logic.Drunken;
import dungeon.logic.Suspicious;


public class DungeonKeep 
{
	static char[][] fixedBoard1 = {{'X','X','X','X','X','X','X','X','X','X'}, 
			{'X',' ',' ',' ','I',' ','X',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'X',' ','I',' ','I',' ','X',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X','X',' ','X'}, 
			{'X',' ','I',' ','I',' ','X','k',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X','X'}};

	static char[][] fixedBoard2 = {{'X','X','X','X','X','X','X','X','X'}, 
			{'I',' ',' ',' ',' ',' ',' ','k','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X'}};



	static Scanner input = new Scanner(System.in);
	static char playerInput; 

	static ArrayList<Character> characters = new ArrayList<>();



	static Hero hero = null; 
	static boolean heroKilled = false;

	static int choseGuard,numberOgres;

	static boolean initialized = false;
	static Guard rookie = null;
	static Guard drunken = null; 
	static Guard suspicious = null; 
	static ArrayList<Guard> guards=new ArrayList<>();

	static Ogre ogre1 = null;
	static Ogre ogre2= null;
	static Ogre ogre3=null;
	static ArrayList<Ogre> ogres = new ArrayList<>();

	static Club heroClub = new Club('c') ;	
	static Board board = null;
	static ShowBoard showBoard = new ShowBoard();
	static int level=2;

	
	public void setEnemys(int choseGuard, int numberOgres){		
		DungeonKeep.choseGuard=choseGuard;
		DungeonKeep. numberOgres= numberOgres;
	}

	public Board getBoard(){
		return board;
	}
	
	public Hero getHero(){
		return hero;
	}
	
	public Club getHeroClub(){
		return heroClub;
	}
	
	public int getLevel(){
		return level;
	}
	
	public boolean getInitialized(){
		return initialized;
	}
	
	public ArrayList<Character> getCharacters(){
		return characters;
	}
	
	public void initializeLevel(){
		initialized=true;
		characters.removeAll(characters);
		// Inicializacao dos Niveis:
		if (level==1){	
			board = new Board(fixedBoard1);
			hero = new Hero('H', 1, 1);

			rookie = new Rookie('G', 1, 8);
			drunken = new Drunken('G', 1, 8 );
			suspicious = new Suspicious('G', 1, 8 );

			// Armazena os guardas num Array
			if (choseGuard==0)
				guards.add(rookie);
			else if (choseGuard==1)
				guards.add(drunken);	
			else if (choseGuard==2)
				guards.add(suspicious);

			// Renova o array de characters (para a colocaçao das persongens no board)
			characters.removeAll(characters);
			characters.add(hero);
			characters.add(guards.get(0));
		}

		else if (level==2)
		{
			board=new Board(fixedBoard2);
			heroClub.setHeroClub(board, level);

			hero = new Hero('H', 7, 2);		

			// Armazena ogres no arrayList:				
			for(int i=0; i<numberOgres; i++){
				ogres.add(new Ogre('O', 1, 3));
			}

			// Renova o array de characters (para a colocaçao das persongens no board)
			characters.removeAll(characters);
			characters.add(hero);
			for (int i=0; i<ogres.size(); i++)
				characters.add(ogres.get(i));
		}
		////////////////////////////
		board.setCharactersInBoard(characters, level);
		heroClub.setHeroClub(board, level);

		showBoard.printBoard(board, level);
		////////////////////////////
	}

	public String playTurn(char playerInput){//, JTextArea textArea){
		//board.setCharactersInBoard(characters, level);
		heroClub.setHeroClub(board, level);

		// Movimento HERO
		hero.moveHero(playerInput, board, level);

		// Movimento dos Inimigos
		if(level==1){               

			// Substitui o guarda anterior no arraylist characters
			characters.set(1, guards.get(0));

			// Depois de escolhido o guard, move-o
			guards.get(0).moveGuard(board);

			// Verifica se mata o heroi
			heroKilled=guards.get(0).killHero(hero);
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

		board.setCharactersInBoard(characters, level);
		heroClub.setHeroClub(board, level);
		
		showBoard.printBoard(board, level); 


		if (heroKilled)
			return "loser";

		else if(hero.getColumn()==0){
			level++;
			initializeLevel();
			if(level==3)
				return "winner";
		}
		
		return "normal";
	


	}
}

