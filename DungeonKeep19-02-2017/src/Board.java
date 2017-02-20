import java.io.*; // evitar usar
import java.util.Scanner;

public class Board 
{
	private int lines; 
	private int columns;	
	private Tile[][] boardTiles; // matriz de objetos do tipo Tile

	private String[] temporaryBoard = {"XXXXXXXXXX", "X   I X  X", "XXX XXX  X", "X I I X  X" , "XXX XXX  X", "I        X", "I        X", "XXX XXXX X", "X I I Xk X", "XXXXXXXXXX"};

	// construtor do tabuleiro
	// cria um objeto em cada celula do tabuleiro do tipo Tile, nao imprime nada
	// cria tabuleiro com pintas
	public Board(int nr_lines, int nr_columns)
	{
		this.lines = nr_lines;
		this.columns = nr_columns;

		boardTiles = new Tile[nr_lines][nr_columns];
 
		for (int i = 0; i < nr_lines; i++)
		{
			for(int j = 0; j < nr_columns; j++) 
			{
				boardTiles[i][j] = new Tile(i, j, '.', false);
			}
		}
	}

	// preenche o tabuleiro com o caracter correto em cada celula, nao imprime nada
	public void fillBoard()
	{
		char tileLetter;
		//String tileState; ainda nao foi usada

		for (int i = 0; i < lines; i++)
		{
			for(int j = 0; j < columns; j++)
			{

				tileLetter = temporaryBoard[i].charAt(j); // retorna o char do indice especificado (inicia em 0)
				boardTiles[i][j] = new Tile(i, j, tileLetter, false); 
			}
		}
	}

	// imprime o tabuleiro!
	public void showBoard(){
		for (int i = 0; i < lines; i++)
		{
			for(int j = 0; j < columns; j++)
			{

				System.out.print(boardTiles[i][j].getTileLetter()) ;
			}
			System.out.println();
		}
	}

	public void unlockDoors()
	{
		for (int i = 0; i < lines; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				if (boardTiles[i][j].getTileLetter() == 'I')
				{
					boardTiles[i][j].setTileLetter(' ');
				}		
			}
		}
	}
	 
	public Tile[][] getBoardTiles()
	{		
		return boardTiles;		
	}
}

