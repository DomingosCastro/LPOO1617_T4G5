//package dungeon.logic;
//
//public class GameControl {
//	int level;
//	boolean inGame;
//	
//	public GameControl(int level, boolean inGame){
//		this.level=level;
//		this.inGame=inGame;
//	}
//	
//	public void setLevel(int level){
//	this.level=level;	
//	}
//	
//	public int getLevel(){
//		return level;
//	}
//		
//	public boolean getInGame(){
//		return inGame;
//	}
//	public void checkGameState(Board board, Hero hero){
//	   int l=hero.getLine();
//	   int c=hero.getColumn();
//	   
//	   if (board.getBoardTiles()[l][c].getTileState()=="lethal"){
//		   inGame=false;
//	   }
//	 }
//	
//}
