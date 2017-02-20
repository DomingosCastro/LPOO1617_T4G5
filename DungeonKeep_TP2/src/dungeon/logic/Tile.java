package dungeon.logic;

public class Tile 
{
	//private Coordinate coord;
	private int l; // coord da linha
	private int c; // coord da coluna
	private char letter; // X, I, H, G, k, ' '   
	private String type; // null, lever, lethal;
	
	// construtor 
	public Tile(int newL, int newC, char newLetter, String type)
	{ 
	
		c = newC;
		l=newL;
		this.letter = newLetter;
		this.type = type;
	}
	
	public int getTileLine()
	{
		return l;
	}
	
	public void setTileLine(int newL)
	{
		l = newL;
	}
	//
	public int getTileColumn()
	{
		return c;
	}
	
	public void setTileColumn(int newC)
	{
		c = newC;
	}
	

	
	public char getTileLetter()
	{
		return letter;
	}
	
	public void setTileLetter(char newLetter)
	{
		letter = newLetter;
	}
	
	public String getTileType()
	{
		return type;
	}
	
	public void setTileType(String type)	
	{
		this.type=type;
	}
}