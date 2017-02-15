import java.io.*;
import java.util.Scanner;

public class Board {

	private int lines; 
	private int columns;	
	private Tiles[][] boardTiles; // Matriz de objetos da classe Tiles 

	// depois podemos por o mapa num ficheiro (DungeonMap.txt) e ler a partir de lá, para preencher o Board.
	// assim para o segundo nível, bastaria correr o mesmo codigo, só teria de ler um outro ficheiro, com o desenho do segundo nivel
	// Para já, vai um temporaryBoard: 
	private String[] temporaryBoard={"XXXXXXXXXX", "X   I X  X", "XXX XXX  X", "X I I X  X" , "XXX XXX  X", "I        X", "I        X", "XXX XXXX X", "X I I Xk X", "XXXXXXXXXX"};


	private Scanner fileInput; // Vai dar jeito depois pa ler do ficheiro, para já nao faz nada!

	// Construtor do Board
	public Board(int nr_lines, int nr_columns){
		lines=nr_lines;
		columns=nr_columns;

		boardTiles = new Tiles[nr_lines][nr_columns];

		for (int i=0; i<nr_lines; i++){
			for(int j=0; j<nr_columns; j++){
				boardTiles[i][j] = new Tiles(i, j, "locked", '.'); 
			}
		}
	}


	// Preenche tabuleiro a partir do temporaryBoard (que depois pode ser substituido pelo ficheiro DungeonMap.txt)
	public void fillBoard(){
		char tileLetter;
		String tileState;

		for (int i=0; i<10; i++){
			for(int j=0; j<10; j++){

				tileLetter=temporaryBoard[i].charAt(j);

				if (tileLetter=='X' || tileLetter=='I')
					tileState="locked";
				else 
					tileState="free";

				boardTiles[i][j] = new Tiles(i, j, tileState, tileLetter); // TEMPORARIO - substituir por leitura do ficheiro
			}
		}
	}

	// Imprime o tabuleiro:
	public void showBoard(){
		for (int i=0; i<lines; i++){
			for(int j=0; j<columns; j++){
									
					System.out.print(boardTiles[i][j].getTileLetter()) ;
			}
			System.out.println();
		}

	}

	public char getBoardTilesLetter(int c, int l)
	{
		return boardTiles[c][l].getTileLetter();	
	}
	
	public String getBoardTilesState(int c, int l)
	{
		return boardTiles[c][l].getTileState();
	}
	
	/*public void MoveCharacter()
	{
		
		
	}*/
	
	// Daqui para baixo vai dar jeito para por a ler o mapa a partir do ficheiro, mas não vale
	// a pena preocuparmo nos com isso neste momento:
	/*
	public void openMap(){
		try{
			fileInput=new Scanner (new File("DungeonMap.txt"));
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
	}

	public void readMap(){
		// Usar as funções do readfile
	}
*/



}

