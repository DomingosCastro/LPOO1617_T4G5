package dungeon.logic;

import java.util.Random;

public class Ogre extends Character 
{
	//boolean clockwise = false;  // <---------- VER ISTO DEPOIS
	
		private int leverL, leverC;
		private int nMaxPossibleMoves = 4;		// nº maximo de movimentos possiveis
		private Random random = new Random();
		//private boolean awake = true;
		
		public Ogre(char letter,  int l, int c, int leverL, int leverC)
		{
			this.letter=letter;
			this.l=l;
			this.c=c;		
			this.leverL = leverL;
			this.leverC = leverC;
		}
		
		/*public boolean isAwake(){
			return awake;
		}

		public void setAwake(boolean state){
			awake=state;
		}*/
		
		/*public void setClockwise(boolean clockwise){
			this.clockwise=clockwise;
		}*/

		public void movingDirection()
		{
			int dirChoice = random.nextInt(nMaxPossibleMoves) + 1;		// [1, nMaxPossibleMoves]
			
			if (dirChoice == 1)
				dir = Direction.LEFT;
			else if (dirChoice == 2)
				dir = Direction.RIGHT;
			else if (dirChoice == 3)
				dir = Direction.UP;
			else if (dirChoice == 4)
				dir = Direction.DOWN;
		}
		
		public void setLethalTiles(Tile[][] boardTiles, String type)
		{
			if (l-1 == leverL && c == leverC)
			{
				if (type == "null")		// para nao colocar o tile da chave com state "null"
					boardTiles[l-1][c].setTileState("lever");
				else
					boardTiles[l-1][c].setTileState(type);
				
				boardTiles[l+1][c].setTileState(type);
				boardTiles[l][c-1].setTileState(type);
				boardTiles[l][c+1].setTileState(type);
			}
			else if (l+1 == leverL && c == leverC)
			{
				if (type == "null")		// para nao colocar o tile da chave com state "null"
					boardTiles[l+1][c].setTileState("lever");
				else
					boardTiles[l-1][c].setTileState(type);
				
				boardTiles[l-1][c].setTileState(type);
				boardTiles[l][c-1].setTileState(type);
				boardTiles[l][c+1].setTileState(type);
			} 
			else if (l == leverL && c-1 == leverC)
			{
				if (type == "null")		// para nao colocar o tile da chave com state "null"
					boardTiles[l][c-1].setTileState("lever");
				else
					boardTiles[l-1][c].setTileState(type);
				
				boardTiles[l-1][c].setTileState(type);
				boardTiles[l+1][c].setTileState(type);
				boardTiles[l][c+1].setTileState(type);
			}
			else if (l == leverL && c+1 == leverC)
			{
				if (type == "null")		// para nao colocar o tile da chave com state "null"
					boardTiles[l][c+1].setTileState("lever");
				else
					boardTiles[l-1][c].setTileState(type);
				
				boardTiles[l-1][c].setTileState(type);
				boardTiles[l+1][c].setTileState(type);
				boardTiles[l][c-1].setTileState(type);
			}
			else
			{
				boardTiles[l-1][c].setTileState(type);
				boardTiles[l+1][c].setTileState(type);
				boardTiles[l][c-1].setTileState(type);
				boardTiles[l][c+1].setTileState(type);
			}
		}	
		
		public void moveCharacter(Tile[][] boardTiles, int leverL, int leverC){
			
			switch (dir)
			{
				case UP:			
					if (boardTiles[l-1][c].getTileLetter()!='X' && boardTiles[l-1][c].getTileLetter()!='I')
					{
					    boardTiles[l][c].setTileLetter(' ');
						l--;
						if (l == leverL && c == leverC)		// se estiver na posicao da lever
							boardTiles[l][c].setTileLetter(letter);
						else
							boardTiles[l][c].setTileLetter('O');
					}
					
					break;
		
				case DOWN:			
					if (boardTiles[l+1][c].getTileLetter()!='X' && boardTiles[l+1][c].getTileLetter()!='I')
					{
						boardTiles[l][c].setTileLetter(' ');
						l++;
						if (l == leverL && c == leverC)		// se estiver na posicao da lever
							boardTiles[l][c].setTileLetter(letter);
						else
							boardTiles[l][c].setTileLetter('O');
					}
					break;
		
				case LEFT:
					if (boardTiles[l][c-1].getTileLetter()!='X' && boardTiles[l][c-1].getTileLetter()!='I')
					{
						boardTiles[l][c].setTileLetter(' ');
						c--;
						if (l == leverL && c == leverC)		// se estiver na posicao da lever
							boardTiles[l][c].setTileLetter(letter);
						else
							boardTiles[l][c].setTileLetter('O');
					}
					break;
		
				case RIGHT:
					if (boardTiles[l][c+1].getTileLetter()!='X' && boardTiles[l][c+1].getTileLetter()!='I')
					{
						boardTiles[l][c].setTileLetter( ' ');
						c++;
						if (l == leverL && c == leverC)		// se estiver na posicao da lever
							boardTiles[l][c].setTileLetter(letter);
						else
							boardTiles[l][c].setTileLetter('O');
					}
					break;
			}
		}	
}
