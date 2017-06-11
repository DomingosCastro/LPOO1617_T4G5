package dungeon.logic;

import java.util.Random;

@SuppressWarnings("serial")
public class Ogre extends Enemy implements java.io.Serializable 
{
	Random randNum = new Random();
	int clubL=l, clubC=c;
	char clubLetter='*';	
	int stunnedRounds=0;	

	/**
	 * Ogre constructor - initializes the letter of the character, and its coordinates
	 * @param letter - letter that represents the character in the board
	 * @param l - line position 
	 * @param c - column position
	 */
	public Ogre(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Sets the coordinates of the ogre's club
	 * @param line - line position
	 * @param column - column position
	 */
	public void setClub(int line, int column){
		clubL=line;
		clubC=column;
	}
	
	/**
	 * Returns the club linw
	 * @return
	 */
	public int getClubLine(){
		return clubL;
	}
	
	/**
	 * Return the club column
	 * @return
	 */
	public int getClubColumn(){
		return clubC;
	}

	/**
	 * Return the club letter
	 * @return
	 */
	public char getClubLetter(){
		return clubLetter;
	}
	
	/**
	 * Moves the ogre and his club. Both movements are randomly set. Checks if the ogre is attacked by the armed hero.
	 * Updates the letters of the ogre and club 
	 * @param board - board object
	 * @param hero - hero object
	 */
	public void moveOgre(Board board, Hero hero){

		boolean valid = true;
		
		// MOVER OGRE - repete ate mover ogre (evita que fique a bater contra a parede)
		do {			
			if (awakeState){
				movingDirection();			
				valid=moveCharacter(board); // se fica a esbarrar na parede, repete leitura	
			}
			
			if (c==0){
				c++;
				valid=false;
			}
						
		} while(valid==false );
		
		// ATAQUE DO HERO - Depois de mover o ogre verifica se o Hero o pode atacar;		
		heroAttack(hero);
				
		// MOVER BASTAO
		do {
			// inicia posiçao do club no ogre
			clubL=l;
			clubC=c;
			
			// atribui direçao aleatoria 
			movingDirection(); 
			
			// atribui coordenadas a direçao atribuida anteriormente, se coincidir com parede, retorna false
			valid=moveClub(board); 
		} while(valid==false);

		
		// Atualização do Board:
		setLethalTiles(awakeState, l, c);	
		setClubLethalTiles(clubL, clubC, lethalTiles);  
		
		manageOgreLetter(board);
		
	}
	
	/**
	 * Establishes a ramdom direction for the ogre	
	 */
	public void movingDirection() {
		
		int direction=randNum.nextInt(4);
		
		if (direction==0)
			dir=Direction.UP;
		else if(direction==1)
			dir=Direction.DOWN;
		else if (direction==2)
			dir=Direction.LEFT;
		else if (direction==3)
			dir=Direction.RIGHT;
	}
		
	/**
	 * Establishes a random position for the club. Returns "true" if the position is valid; "false" if it is invalid
	 * @param board - board object
	 * @return - boolean that states if the attributed position is valid
	 */
	public boolean moveClub(Board board){
		char[][] boardTiles=board.getBoard();
		boolean valid=true;

		switch (dir)
		{
		case UP:			
			if (boardTiles[l-1][c]!='X' && boardTiles[l-1][c]!='I' && boardTiles[l-1][c]!='S'){
				clubL--;	
			}
			else valid=false;
			break;

		case DOWN:			
			if (boardTiles[l+1][c]!='X' && boardTiles[l+1][c]!='I'&& boardTiles[l+1][c]!='S'){
				clubL++;				
			}
			else valid=false;
			break;

		case LEFT:
			if (boardTiles[l][c-1]!='X' && boardTiles[l][c-1]!='I'&& boardTiles[l][c-1]!='S'){
				clubC--;				
			}
			else valid=false;
			break;

		case RIGHT:
			if (boardTiles[l][c+1]!='X' && boardTiles[l][c+1]!='I'&& boardTiles[l][c+1]!='S'){
				clubC++;
			}
			else valid=false;
			break;

		}
		return valid;
	}
	
	/**
	 * Updates the Lethal Tiles (tiles that kill the hero) of the ogre, by introducing the tiles around the club
	 * @param l - club line
	 * @param c - club column
	 * @param ogreLethalTiles - ogre lethal tiles (before the introduction of the club lethal tiles)
	 */
	public void setClubLethalTiles(int l, int c, int[][] ogreLethalTiles){
		int[][] clubLethalTiles=new int[][] {{l,c}, {l+1,c}, {l, c+1}, {l-1, c}, {l, c-1}};
		lethalTiles=concat(clubLethalTiles, ogreLethalTiles );
	}

	private int[][] concat(int[][] a, int[][] b) {
		int[][] result = new int[a.length + b.length][];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}

	public void manageOgreLetter(Board board){

		char[][] boardTiles = board.getBoard(); 

		if(awakeState==false)
			letter='8';

		else if (boardTiles[l][c]=='k' ||  boardTiles[l][c]=='$' || boardTiles[l][c]=='c' || boardTiles[l][c]=='#'){
			letter='$';
			clubLetter='*';
		}
		

		else if (boardTiles[clubL][clubC]=='k' || boardTiles[clubL][clubC]=='$' || boardTiles[clubL][clubC]=='c' || boardTiles[clubL][clubC]=='#'){
			
			clubLetter='#';
			letter='O';
		}
		

		else {letter='O';
		clubLetter='*';
		}

	}


	public void heroAttack(Hero hero){
		int heroL=hero.getLine();
		int heroC=hero.getColumn();
		if(hero.getArmedState()){
			if ((heroL==l && Math.abs(heroC-c)==1) || (heroC==c && Math.abs(heroL-l)==1)){
				awakeState=false;
			}
		}

		if (awakeState==false){
			stunnedRounds++;
		}

		if (stunnedRounds==4){
			stunnedRounds=0;
			awakeState=true;
		}

	}
}
