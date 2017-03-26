package dungeon.logic;

import java.util.ArrayList;


@SuppressWarnings("serial")
public class Board implements java.io.Serializable 
{
	private int lines; 
	private int columns;	
	
	private int leverC;	// lever coluna
	private int leverL; // lever linha

	private ArrayList<Integer> exit = new ArrayList<Integer>();

	private char[][] fixedBoard;
	private char[][] changingBoard;

	public Board(char[][] board){
		
		lines=board.length;
		columns=board[0].length;
		fixedBoard= new char[lines][columns];

		for (int i=0; i<lines; i++){
			for (int j=0; j<columns; j++){
				fixedBoard[i][j]=board[i][j];
			}
		}

		changingBoard= new char[lines][columns];

		// Inicializa changingBord:
		for(int i=0; i<lines;i++)
		{			
			if(fixedBoard[i][0]=='I')
			   exit.add(i);			
			

			for(int j=0; j<columns;j++)
			{	
				changingBoard[i][j]=fixedBoard[i][j];

				if (changingBoard[i][j]=='k')
				{leverL=i;
				leverC=j;}
			}
		}
	}
	
	public void setFixedBoardLetter(int l, int c, char letter, int level){
		fixedBoard[l][c]=letter;	
	}

	public void setChangingBoardLetter(int l, int c, char letter){
		changingBoard[l][c]=letter;
	}

	public int getLines(){
		return lines;
	}

	public int getColumns(){
		return columns;
	}

	public char[][] getBoard(){
		return changingBoard;
	}

	public int getLeverLine()
	{
		return leverL;
	}

	public int getLeverColumn()
	{
		return leverC;
	}

	public void setCharactersInBoard(ArrayList<Character>characters, int level){

		// Inicializa tabuleiro vazio
		initializeChangingBoard();


		int l, c;
		for (int i=0; i<characters.size(); i++){

			Character character = characters.get(i);

			l=character.getLine();
			c=character.getColumn();
			changingBoard[l][c]=characters.get(i).getLetter();
		}
	}
	
	public void initializeChangingBoard(){
		// Inicializa tabuleiro vazio:
		for(int i=0; i<lines;i++)
			for(int j=0; j<columns;j++)
			{	
				changingBoard[i][j]=fixedBoard[i][j];

				if (changingBoard[i][j]=='k')
				{leverL=i;
				leverC=j;}
			}

	}
	
	public void unlockDoors(int level)
	{
		for(int i: exit)
			fixedBoard[i][0]='S';

		if (level==2)		
			for (int i=0; i<lines; i++)
				for (int j=0; j<columns; j++){
			fixedBoard[leverL][leverC]=' ';
			if (fixedBoard[i][j]=='I')
				fixedBoard[i][j]='S';
				}
	}


}


