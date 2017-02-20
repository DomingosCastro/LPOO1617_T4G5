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
	protected char letter;   // se fosse private as classes derivadas nao poderiam aceder
	protected Direction dir; // = Direction.RIGHT; // fiz desta forma porque o 1º movimento do Heroi é para a direita (nao sei se está correto!)
	protected int l;
	protected int c;
    
	//protected boolean awake = true;
	//protected boolean walking = true;


	
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
	///////////////////////////////////////////////////////////////////
	
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

	
	//public abstract void movingDirection();
	
	public void moveCharacter(Tile[][] boardTiles){
			
		switch (dir)
		{
		case UP:			
			if (boardTiles[l-1][c].getTileLetter()!='X' && boardTiles[l-1][c].getTileLetter()!='I'){
			    boardTiles[l][c].setTileLetter(' ');
				l--;
				boardTiles[l][c].setTileLetter(letter);
			}
			
			break;

		case DOWN:			
			if (boardTiles[l+1][c].getTileLetter()!='X' && boardTiles[l+1][c].getTileLetter()!='I'){
				boardTiles[l][c].setTileLetter(' ');
				l++;
				boardTiles[l][c].setTileLetter(letter);
			}
			break;

		case LEFT:
			if (boardTiles[l][c-1].getTileLetter()!='X' && boardTiles[l][c-1].getTileLetter()!='I'){
				boardTiles[l][c].setTileLetter(' ');
				c--;
				boardTiles[l][c].setTileLetter(letter);
			}
			break;

		case RIGHT:
			if (boardTiles[l][c+1].getTileLetter()!='X' && boardTiles[l][c+1].getTileLetter()!='I'){
				boardTiles[l][c].setTileLetter( ' ');
				c++;
				boardTiles[l][c].setTileLetter(letter);
			}
			break;
		}

	}
	

}
