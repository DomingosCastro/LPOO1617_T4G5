package dungeon.logic;
import java.io.*; // evitar usar
import java.util.Scanner;

public class Board 
{
	private int lines; 
	private int columns;	
	private Tile[][] boardTiles; // matriz de objetos do tipo Tile
	private int leverC;	// lever coluna
	private int leverL; // lever linha

	public String[] temporaryBoard1 = {"XXXXXXXXXX", 
			                           "X   I X  X", 
			                           "XXX XXX  X", 
			                           "X I I X  X" , 
			                           "XXX XXX  X", 
			                           "I        X", 
			                           "I        X", 
			                           "XXX XXXX X", 
			                           "X I I Xk X", 
			                           "XXXXXXXXXX"};
	
	private String[] temporaryBoard2 = {"XXXXXXXXX", 
			                            "I      kX", 
			                            "X       X", 
			                            "X       X", 
			                            "X       X", 
			                            "X       X", 
			                            "X       X", 
			                            "X       X", 
			                            "XXXXXXXXX"};


// preenche o tabuleiro com o caracter correto em cada celula, nao imprime nada
	public Board(int level)
	{
		String[] temporaryBoard={};
		char tileLetter;
		
		// Seleçao do tabuleiro do nivel
        if (level==1){
        	temporaryBoard=temporaryBoard1;}
		else if (level==2){
			temporaryBoard=temporaryBoard2;}
				        
        this.lines = temporaryBoard.length;
		this.columns = temporaryBoard[1].length();
		boardTiles = new Tile[lines][columns]; // array de tiles
		
		// inicialização de todos os tiles (X, I, k, empty cell)
		for (int i = 0; i < lines; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				tileLetter = temporaryBoard[i].charAt(j);
				if (tileLetter == 'k')
				{
					boardTiles[i][j] = new Tile(i, j, tileLetter, "lever"); 
					leverL = i;
					leverC = j;
				}
				else 
					boardTiles[i][j] = new Tile(i, j, tileLetter, "null"); 
			}
		}
	}

	public void unlockDoors(int level)
	{
		if (level==1)
		{
			boardTiles[5][0].setTileLetter('S');
			boardTiles[6][0].setTileLetter('S');
		}
		
		else if (level==2)
		{
			boardTiles[1][0].setTileLetter('S');
		}
	}
	 
	public Tile[][] getBoardTiles()
	{		
		return boardTiles;		
	}
	
	public int getLines(){
		return lines;
	}
	
	public int getColumns(){
		return columns;
	}
	
	public int getLeverLine()
	{
		return leverL;
	}
	
	public int getLeverColumn()
	{
		return leverC;
	}
}

