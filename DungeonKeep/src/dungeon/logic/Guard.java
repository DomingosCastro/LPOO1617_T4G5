package dungeon.logic;

import java.util.Random;
import java.util.ArrayList;
import java.util.Vector;

public class Guard extends Enemy  implements java.io.Serializable {
	
	public Guard(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}	

	boolean normalRoute = true; // Sentido de deslocação (normal ou inverso)
	boolean changeRoute = false; // Passa a true sempre que muda de sentido. Depois passa logo a falso!

	boolean reachSetpoint = false;

	Random randNum = new Random();
		
	public boolean getAwakeState(){
		return awakeState;
	}

	public void setAwake(boolean state){
		awakeState=state;
	}
	public void setRoute(boolean normalRoute){
		this.normalRoute=normalRoute;
	}

	public void moveGuard(Board board){};
	
	protected void movingDirection(){

		// Vertices:
		if (l==1 && c==7){
			if (normalRoute==true)
				dir = Direction.DOWN;
			else dir = Direction.RIGHT;			

		}		
		else if (l==5 && c==7){
			if (normalRoute==true)
				dir = Direction.LEFT;
			else dir = Direction.UP;			

		}
		else if (l==5 && c==1){
			if (normalRoute==true)
				dir = Direction.DOWN;
			else dir = Direction.RIGHT;

		}
		else if (l==6 && c==1){
			if (normalRoute==true)
				dir = Direction.RIGHT;
			else dir = Direction.UP;

		}
		else if (l==6 && c==8){
			if (normalRoute==true)
				dir = Direction.UP;
			else dir = Direction.LEFT;			

		}
		else if (l==1 && c==8){
			if (normalRoute==true)
				dir = Direction.LEFT;
			else dir = Direction.DOWN;			

		}		

		// Arestas: mudar de direçao entre vertices
		else if (changeRoute==true){
			if (dir==Direction.LEFT)
				dir=Direction.RIGHT;
			else if(dir==Direction.RIGHT)
				dir=Direction.LEFT;
			else if(dir==Direction.UP)
				dir=Direction.DOWN;
			else if(dir==Direction.DOWN)
				dir=Direction.UP;
		}
	}
		
	protected void randomRouteChange(int range){
		changeRoute=false;
		int change=randNum.nextInt(range);				
		if (change==1){ // Mudar rota: normalRoute -> inverse route (vice versa)
			changeRoute=true;

			// troca as rotas:
			if (normalRoute) //
				normalRoute=false; // Rota inversa
			else normalRoute=true;}
	}

	public boolean changeGuard(){ // Retorna True quando for para trocar guarda

		if (l==6 && c==1) // posiçao do setpoint
			reachSetpoint=true;

		if (reachSetpoint && l==1 && c==8){// ja chegou ao setpoint e voltou à posiçao inicial
			
			reachSetpoint=false; 
			return true;}

		else return false;
	}
}
