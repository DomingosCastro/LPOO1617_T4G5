
public class Hero extends Character 
{
	private boolean hasLever = false;
	
	// construtor
	public Hero(String name)
	{
		this.name = name;
	}
	
	public Hero(String name, Coordinate pos)
	{
		this.name = name;
		this.pos = pos;
	}
	
	public boolean hasLever()
	{
		return hasLever;
	}
	
	// retorna true se o Heroi alcancou a alavanca
	public void OpenExitDoors()
	{
		hasLever = true;
	}
	
	// desbloqueia as portas de saida
	// passagem de nivel
	
	public String toString()
	{
		if(hasLever)
		{
			return "S";
		}
		else
		{
			return "I";
		}
	}
	
	// movimento do Heroi
	// CORRIGIR E COMPLETAR...
	/*public void move(Board board, Direction dir, int l, int c)
	{
		board.getBoardTiles()[l][c].setTileLetter('H');
		board.showBoard();

		do {
			dir = input.next().charAt(0);
			board.getBoardTiles()[l][c].setTileLetter(' ');	
		switch (dir)
		{
		case UP:
			if(board.getBoardTiles()[l-1][c].getTileLetter() != 'X' && board.getBoardTiles()[l-1][c].getTileLetter() != 'I')
				l--;				
			break;

		case DOWN:
			if(board.getBoardTiles()[l+1][c].getTileLetter() != 'X' && board.getBoardTiles()[l+1][c].getTileLetter() != 'I')
				l++;
			break;

		case LEFT:
			if(board.getBoardTiles()[l][c-1].getTileLetter() != 'X' && board.getBoardTiles()[l][c-1].getTileLetter() != 'I')
				c = c-1;
			System.out.println(c);
			break;

		case RIGHT:
			if (board.getBoardTiles()[l][c+1].getTileLetter() != 'X' && board.getBoardTiles()[l][c+1].getTileLetter() != 'I')
				c++;				
			break;
			
		default:
			break;
		}
		////////////////////////////////////////////////////////////////////////////
		// desbloquear as portas --> alavancas
		if (board.getBoardTiles()[l][c].getTileLetter() == 'k')
			board.unlockDoors();
				
			board.getBoardTiles()[l][c].setTileLetter('H');
			board.showBoard();
				
			} while(c != 0);
	}*/
}

