package dungeon.logic;
import java.util.ArrayList;
import java.util.Scanner;
import dungeon.cli.Input;

public class Hero extends Character 
{
	char keyboard; // caracter lido a partir do teclado
	Scanner input = new Scanner(System.in);
	boolean armedState = false;

	public Hero(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	public boolean getArmedState(){
		return armedState;
	}

	public void setArmedState(){
		armedState=true;
	}

	public void moveHero(char playerInput, Board board, int level){

		// Estabelece direção do movimento
		movingDirection(playerInput);

		// muda coordenadas do hero:
		moveCharacter(board);

		// Verifica se chegou ao Club
		if(board.getBoard()[l][c]=='c'){
			letter='A';
			armedState=true;
		}		

		// Desbloqueio das portas
		if (catchKey(board, level))
			board.unlockDoors(level);
	}
	
	public boolean catchKey(Board board, int level){
		boolean withKey=false;
		if (l==board.getLeverLine() && c==board.getLeverColumn()){	
			{withKey=true;
			if (level==2)
				letter='K';}
		}	
		return withKey;
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
}

