/*
 * Representa uma personagem  
 * 
 * Caracteristicas de cada personagem: nome, posicao (l, c), direcao
 * 
 * Existem sinalizadores (flags) que indicam se a personagem esta a dormir ou a movimentar-se
 *    
 * Personagens: Heroi, Guarda, Ogre 
 */     

public abstract class Character   
{  
	protected String name;   // se fosse private as classes derivadas nao poderiam aceder
	protected Coordinate pos = new Coordinate();
	protected Direction dir = Direction.RIGHT; // fiz desta forma porque o 1º movimento do Heroi é para a direita (nao sei se está correto!)
	//protected boolean awake = true;
	//protected boolean walking = true;
	
	public Coordinate getPos()
	{
		return pos;
	}
	
	public void setPos(Coordinate newPos)
	{
		pos = newPos;
	}
	
	public Direction getDir()
	{
		return dir;
	}
	
	public void setDir(Direction newDir)
	{
		dir = newDir;
	}
	
}
