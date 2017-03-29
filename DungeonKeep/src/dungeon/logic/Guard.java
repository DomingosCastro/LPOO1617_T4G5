package dungeon.logic;

import java.util.Random;
/**
 * Superclass of the subclasses Roockie, Drunken and Suspicious
 * @author Domingos
 *
 */
@SuppressWarnings("serial")
public class Guard extends Enemy {

	/**
	 * Guard constructor - initializes the letter of the character, and its coordinates 
	 * 
	 * @param letter - letter that represents the character in the board
	 * @param l - line position 
	 * @param c - column position
	 */
	public Guard(char letter, int l, int c) {
		super(letter, l, c);
	}	

	boolean normalRoute = true; // Sentido de deslocação (normal ou inverso)
	boolean changeRoute = false; // Passa a true sempre que muda de sentido. Depois passa logo a falso!

	boolean reachSetpoint = false;

	Random randNum = new Random();

	/**
	 * returns the state of the guard 
	 * @return - true=awake, false=asleep
	 */
	public boolean getAwakeState(){
		return awakeState;
	}

	/**
	 * Sets the state of the guard: true=awake, false=asleep
	 * @param state
	 */
	public void setAwake(boolean state){
		awakeState=state;
	}
	
	/**
	 * Sets the route that the guard is taking, which can be the normal route (anti-clockwise), or the inverse 
	 * route (clock-wise)
	 * @param normalRoute - true=normal route, faslse = inverse route
	 */
	public void setRoute(boolean normalRoute){
		this.normalRoute=normalRoute;
	}

	public void moveGuard(Board board){};

	/**
	 * Sets the moving direction of the guard taking into account the pre-established path, 
	 * the route (normal or inverse), the guard's coordinates, and the random change of direction
	 */
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

	/**
	 * Sets, randomly, the boolean variable "changeRoute" which states if the guard must invert the direction.
	 * @param range - A random number is generated between 0 and range-1. The range of values will determine the
	 * probability of changing the direction 
	 */
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

	/**
	 * Checks if the guard must be replaced (in case they take turns)
	 * @return - returns "true" for changing the guard
	 */
	public boolean changeGuard(){ 

		if (l==6 && c==1) // posiçao do setpoint
			reachSetpoint=true;

		if (reachSetpoint && l==1 && c==8){// ja chegou ao setpoint e voltou à posiçao inicial

			reachSetpoint=false; 
			return true;}

		else return false;
	}
}
