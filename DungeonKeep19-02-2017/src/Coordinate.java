
/*
 * Representa a localizacao da personagem no ponto de coordenadas inteiras (l, c) 
 */

public class Coordinate 
{
	private int l; // coord da linha
	private int c; // coord da coluna
	   
	// construtor de uma coordenada na origem	
	public Coordinate()
	{
		this.l = 0;
		this.c = 0;
	}
	
	// construtor de uma coordenada na localizacao (l, c)
	public Coordinate(int newL, int newC)
	{
		this.l = newL;
		this.c = newC;
	}
	
	// construtor de uma coordenada com a mesma localizacao do objeto Coord especificado  
	public Coordinate(Coordinate position)
	{
		l = position.getTileLine();
		c = position.getTileColumn();
	}

	// metodo equals para comparar atributos
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Coordinate))
		{
			return false;
		}
		Coordinate otherCoordinate = (Coordinate) obj;
		return (this.l == otherCoordinate.l) && (this.c == otherCoordinate.c) ;
	}
	
	public int getTileLine()
	{    
		return l;
	}
	
	public void setTileLine(int newL)
	{
		this.l = newL;
	}
	
	public int getTileColumn()
	{
		return c;
	}
	
	public void setTileColumn(int newC)
	{
		this.c = newC;
	}	
}
