package dungeon.logic;
import java.util.Scanner;
import dungeon.cli.Input;

public class Hero extends Character 
{

	char keyboard; // caracter lido a partir do teclado

	private boolean hasLever = false;
	Scanner input = new Scanner(System.in);
	
	// construtor
	public Hero(char name)
	{
		this.letter = name;
	}


	public void movingDirection(char playerInput){
		Input input = new Input();
		
		
//		do{
////			keyboard = input.next().charAt(0);
//			keyboard = input.read();
//		} while(keyboard!='w' && keyboard!='a' && keyboard!='s' && keyboard!='d');

		
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

