package dungeon.cli;


import dungeon.gui.GamePanel;
import dungeon.logic.DungeonKeep;

import java.io.*;

public class SaveLoad {

	public void saveGamePanel(GamePanel gamePanel){

		try {
			FileOutputStream fileOut =
			new FileOutputStream("gamePanel.bin");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gamePanel);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in gamePanel.bin");
		}catch(IOException i) {
			i.printStackTrace();
		}


	}

	public void saveDungeonKeep(DungeonKeep dungeonKeep){
		try {
			FileOutputStream fileOut =
			new FileOutputStream("dungeonKeep.bin");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(dungeonKeep);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in dungeonKeep.bin");
		}catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public GamePanel loadGamePanel(){
		GamePanel gamePanel;
		try {
			FileInputStream fileIn = new FileInputStream("gamePanel.bin");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			gamePanel = (GamePanel) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
			return null;
		}catch(ClassNotFoundException c) {
			System.out.println("GamePanel class not found");
			c.printStackTrace();
			return null;
		}
		System.out.println("GamePanel loaded");
		return gamePanel;
	}
	
	public DungeonKeep loadDungeonKeep (){
		DungeonKeep dungeonKeep;
		try {
			FileInputStream fileIn = new FileInputStream("dungeonKeep.bin");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			dungeonKeep = (DungeonKeep) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
			return null;
		}catch(ClassNotFoundException c) {
			System.out.println("DungeonKeep class not found");
			c.printStackTrace();
			return null;
		}
		System.out.println("DungeonKeep loaded");
		return dungeonKeep;
	}
	
	
}




