package dungeon.logic;

import java.util.Random;

@SuppressWarnings("serial")
public class Drunken extends Guard implements java.io.Serializable {
	
	Random randNum=new Random();

/**
 * Drunken constructor - initializes the letter of the character, and its coordinates
 * @param letter - letter that represents the character in the board
 * @param l - line position 
 * @param c - column position
 */
	public Drunken(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	} 

	/**
	 * Moves the Drunken character in the board. Sets if the Drunken falls asleep or wakes up
	 * @param board - Board object
	 */
	public void moveGuard(Board board){
				
    	// Controla o sono do guarda
    	randomSleep();    	
    	
    	// muda coordenadas do guarda caso esteja acordado 
    	if (awakeState){      		
    	movingDirection();         
    	moveCharacter(board);   
    	}
    	setLethalTiles(awakeState, l, c);
	}
	
	/**
	 * Makes the Drunken fall asleep and wake up, randomly. When he falls asleep, his letter is updated
	 * Sets, randomly, the moving direction every time it wakes up
	 */
	public void randomSleep(){
		
		// Estado atual: awakeState
		// Novo estado: newAwakeState
		
		boolean newAwakeState=true;
		changeRoute=false; // permite que o changeRoute=true so dure 1 iteraçao
		
		int State=randNum.nextInt(3); // gera numero entre 0 e 2: 0-dormir, 1 a 2-acordado
		// Estabelece se o novo estado é acordado ou a dormir (2/3 do tempo esta acordado)

		// Quando adormece
		if (State==0){
			newAwakeState=false;
			letter='g';}	

		// Acorda: pode mudar sentido de rota aleatoriamente: mudar changeRoute=true!
		else if (awakeState==false && newAwakeState==true){
			letter='G';			
			randomRouteChange(2);
		}
		awakeState=newAwakeState;
	}
}
