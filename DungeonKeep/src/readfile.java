// Tive a ver cenas no youtube para ler a partir de ficheiro, mas ainda nao esta acabado, 
// e n�o � uma prioridade para j�. 
// Pode se ignorar isto para j�.

import java.io.*;
import java.util.*;


public class readfile {

	private Scanner fileInput;
	
	public void openFile(){
		try{
			fileInput=new Scanner (new File("DungeonMap.txt"));
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
	}
	
	public void readFile(){
		
		// Ler o mapa a partir do ficheiro - preencher os Tiles com a informa��o do ficheiro
	}
	
	public void closeFile(){
		fileInput.close();
	}
	
}
