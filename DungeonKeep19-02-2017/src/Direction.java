
public enum Direction 
{
	RIGHT('d'), LEFT('a'), UP('w'), DOWN('s');
	
	private final char c;
	
	private Direction(char c1)
	{
		c = c1;
	}
	
	public char getC()
	{
		return c;
	}	
}
