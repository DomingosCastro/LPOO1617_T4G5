package dungeon.logic;

public class Guard extends Character  {

	int path=1;
	boolean clockwise = false;  // <---------- VER ISTO DEPOIS
	public Guard(char letter,  int l, int c){
		
		this.letter=letter;
		this.l=l;
		this.c=c;		
	}
	
	boolean awake = true;
	
	
	public boolean isAwake(){
		return awake;
	}

	public void setAwake(boolean state){
		awake=state;
	}
	public void setClockwise(boolean clokwise){
		this.clockwise=clockwise;
	}

	public void movingDirection(){

		if(path==1){
			if (l==1 && c==7)
			path=2;
			else dir = Direction.LEFT;
		}
		if (path==2){
			
			if (l==5 && c==7)
				path=3;
			else dir = Direction.DOWN;
		}
		if (path==3){
			dir = Direction.LEFT;
			if (l==5 && c==1)
				path=4;
		}
		if (path==4){
			dir = Direction.DOWN;
			if (l==6 && c==1)
				path=5;
		}
		if (path==5){
			dir = Direction.RIGHT;
			if (l==6 && c==8)
				path=6;
		}
		if (path==6) {
			dir = Direction.UP;
			if (l==1 && c==8)
				path=1;
		}
	}
	
	public void setLethalTiles(Tile[][] boardTiles, String type){
		boardTiles[l-1][c].setTileState(type);
		boardTiles[l+1][c].setTileState(type);
		boardTiles[l][c-1].setTileState(type);
		boardTiles[l][c+1].setTileState(type);
	}	
	
}



