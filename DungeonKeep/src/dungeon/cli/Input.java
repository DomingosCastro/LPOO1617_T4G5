package dungeon.cli;
import java.util.Scanner;

public class Input {
	
	Scanner input = new Scanner(System.in);
	public char read(){
	char letter= input.next().charAt(0); 
	return letter; 
	}
	

	
	
}
