
public class Tile 
{
	private Coordinate coord;
	//private int l; // coord da linha
	//private int c; // coord da coluna
	private char letter; // X, I, H, G, k, ' '   
	private boolean lethal;
	
	// construtor 
	public Tile(int newL, int newC, char newLetter, boolean isLethal)
	{ 
		coord = new Coordinate(newL, newC);
		//c = newC;
		//coord = newCoord;
		this.letter = newLetter;
		this.lethal = isLethal;
	}
	/*
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
	*/
	public Coordinate getTileCoordinate()
	{
		return coord;
	}
	
	public void setTileCoordinate(Coordinate newCoord)
	{
		//coord = newCoord; // igualar as referencias, ficam a apontar para a mesma inf
		coord.setTileLine(newCoord.getTileLine());
		coord.setTileColumn(newCoord.getTileColumn());
	}
	
	public char getTileLetter()
	{
		return letter;
	}
	
	public void setTileLetter(char newLetter)
	{
		letter = newLetter;
	}
	
	public boolean isLethal()
	{
		return lethal;
	}
	
	public void setLethal(boolean isLethal)
	{
		lethal = isLethal;
	}
}