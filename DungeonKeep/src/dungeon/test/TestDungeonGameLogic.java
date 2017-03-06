package dungeon.test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import dungeon.cli.ShowBoard;
import dungeon.logic.Board;
import dungeon.logic.Character;
import dungeon.logic.Club;
import dungeon.logic.Direction;
import dungeon.logic.Guard;
import dungeon.logic.Hero;
import dungeon.logic.Ogre;


public class TestDungeonGameLogic {

	char[][] map = {{'X', 'X', 'X', 'X', 'X'},
			        {'X', 'H', ' ', 'G', 'X'},
			        {'I', ' ', ' ', ' ', 'X'},
			        {'I', 'k', ' ', ' ', 'X'},
			        {'X', 'X', 'X', 'X', 'X'}};
	
	char[][] map2 = {{'X', 'X', 'X', 'X', 'X'},
	                 {'X', 'H', ' ', 'O', 'X'},
	                 {'I', ' ', ' ', ' ', 'X'},
	                 {'I', 'k', ' ', ' ', 'X'},
	                 {'X', 'X', 'X', 'X', 'X'}};
	
	ShowBoard showBoard = new ShowBoard();
	/*
	@Test
	public void MoveToFreeCeel() {
		Board board = new Board(map);
		int level=1;
		Hero hero = new Hero('H', 1, 1);
		
		// Verifica Posiçao inicial:
		assertEquals(1, hero.getLine());		
		assertEquals(1, hero.getColumn());
		
	    // Descer ate uma posiçao:
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
		
		// Verifica Posiçao inicial:
		assertEquals(1, hero.getLine());		
		assertEquals(1, hero.getColumn());
		
	    // Descer ate uma posiçao:
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
		
		// Descer ate a posiçao da Porta
		hero.setDir(Direction.DOWN);		
		hero.moveCharacter(board);
		hero.setDir(Direction.LEFT);		
		hero.moveCharacter(board);
		
		// Faria as alteraçoes no changingBoard caso se tivesse alcançado alavanca
		board.initializeChangingBoard();
		
		// Verifica que nao ultrapassa a porta
		assertTrue(hero.getColumn()!=0);
	}
	
	
	@Test
	public void OpenDoorLever(){
		Board board = new Board(map);
		int level=1;
		Hero hero = new Hero('H', 1, 1);
		
		// Descer ate a posiçao da Porta
		hero.setDir(Direction.DOWN);		
		hero.moveCharacter(board);
		hero.setDir(Direction.DOWN);		
		hero.moveCharacter(board);
		
		// Desbloquear as portas caso apanhe a chave 
		if (hero.catchKey(board, level)){
			board.unlockDoors(level);
			board.initializeChangingBoard();
		}

        // Verifica se a pota abriu
		assertEquals('S', board.getBoard()[2][0]);
		assertEquals('S', board.getBoard()[3][0]);
	}
	
	
	@Test
	public void passLevel1(){
		Board board = new Board(map2);
		int level=1;
		Hero hero = new Hero('H', 1, 1);
		
		// Descer ate a posiçao da Porta
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
		
	// Descer ate a posiçao da key
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
		
		// Descer ate a posiçao da Porta
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
		
		// Descer ate a posiçao da Porta
		hero.setDir(Direction.DOWN);		
		hero.moveCharacter(board);
		hero.setDir(Direction.DOWN);		
		hero.moveCharacter(board);
		
		// Desbloquear as portas caso apanhe a chave 
		if (hero.catchKey(board, level)){
			board.unlockDoors(level);
			board.initializeChangingBoard();
		}

        // Verifica se a pota abriu
		assertEquals('S', board.getBoard()[2][0]);
		assertEquals('S', board.getBoard()[3][0]);
	}
	
	@Test
	public void moveToOpenedDoor(){
		Board board = new Board(map2);
		int level=2;
		Hero hero = new Hero('H', 1, 1);
		
		// Descer ate a posiçao da Porta
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
	*/
	@Test
	public void moveRandomOgre(){
		Board board = new Board(map2);
		int level=2;
		Ogre ogre=new Ogre('O', 1, 3);
		
		TestRandomOgre test = new TestRandomOgre();
		boolean result=test.testRandomOgreBehaviour(ogre, board);
		
		assertTrue(result);
		
	}
}
