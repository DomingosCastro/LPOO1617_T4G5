package dungeon.cli;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static java.lang.System.*;
import java.io.*;

public class SaveLoad {

	public void saveBoard(char[][] board){

		try {

			FileWriter fw = new FileWriter("SavedBoard");
			PrintWriter pw = new PrintWriter(fw);

			pw.println(board);

			pw.close();

		} catch(IOException e){
			out.println("ERROR");
		}


	}

	public char[][] loadBoard(){

		char[][] board;
		String str=null;
		ArrayList<String> boardString = new ArrayList<String>();
		int lineCount=0;

		try{


			FileReader fr = new FileReader("SavedBoard");
			BufferedReader br = new BufferedReader(fr);


			while ((str = br.readLine()) != null){				
				out.println(str + "\n");
				boardString.add(str);
			}
			br.close();
		}catch(IOException e){
			out.println("File not found");
		}

		int lines =	boardString.size();
		int columns = boardString.get(0).length();
		board= new char[lines][columns];

		for (int i =0; i< lines; i++)
			for (int j=0; j<columns; j++)
				board[i][j]=boardString.get(i).charAt(j);		
		
		return board;
	}
}




