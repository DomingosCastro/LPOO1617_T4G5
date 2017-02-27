package dungeon.logic;
import java.io.*; // evitar usar!!
import java.util.ArrayList;
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
	
	public ArrayList<Tile> getBoardLethalTiles()
	{
		ArrayList<Tile> lethalTiles = new ArrayList<Tile>();
	
		/*for(int i = 0; i < boardTiles.length ; i ++)
		{
			for(int j = 0; j < boardTiles[i].length; j++)
			{
				boardTiles[i][j].getTileLetter()
			}
		}*/
		
		for(Tile[] line : boardTiles)
			for(Tile t : line)
				if(t.getTileState() == "lethal" || t.getTileState() == "lever" )
				{
					//lethalTiles[lethalTiles.length] = t;
					lethalTiles.add(t);
				}
		
		return lethalTiles;
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


// Obter a coordenada da porta a abrir
// É necessário ter acesso às coordenadas das portas a abrir no ficheiro DungeonKeep.java de modo a poder detetar a proximidade do Heroi a elas
// Isto é apenas para o nivel 2, é necessário fazer uma geral de modo a dar a lista de portas a abrir de cada nivel

	public Tile getDoorTile(int level)
	{
		if (level == 2)
			return boardTiles[1][0];
		return null;
	}

}
