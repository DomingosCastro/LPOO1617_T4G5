
public class Tiles {

	private int l; // Coordenada linha
	private int c; // Coordenada coluna
	private String state; // locked , free, lethal 
	private char letter; // X, ' ', I, H, G, k
	
	// Construtor
	public Tiles(int newL, int newC, String newState, char newLetter){
		l=newL;
		c=newC;
		state=newState; 
		letter=newLetter;
	}
	
	public void setTileLetter(char newLetter){
		letter=newLetter;
	}
	
	public void setTileState(String newState){
		state=newState;
	}
	
	public int getTileColumn(){
		return c;
	}
	
	public int getTileLine(){
		return l;
	}
	
	public char getTileLetter(){
		return letter;
	}
	public String getTileState(){
		return state;
	}
	
	
}
