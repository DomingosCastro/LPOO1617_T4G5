package dungeon.logic;

public class Enemy extends Character {
	
	boolean awakeState = true;
	
	public Enemy(char letter, int l, int c) {
		super(letter, l, c);
		// TODO Auto-generated constructor stub
	}

	int[][] lethalTiles ; 
	
	protected void setLethalTiles(boolean state, int l, int c){
		if (state){
			lethalTiles=new int[][] {{l,c}, {l+1,c}, {l, c+1}, {l-1, c}, {l, c-1}};}
		else lethalTiles = new int[][] {{l,c}};	
	}
	
	public boolean killHero(Hero hero){

		boolean heroKilled=false;
		int heroL=hero.getLine();
		int heroC=hero.getColumn();
		int[] heroPos = new int[] {heroL,  heroC};		
		for (int i=0; i<lethalTiles.length; i++)
		{
			if (lethalTiles[i][0]==heroL && lethalTiles[i][1]==heroC)
				heroKilled=true;
		}
		return heroKilled;
	}	
}
