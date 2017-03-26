package dungeon.logic;

import java.util.ArrayList;

import dungeon.logic.Board;
import dungeon.logic.Character;
import dungeon.logic.Club;
import dungeon.logic.Hero;
import dungeon.logic.Ogre;
import dungeon.logic.Guard;
import dungeon.logic.Rookie;
import dungeon.logic.Drunken;
import dungeon.logic.Suspicious;
/**
 * 
 * Class that controls the Game logic
 *
 */
@SuppressWarnings("serial")
public class DungeonKeep implements java.io.Serializable  
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
	static int level=1;
	static ArrayList<int[]> newPositions = new ArrayList<>();	
	static int nLevels = 2;
	static int[] portasSaidaPorNivel = {2, 1};

	
	/**
	 * Sets the guard personality and the number of ogres in the game
	 * @param choseGuard - index of the guard (0 - Rookie, 1 - Drunken, 2 - Suspicious)
	 * @param numberOgres - number of ogres in the keep level
	 */
	public void setEnemys(int choseGuard, int numberOgres){		
		DungeonKeep.choseGuard=choseGuard;
		DungeonKeep. numberOgres= numberOgres;
	}

	/**
	 * Returns the current board
	 * @return - current Board object
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * Stores the edited board and the new characters positions of the level created
	 * @param board2 - 2D array with the edited board
	 * @param newPositions - ArrayList with the new coordinates of the characters
	 */
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

	public char[][] getEditedBoard(){
		return editedBoard;
	}
	
	public ArrayList<int[]> getNewPositions(){
		return newPositions;
	}
	
	/**
	 * Returns 'true' if the board was edited
	 */
	public boolean getEditedState(){
		return edited;
	}

	/**
	 * Return the hero, of the Hero Class
	 * @return - Hero object
	 */
	public Hero getHero(){
		return hero;
	}

	/**
	 * Return the guard, of the Guard Class
	 * @return - Guard object
	 */
	public Guard getGuard(){
		return guards.get(0);
	}

	/**
	 * Returns the hero's club, of the Club Class
	 * @return - Club object
	 */
	public Club getHeroClub(){
		return heroClub;
	}

	/**
	 * Return the ogres ArrayList of Ogre Class
	 * @return - ArrayList of Ogre
	 */
	public ArrayList<Ogre> getOgres(){
		return ogres;
	}

	/**
	 * Return the current level
	 * @return - current level
	 */
	public int getLevel(){
		return level;
	}

	//		public boolean getInitialized(){
	//			return initialized;
	//		}

	/**
	 * Sets the current level
	 * @param level
	 */
	public void setLevel(int level){
		if(level==1|| level==2)
			DungeonKeep.level=level;
	}

	/**
	 * Return the ArrayList of Character that stores all the characters currently in game.
	 * @return
	 */
	public ArrayList<Character> getCharacters(){
		return characters;
	}

	/**
	 * Initializes the board and characters that take part in the current level.
	 */
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

		board.setCharactersInBoard(characters);//, level);
		heroClub.setHeroClub(board, level);

	}

	/**
	 * Sets the new coordinates of the hero and ogres. These coordinates are different from the default ones
	 * because the keep level has been edited by the user
	 */
	private void initializeEditedCharacters() {
		int heroCol=newPositions.get(0)[1];
		int heroLine=newPositions.get(0)[0];

		hero.setLine(heroLine);
		hero.setColumn(heroCol);

		initializeOgres(edited);
	}
	/**
	 * Moves all the characters, checks if the hero is alive, checks if the level was completed
	 * @param playerInput - char that represent the moving direction of the hero, established by the user
	 * @return - return a String that describes the state of the game: "normal", "next level", "winner", "looser"
	 */
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
		board.setCharactersInBoard(characters);//, level);
		heroClub.setHeroClub(board, level);

		if (heroKilled)
			return "loser"; 

		else if(passLevel()){

			level++;

			if(level==3)
				return "winner";
			else return "next level";
		}

		return "normal";
	}

	private boolean passLevel(){
		if (hero.getColumn()==0 || hero.getColumn()==board.getColumns()-1 ||
				hero.getLine()==0 || hero.getLine()==board.getLines()-1)
			return true;
		else return false;
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

	/**
	 * Clears the ArrayList that stores the coordinates of the characters, 
	 * established by the user upon the keep level edition
	 */
	public void clearNewPositions(){
		newPositions.clear();
	}

	/**
	 * Updates the variable "edited" which determines if the keep level has been edited
	 * @param state - equals "true" if the keep level was edited
	 */
	public void setEditedState(boolean state){
		edited = state;
	}

	/**
	 * Returns the 2D array that stores the board for the specified level
	 * @param level 
	 * @return
	 */
	public char[][] getBoard(int level)
	{
		switch(level)
		{
		case 1: return fixedBoard1;
		case 2: return fixedBoard2;
		default: return null;
		}
	}

	/**
	 * Returns the total number of levels
	 * @return
	 */
	public int getTotalNLevels()
	{
		return nLevels;
	}

	/**
	 * Returns the number of exit doors in each level
	 * @return
	 */
	public int[] getNDoorsArray()
	{
		return portasSaidaPorNivel;
	}

}

