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
	final char[][] fixedBoard1 = {{'X','X','X','X','X','X','X','X','X','X'}, 
			{'X',' ',' ',' ','I',' ','X',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'X',' ','I',' ','I',' ','X',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X','X',' ','X'}, 
			{'X',' ','I',' ','I',' ','X','k',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X','X'}};

	final char[][] fixedBoard2 = {{'X','X','X','X','X','X','X','X','X'}, 
			{'I',' ',' ',' ',' ',' ',' ','k','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X'}};

	static char[][] editedBoard;
	boolean edited=false;

	static Scanner input = new Scanner(System.in);
	static char playerInput; 

	static ArrayList<Character> characters = new ArrayList<>();

	static Hero hero = null; 
	static boolean heroKilled = false;

	static int choseGuard,numberOgres;

	static boolean initialized = false;

	static ArrayList<Guard> guards=new ArrayList<>();

	static ArrayList<Ogre> ogres = new ArrayList<>();

	static Club heroClub = new Club('c') ;; 	
	static Board board = null;
	static ShowBoard showBoard = new ShowBoard();
	static int level=1;
	static ArrayList<int[]> newPositions = new ArrayList<>();


	public void setEnemys(int choseGuard, int numberOgres){		
		DungeonKeep.choseGuard=choseGuard;
		DungeonKeep. numberOgres= numberOgres;
	}

	public Board getBoard(){
		return board;
	}

	public void setEditedBoard(char[][] board2, ArrayList<int[]>newPositions){
		
		int lines=board2.length;
		int columns=board2[0].length;

		editedBoard= new char[lines][columns];

		for(int i=0; i<lines;i++)					
			for(int j=0; j<columns;j++){				
				if(board2[i][j]!='H' && board2[i][j]!='O') 
					editedBoard[i][j]=board2[i][j];
			}

		DungeonKeep.newPositions=newPositions;		

		edited=true;

	}

	public boolean getEditedState(){
		return edited;
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

	public void setLevel(int level){
		if(level==1|| level==2)
			DungeonKeep.level=level;
	}

	public ArrayList<Character> getCharacters(){
		return characters;
	}

	public void initializeLevel(){

		initialized=true;
		characters.clear();

		// Inicializacao dos Niveis:
		if (level==1){	
			board = new Board(fixedBoard1);
			hero = new Hero('H', 1, 1);
			guards.clear();

			// Armazena os guardas num Array
			if (choseGuard==0)
				guards.add(new Rookie('G', 1, 8));

			else if (choseGuard==1)
				guards.add(new Drunken('G', 1, 8 ));	

			else if (choseGuard==2)
				guards.add(new Suspicious('G', 1, 8 ));

			// Renova o array de characters (para a colocaçao das persongens no board)
			//	characters.removeAll(characters);
			characters.add(hero);
			characters.add(guards.get(0));
		}

		else if (level==2)
		{
			hero = new Hero('H', 7, 2);	

			if (edited){
				board= new Board(editedBoard);
				initializeEditedCharacters();

			}
			else board=new Board(fixedBoard2);

			heroClub = new Club('c') ;
			heroClub.setHeroClub(board, level);


			initializeOgres(edited);

			// Renova o array de characters (para a colocaçao das persongens no board)
			characters.removeAll(characters);
			characters.add(hero);
			for (int i=0; i<ogres.size(); i++)
				characters.add(ogres.get(i));
		}

		board.setCharactersInBoard(characters, level);
		heroClub.setHeroClub(board, level);

	}


	private void initializeEditedCharacters() {
		int heroCol=newPositions.get(0)[1];
		int heroLine=newPositions.get(0)[0];

		hero.setLine(heroLine);
		hero.setColumn(heroCol);

		initializeOgres(edited);
	}

	public String playTurn(char playerInput){

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


		if (heroKilled)
			return "loser";

		else if(hero.getColumn()==0){

			level++;

			if(level==3)
				return "winner";
			else return "next level";
		}

		return "normal";

	}

	private void initializeOgres(boolean edited){

		ogres.clear();

		// Armazena ogres no arrayList, depois do tabuleiro ter sido editado
		if (edited){
			int numberOfOgres=newPositions.size()-1;

			for(int i=1; i<=numberOfOgres; i++){

				ogres.add(new Ogre('O', newPositions.get(i)[0], newPositions.get(i)[1]));
			}
		}

		// Armazena ogres no arrayList, sem o tabuleiro ter sido editado	
		else{
			for(int i=0; i<numberOgres; i++){		
				int ogreCol=1;
				if (i<board.getColumns()-2)
					ogreCol=i+1;

				ogres.add(new Ogre('O', 1, ogreCol));
			}
		}
	}

	public void clearNewPositions(){
		newPositions.clear();
	}

}

