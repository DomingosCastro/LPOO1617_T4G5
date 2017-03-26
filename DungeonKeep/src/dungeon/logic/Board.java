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

	/**
	 * Board Constructor - initializes Board class attributes;
	 * 
	 * The Board Class is constituted by two 2D arrays of chars:
	 * - fixedBoard: stores the empty board (only walls, doors and lever)
	 * - changingBoard: stores the current board and characters. The changingBoard is updated every round, 
	 * by putting the characters' letters in the fixedBoard
	 *
	 * @param board - 2D array of chars with the empty board (only walls, doors and lever)
	 */
	
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
	

/**
 * Change the specified tile of the board
 * @param l - line of the tile
 * @param c - column of the tile
 * @param letter - letter to insert in the board
 */
	public void setChangingBoardLetter(int l, int c, char letter){
		changingBoard[l][c]=letter;
	}

	/**
	 * Returns the number of lines of the board
	 * @return - number of lines
	 */
	
	public int getLines(){
		return lines;
	}
/**
 *  Returns the number of columns of the board
	 * @return - number of columns
 */
	public int getColumns(){
		return columns;
	}

	/**
	 * Returns the 2D array that represents the board tiles
	 * @return 2D array with the board tiles
	 */
	public char[][] getBoard(){
		return changingBoard;
	}

	/**
	 * Returns the line where the lever stands
	 * @return tile line
	 */
	public int getLeverLine()
	{
		return leverL;
	}

	/**
	 * Returns the column where the lever stands
	 * @return tile column
	 */
	public int getLeverColumn()
	{
		return leverC;
	}

	/**
	 * Puts all the characters in the empty changing board
	 * @param characters - ArrayList with all the Characters
	 */
	public void setCharactersInBoard(ArrayList<Character>characters){

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
	
	/**
	 * Initializes the empty changingBoard by copying the fixedBoard
	 */
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
	
	/**
	 * Unlocks the exit doors, by replacing the tile letter 'X' by 'S'
	 * @param level - the current level number
	 */
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


