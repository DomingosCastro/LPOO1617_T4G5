package dungeon.logic;
/**
 * Superclass of the enemys (guards and ogre)
 * @author Domingos
 *
 */
@SuppressWarnings("serial")
public class Enemy extends Character{
	 
	boolean awakeState = true;
	
	/**
	 * Enemy Class constructor 
	 * @param letter
	 * @param l
	 * @param c
	 */
	public Enemy(char letter, int l, int c) {
		super(letter, l, c);
		
	}

	int[][] lethalTiles ; 
	
	/**
	 * Sets the lethal tiles of the enemy. If the hero steps into one of this tiles, he dies
	 * @param state - state of the enemy: asleep/stuned=false, awake=true
	 * @param l - line position of the enemy
	 * @param c - column position of the enemy
	 */
	public void setLethalTiles(boolean state, int l, int c){
		if (state){
			lethalTiles=new int[][] {{l,c}, {l+1,c}, {l, c+1}, {l-1, c}, {l, c-1}};}
		else lethalTiles = new int[][] {{l,c}};	
	}
	
	/**
	 * Checks if the hero is killed by the enemy
	 * @param hero
	 * @return returns "true" if the hero was killed
	 */
	public boolean killHero(Hero hero){

		boolean heroKilled=false;
		int heroL=hero.getLine();
		int heroC=hero.getColumn();
		
		for (int i=0; i<lethalTiles.length; i++)
		{
			if (lethalTiles[i][0]==heroL && lethalTiles[i][1]==heroC)
				heroKilled=true;
		}
		return heroKilled;
	}	
}
