package dungeon.logic;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Ogre extends Enemy 
{
	Random randNum = new Random();
	int clubL=l+1, clubC=c;
	char clubLetter='*';	
	int stunnedRounds=0;	

	public Ogre(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	public int getClubLine(){
		return clubL;
	}
	
	public int getClubColumn(){
		return clubC;
	}

	public char getClubLetter(){
		return clubLetter;
	}
	
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
		
//		board.setChangingBoardLetter(l, c, letter); 
	}
	
		
	protected void movingDirection() {
		
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
		
	protected boolean moveClub(Board board){
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
	
	public void setClubLethalTiles(int l, int c, int[][] ogreLethalTiles){
		int[][] clubLethalTiles=new int[][] {{l,c}, {l+1,c}, {l, c+1}, {l-1, c}, {l, c-1}};
		lethalTiles=concat(clubLethalTiles, ogreLethalTiles );
	}

	public int[][] concat(int[][] a, int[][] b) {
		int[][] result = new int[a.length + b.length][];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}

	public void manageOgreLetter(Board board){

		char[][] boardTiles = board.getBoard(); 

		if(awakeState==false)
			letter='8';

		else if (boardTiles[l][c]=='k' || boardTiles[l][c]=='c' || boardTiles[l][c]=='$'){
			letter='$';
			clubLetter='*';
		}

		else if (boardTiles[clubL][clubC]=='k' || boardTiles[clubL][clubC]=='c' || boardTiles[clubL][clubC]=='$'){
			clubLetter='$';	
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
