package dungeon.logic;
/*
 * Representa uma personagem  
 * 
 * Caracteristicas de cada personagem: nome, posicao (l, c), direcao
 * 
 * Existem sinalizadores (flags) que indicam se a personagem esta a dormir ou a movimentar-se
 *    
 * Personagens: Heroi, Guarda, Ogre 
 */     

public abstract class Character   
{  
	protected char letter;   
	protected Direction dir; 
	protected int l;
	protected int c;
	
	public Character(char letter,  int l, int c){
		this.letter=letter;
		this.l=l;
		this.c=c;		
	}
	
	// LINE COLUMN
	public void setLine(int newL)
	{
		l = newL;
	}
	
	public void setColumn(int newC)
	{
		c = newC;
	}
	
	public int getLine()
	{
		return l;
	}
	
	public int getColumn()
	{
		return c;
	}
	
	public void setCharacterLetter(char letter)
	{
		this.letter = letter;
	}	
	
	public char getLetter(){
		return letter;
	}
	
	public Direction getDir()
	{
		return dir;
	}
	
	public void setDir(Direction newDir)
	{
		dir = newDir;
	}
	
	public boolean moveCharacter(Board board){
		
		char[][] boardTiles=board.getBoard();
		boolean valid=true;
		
		switch (dir)
		{
		case UP:			
			if (boardTiles[l-1][c]!='X' && boardTiles[l-1][c]!='I'){
				l--;
			}
			else valid=false;
			break;

		case DOWN:			
			if (boardTiles[l+1][c]!='X' && boardTiles[l+1][c]!='I'){
				l++;
			}
			else valid=false;
			break;

		case LEFT:
			if (boardTiles[l][c-1]!='X' && boardTiles[l][c-1]!='I'){
				c--;
			}
			else valid=false;
			break;

		case RIGHT:
			if (boardTiles[l][c+1]!='X' && boardTiles[l][c+1]!='I'){
				c++;
			}
			else valid=false;
			break;
		}
		return valid;
	} 
}
