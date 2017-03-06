package dungeon.logic;

import java.util.Random;

public class Drunken extends Guard {
	
	Random randNum=new Random();


	public Drunken(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	} 

	public void moveGuard(Board board){
		
		char[][] boardTiles=board.getBoard();
		
    	// Controla o sono do guarda
    	randomSleep();    	
    	
    	// muda coordenadas do guarda caso esteja acordado 
    	if (awakeState){      		
    	movingDirection();         
    	moveCharacter(board);   
    	}
    	setLethalTiles(awakeState, l, c);
	}
	
	
	public void randomSleep(){
		
		// Estado atual: awakeState
		// Novo estado: newAwakeState
		
		boolean newAwakeState=true;
		changeRoute=false; // permite que o changeRoute=true so dure 1 iteraçao
		
		int State=randNum.nextInt(4); // gera numero entre 0 e 3: 0-dormir, 1 a 3-acordado
		// Estabelece se o novo estado é acordado ou a dormir (3/4 do tempo esta acordado)

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
