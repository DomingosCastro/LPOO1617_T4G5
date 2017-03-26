package dungeon.logic;
import java.util.Random;

public class Suspicious extends Guard implements java.io.Serializable  {
	
	public Suspicious(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	public void moveGuard(Board board){
		
		
    	//Move guarda  
		int change=randNum.nextInt(2);	
		
		// FAz com que mude a rota durante um bocado, e depois volta ao normal
		if (normalRoute)
		randomRouteChange(4); // se esta a fazer a rota normal, tem pouca probabilidade (25%) de inverter
		else randomRouteChange(2); // se esta a fazer a rota normal tem 50% probabilidade de voltar ao normal
		
		
    	movingDirection();
    	moveCharacter(board);    	  	
    	

  	setLethalTiles(awakeState, l, c);

	}
}