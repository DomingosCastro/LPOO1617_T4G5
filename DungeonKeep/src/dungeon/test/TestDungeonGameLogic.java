package dungeon.test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;


import java.util.Collection;
import java.util.Scanner;

import dungeon.cli.ShowBoard;
//import dungeon.cli.SaveLoad;
import dungeon.logic.Board;
import dungeon.logic.Character;
import dungeon.logic.Club;
import dungeon.logic.Direction;
import dungeon.logic.Drunken;
import dungeon.logic.Guard;
import dungeon.logic.Hero;
import dungeon.logic.Ogre;
import dungeon.logic.Rookie;
import dungeon.logic.Suspicious;
import dungeon.logic.DungeonKeep;
//
public class TestDungeonGameLogic {
	//
	
	char[][] map = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', ' ', 'X'},			
			{'I', 'k', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	
		char[][] map2 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'I', ' ', ' ', ' ', 'X'},
				{'I', 'k', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
	 
		char[][] map3 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'I', ' ', ' ', ' ', 'X'},
				{'I', 'c', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
	
		char[][] map4 = {{'X','X','X','X','X','X','X','X','X','X'}, 
				{'X',' ',' ',' ','I',' ','X',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X',' ',' ','X'}, 
				{'X',' ','I',' ','I',' ','X',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X',' ',' ','X'}, 
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
				{'X','X','X',' ','X','X','X','X',' ','X'}, 
				{'X',' ','I',' ','I',' ','X','k',' ','X'}, 
				{'X','X','X','X','X','X','X','X','X','X'}};
	
		char[][] map5 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', 'k', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
	
		char[][] map6 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		
		char[][] map7 = {{'X', 'X', 'X'},
				{'X', ' ', ' '},
				{'I', ' ', ' '}};
				
	
		ShowBoard showBoard = new ShowBoard();
	
		@Test
		public void MoveToFreeCeel() {
			Board board = new Board(map);
			int level=1;
			Hero hero = new Hero('H', 1, 1);
	
			// Verifica Posi�ao inicial:
			assertEquals(1, hero.getLine());		
			assertEquals(1, hero.getColumn());
	
			// Descer ate uma posi�ao:
			hero.movingDirection('s');
			hero.setDir(hero.getDir());			
			hero.moveCharacter(board);
	
			// Verifica se desceu
			assertEquals(2, hero.getLine());		
			assertEquals(1, hero.getColumn());
		}
	
		@Test
		public void MoveToWall() {
			Board board = new Board(map);
			int level=1;
			Hero hero = new Hero('H', 1, 1);
	
			// Verifica Posi�ao inicial:
			assertEquals(1, hero.getLine());		
			assertEquals(1, hero.getColumn());
	
			// Descer ate uma posi�ao:
			hero.movingDirection('w');
			hero.setDir(hero.getDir());		
			hero.moveCharacter(board);
	
			// Verifica nao moveu
			assertEquals(1, hero.getLine());		
			assertEquals(1, hero.getColumn());
		}
	
		@Test
		public void testHeroKilledByGuard() {
			Board board = new Board(map);
			Hero hero = new Hero('H', 1, 1);
	
			Guard guard = new Guard('G', 1, 3);	
			guard.setLethalTiles(true, guard.getLine(), guard.getColumn());
	
			hero.setDir(Direction.RIGHT);		
			hero.moveCharacter(board);
	
			assertTrue(guard.killHero(hero));		
		}
	
		@Test
		public void moveToClosedDoor(){
			Board board = new Board(map);
			int level=1;
			Hero hero = new Hero('H', 1, 1);
	
			// Descer ate a posi�ao da Porta
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
			hero.setDir(Direction.LEFT);		
			hero.moveCharacter(board);
	
			// Faria as altera�oes no changingBoard caso se tivesse alcan�ado alavanca
			board.initializeChangingBoard();
	
			// Verifica que nao ultrapassa a porta
			assertTrue(hero.getColumn()!=0);
		}
	
	
		@Test
		public void OpenDoorLever(){
			Board board = new Board(map2);
			int level=1;
			Hero hero = new Hero('H', 1, 1);
	
			// Descer ate a posi�ao da Porta
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
	
			// Desbloquear as portas caso apanhe a chave 
			if (hero.catchKey(board, level)){
				board.unlockDoors(level);
				board.initializeChangingBoard();
			}
	
			// Verifica se a porta abriu
			assertEquals('S', board.getBoard()[2][0]);
			assertEquals('S', board.getBoard()[3][0]);
		}
	
	
		@Test
		public void passLevel1(){
			Board board = new Board(map2);
			int level=1;
			Hero hero = new Hero('H', 1, 1);
	
			// Descer ate a posi�ao da Porta
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
	
			// Desbloquear as portas caso apanhe a chave 
			if (hero.catchKey(board, level)){
				board.unlockDoors(level);
				board.initializeChangingBoard();
			}
	
			hero.setDir(Direction.LEFT);		
			hero.moveCharacter(board);
	
			// Se ultrapassar porta, passa de nivel:
			if (hero.getColumn()==0)
				level++;
	
			// Verifica se ultrapassa o nivel
			assertEquals(2, level);
		}
	
		@Test
		public void testHeroKilledByOgre() {
			Board board = new Board(map2);
			Hero hero = new Hero('H', 1, 1);
	
			Ogre ogre = new Ogre('O', 1, 3);	
			ogre.setLethalTiles(true, 1, 3);
	
			hero.setDir(Direction.RIGHT);		
			hero.moveCharacter(board);
	
			assertTrue(ogre.killHero(hero));		
		}
	
		@Test
		public void testHeroCatchKey() {
			Board board = new Board(map2);
			int level=2;
			Hero hero = new Hero('H', 1, 1);
	
			// Descer ate a posi�ao da key
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
	
			hero.catchKey(board, level);
	
			// Verifica se muda para 'K'
			assertEquals('K', hero.getLetter());		
		}
	
		@Test
		public void moveToCloseDoor(){
			Board board = new Board(map2);
			int level=2;
			Hero hero = new Hero('H', 1, 1);
	
			// Descer ate a posi�ao da Porta
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
			hero.setDir(Direction.LEFT);		
			hero.moveCharacter(board);
	
			// Verifica se vai para a coluna 0
			assertEquals(false, hero.getColumn()==0);
		}
	
		@Test
		public void OpenDoor(){
			Board board = new Board(map2);
			int level=2;
			Hero hero = new Hero('H', 1, 1);
	
			// Descer ate a posi�ao da Porta
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
	
			// Desbloquear as portas caso apanhe a chave 
			if (hero.catchKey(board, level)){
				board.unlockDoors(level);
				board.initializeChangingBoard();
			}
	
			// Verifica se a porta abriu
			assertEquals('S', board.getBoard()[2][0]);
			assertEquals('S', board.getBoard()[3][0]);
		}
	
		@Test
		public void moveToOpenedDoor(){
			Board board = new Board(map2);
			int level=2;
			Hero hero = new Hero('H', 1, 1);
	
			// Descer ate a posi�ao da Porta
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
			hero.setDir(Direction.DOWN);		
			hero.moveCharacter(board);
	
			// Desbloquear as portas caso apanhe a chave 
			if (hero.catchKey(board, level)){
				board.unlockDoors(level);
				board.initializeChangingBoard();
			}
	
			hero.setDir(Direction.LEFT);		
			hero.moveCharacter(board);
	
			// Verifica se ultrapassa a porta
			assertTrue(hero.getColumn()==0);
		}
	
		@Test
		public void moveRandomOgre(){
			Board board = new Board(map2);
			int level=2;
			Ogre ogre=new Ogre('O', 1, 3);
	
			TestRandomOgre test = new TestRandomOgre();
	
			test.testRandomOgreBehaviour(ogre, board);
		}
	
		@Test
		public void moveRandomClub(){
			Board board = new Board(map2);
			int level=2;
			Ogre ogre=new Ogre('O', 2, 2);
	
			TestRandomOgre test = new TestRandomOgre();
	
			test.testRandomClubBehaviour(ogre, board);
		}
	
		@Test
		public void TestSetCharactersInBoard(){
			int level=1;
			Board board = new Board(map);
			Hero hero = new Hero('H', 1, 1);
			Rookie rookie = new Rookie('G', 1, 3);
			Drunken drunken = new Drunken('G', 1, 3 );
			Suspicious suspicious = new Suspicious('G', 1, 3 );
	
			ArrayList<Guard> guards=new ArrayList();
	
			guards.add(rookie);
			guards.add(drunken);		
			guards.add(suspicious);
	
			ArrayList<Character> characters = new ArrayList();
	
			characters.add(hero);
			characters.add(guards.get(0));
	
			board.setCharactersInBoard(characters);
			showBoard.printBoard(board, level);
		}
	
//		@Test
//		public void testMoveHeroWithKeyBoard(){
//			int level=1;
//			Scanner input = new Scanner(System.in);
//			char playerInput; 
//			Board board = new Board(map);
//			Hero hero = new Hero('H', 1, 1);
//			ArrayList<Character> characters = new ArrayList();
//	
//			characters.add(hero);
//	
//			board.setCharactersInBoard(characters, level);
//			showBoard.printBoard(board, level);
//			do{
//				playerInput = input.next().charAt(0);		
//			} while(playerInput!='w' && playerInput!='a' && playerInput!='s' && playerInput!='d');
//	
//			// Movimento HERO
//			hero.moveHero(playerInput, board, level);
//	
//			board.setCharactersInBoard(characters, level);
//			showBoard.printBoard(board, level);
//	
//			assertTrue(hero.getColumn()!=1 || hero.getLine()!=1);
//	
//		}
	
		@Test
		public void testChangeGuard(){
			int level=1;
			Board board = new Board(map4);
			Hero hero = new Hero('H', 1, 1);
			Rookie rookie = new Rookie('G', 1, 8);
			Drunken drunken = new Drunken('G', 1, 8 );
			Suspicious suspicious = new Suspicious('G', 1, 8 );
	
			ArrayList<Guard> guards=new ArrayList();
	
			guards.add(rookie);
			guards.add(drunken);		
			guards.add(suspicious);
	
			ArrayList<Character> characters = new ArrayList();
	
			characters.add(hero);
			characters.add(guards.get(0));
			int choseGuard=0;
			boolean heroKilled;
			while(choseGuard<2){
				// Escolhe o Guarda
				if (guards.get(choseGuard).changeGuard())						
					choseGuard=choseGuard+1;
	
	
				// Substitui o guarda anterior no arraylist characters
				characters.set(1, guards.get(choseGuard));
	
				// Depois de escolhido o guard, move-o
				guards.get(choseGuard).moveGuard(board);
	
				// Verifica se mata o heroi
				heroKilled=guards.get(choseGuard).killHero(hero);
			}
	
		}
	
	
	
		@Test
		public void testGetHeroArmed(){
			int level=2;
			Scanner input = new Scanner(System.in);
			char playerInput; 
			Board board = new Board(map3);
			Hero hero = new Hero('H', 1, 1);
			ArrayList<Character> characters = new ArrayList();
	
			playerInput='s';
			hero.moveHero(playerInput, board, level);
	
			playerInput='s';
			hero.moveHero(playerInput, board, level);
	
			assertEquals('A', hero.getLetter());
		}
	
//		@Test(timeout=100)
//		public void testOgreLetters(){
//			Board board = new Board(map5);
//			int level=2;
//			Ogre ogre=new Ogre('O', 1, 3);
//			Hero hero= new Hero('H', 2, 1);
//	
//			boolean clubLetter=false;
//			boolean ogreLetter=false;
//			ArrayList<Character> characters = new ArrayList();
//	
//			characters.add(ogre);
//	
//			ogre.setClub(ogre.getLine(), ogre.getColumn());	
//			int i=0;
//			while (!ogreLetter || !clubLetter){
//	
//				board.setCharactersInBoard(characters, level);
//				showBoard.printBoard(board, level);
//	
//				ogre.moveOgre(board,  hero);
//				if(ogre.getLetter()=='$')
//					ogreLetter=true;
//				if(ogre.getClubLetter()=='$')
//					clubLetter=true;
//			}        
//		}
	
		@Test(timeout=100)
		public void testOgreStunned(){
			Board board = new Board(map6);
			int level=2;
			Ogre ogre=new Ogre('O', 1, 3);
			Hero hero= new Hero('H', 1, 1);
	
			boolean clubLetter=false;
			boolean ogreLetter=false;
			ArrayList<Character> characters = new ArrayList();
	
			characters.add(ogre);
			characters.add(hero);
	
			ogre.setClub(ogre.getLine(), ogre.getColumn());	
			int i=0;
	
			hero.setArmedState();
	
			while (!ogreLetter){
	
				board.setCharactersInBoard(characters);
				showBoard.printBoard(board, level);
	
				ogre.moveOgre(board,  hero);
				if(ogre.getLetter()=='8')
					ogreLetter=true;
	
			}        
		}		
		
		/* Verificar o n� de portas de saida em todas as boards*/
		@Test
		public void testFixedBoard() { 
			
			DungeonKeep m = new DungeonKeep();
			
			for (int i = 1; i <= m.getTotalNLevels(); i++)
			{
				assertEquals(m.getNDoorsArray()[i-1], checkExitDoors(m.getBoard(i)));
			}
			
			// teste para obter um board que nao existe 
			m.getBoard(-1);
		}
		
		/* Percorrer a matriz das posicoes */
		public int checkExitDoors(char[][] fixedBoard) {
			
			int countExit = 0;
			for (int i = 0; i < fixedBoard.length; i++)
				for (int j = 0; j < fixedBoard.length; j++)
					if (i == 0 || j == 0 || i == fixedBoard.length - 1 || j == fixedBoard.length - 1)
						if (fixedBoard[i][j] == 'I')
							countExit++;
			return countExit;	
		}
		 
		@Test
		public void testLevelSetting()
		{
			DungeonKeep dk = new DungeonKeep();
			
			for (int i = 1; i <= dk.getTotalNLevels(); i++)
			{
				dk.setLevel(i);
				assertEquals(i, dk.getLevel());
			}
		}
		
		@Test
		public void testLevelInitialization()
		{
			DungeonKeep dk = new DungeonKeep();
			
			dk.setLevel(1);
			for (int i = 0; i <= 2; i++)
			{
				dk.setEnemys(i, 0);
				dk.initializeLevel();
				assertEquals(2, dk.getCharacters().size());
			}
			
			dk.setLevel(2);
			dk.setEnemys(0, 1);
			dk.initializeLevel();
			assertEquals(2, dk.getCharacters().size());
			
		}
		
		@Test
		public void testGuardState()
		{
			DungeonKeep dk = new DungeonKeep();
			
			dk.setLevel(1);
			dk.setEnemys(0, 0);
			dk.initializeLevel();
			assertEquals(true, dk.getGuard().getAwakeState());
		}
		
		@Test
		public void testBoardDimensions()
		{
			Board board = new Board(map4);
			assertEquals(map4.length, board.getLines());
			assertEquals(map4[0].length, board.getColumns());
		}
		
		@Test
		public void testEditedBoard()
		{
			ArrayList<int[]> newPositions = new ArrayList<>();
			int[] pos1={1,2};
			int[] pos2={3,3};
			newPositions.add(pos1);
			newPositions.add(pos2);
			
			char[][] mapTest=map;
			
			DungeonKeep dk = new DungeonKeep(); 
			dk.setEditedBoard(mapTest, newPositions);
			ArrayList<int[]> editedPosition= dk.getNewPositions();
			char[][] editedBoard = dk.getEditedBoard();
			
			
			assertEquals(newPositions, editedPosition);
			boolean valid=true;
			for (int i=0; i<4; i++)
				for (int j=0; j<5; j++)
					if(mapTest[i][j]!=editedBoard[i][j])
						valid=false;
			assertTrue(valid);
		}
			
		@Test
		public void testClub()
		{
			DungeonKeep dk = new DungeonKeep();
			
			dk.setLevel(2);
			dk.setEnemys(0, 1);
			dk.initializeLevel();
			
			assertEquals('*', dk.getOgres().get(0).getClubLetter());
		}
		
		@Test
		public void testSword()
		{
			DungeonKeep dk = new DungeonKeep();
			
			dk.setLevel(1);
			dk.setEnemys(0, 0);
			dk.initializeLevel();
	
			assertEquals('c', dk.getHeroClub().getSwordLetter());
		}
		
		@Test
		public void testHeroArmedStateAtLevel1()
		{
			DungeonKeep dk = new DungeonKeep();
			
			dk.setLevel(1);
			dk.setEnemys(0, 0);
			dk.initializeLevel();
	
			assertEquals(false, dk.getHero().getArmedState());
		}
		

}

