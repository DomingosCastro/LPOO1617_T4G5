package dungeon.logic;
import java.util.Scanner;
import dungeon.cli.Input;

public class Hero extends Character 
{

	char keyboard; // caracter lido a partir do teclado

	Scanner input = new Scanner(System.in);
	
	// construtor
	public Hero(char name, int l, int c)
	{
		this.letter = name;
		this.l=l;
		this.c=c;
	}


	public void movingDirection(char playerInput){
		Input input = new Input();
				
		switch (playerInput){
		case 'w':
			dir = Direction.UP;
			break;
		case 's':
			dir = Direction.DOWN;
			break;
		case 'a':
			dir = Direction.LEFT;
			break;
		case 'd':
			dir = Direction.RIGHT;
			break;
		}
	}
	
	public boolean isALive(Board board){
			  if (board.getBoardTiles()[l][c].getTileState()=="lethal")
				  return false;
			  else return true;	   
			 
	}
}

