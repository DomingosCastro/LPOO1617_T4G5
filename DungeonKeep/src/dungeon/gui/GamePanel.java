package dungeon.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dungeon.logic.DungeonKeep;
import dungeon.logic.TileType;

public class GamePanel extends JPanel {

	private Image wall, wall2, floor, door, doorfront, key, lever, hero, guard, drunken, heroClub, 
	ogreClub , ogre, armedHero, stunedOgre, blood;
	static private DungeonKeep game;
	private String gameState;
	private String stateText;
	private int tileWidth, tileHeight;
	private boolean initialized=false;
	static boolean editingBoard = false;
	static private boolean boardEdited = false;
	static private char[][] editedBoard;
	private char[][] temporaryBoard;
	private int boardLines, boardColumns;
	private int editLines, editColumns;
	private int mouseX, mouseY;
	private JLabel label;
	MyMouseAdapter mouseAdapter;
	private TileType addingTile;



	public GamePanel() {

		loadImages();

		requestFocus();		
		mouseAdapter = new MyMouseAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		addKeyListener(new MyKeyboardAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
	} 

	public void startGame(int guard, int ogres){
		removeMouseListener(mouseAdapter);

		if(!boardEdited)
			game = new DungeonKeep();

		game.setEnemys(guard, ogres);
		game.setLevel(1);
		game.initializeLevel();
		gameState="normal";
		initialized=true;
		requestFocus();
		if(boardEdited)
			game.setEditedBoard(editedBoard);

		//setStateText();
	}

	public void playTurn(char direction ){

		if(gameState=="normal")
			gameState=game.playTurn(direction);

		setStateText();
		repaint();

		if (gameState=="next level"){
			game.initializeLevel();
			gameState="normal";
		}

	}

	public void setBoardLinesColumns(){
		if(editingBoard){
			boardLines=editLines;
			boardColumns=editColumns;
		}
		else {boardLines=game.getBoard().getLines();
		boardColumns=game.getBoard().getColumns();
		temporaryBoard=game.getBoard().getBoard();
		}
	}

	private void setStateText() {
		// TODO Auto-generated method stub
		switch (gameState){
		case "normal":
			if (game.getLevel()==1){
				stateText="Level 1";
			}
			else stateText="Level 2";
			break;
		case "next level":
			stateText="Well Done!";
			break;
		case "winner":
			stateText="WINNER!";
			break;
		case "loser":
			stateText="GAME OVER";
			break;
		}

	}

	public String getStateText(){

		System.out.println(stateText);
		return stateText;
	}

	private void loadImages() {


		wall= new ImageIcon(this.getClass().getResource("/wall (2).png")).getImage();

		wall2= new ImageIcon(this.getClass().getResource("/wall (1).png")).getImage();

		door= new ImageIcon(this.getClass().getResource("/door.png")).getImage();		

		doorfront= new ImageIcon(this.getClass().getResource("/doorfront.png")).getImage();	
		
		floor= new ImageIcon(this.getClass().getResource("/floor3.png")).getImage();

		lever= new ImageIcon(this.getClass().getResource("/lever.png")).getImage();

		key= new ImageIcon(this.getClass().getResource("/key.png")).getImage();

		hero= new ImageIcon(this.getClass().getResource("/hero.png")).getImage();

		guard= new ImageIcon(this.getClass().getResource("/guard.png")).getImage();

		ogre= new ImageIcon(this.getClass().getResource("/ogre.png")).getImage();

		ogreClub= new ImageIcon(this.getClass().getResource("/ogreClub.png")).getImage();

		ogre= new ImageIcon(this.getClass().getResource("/ogre.png")).getImage();

		armedHero=new ImageIcon(this.getClass().getResource("/heroArmed2.png")).getImage();

		heroClub = new ImageIcon(this.getClass().getResource("/heroClub.png")).getImage();

		stunedOgre = new ImageIcon(this.getClass().getResource("/stunedOgre.png")).getImage();

		drunken = new ImageIcon(this.getClass().getResource("/drunk.png")).getImage();

		blood = new ImageIcon(this.getClass().getResource("/blood.png")).getImage();
	}




	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		drawGame(g2d);

	}

	private void drawGame(Graphics2D g2d) {		

		if (initialized)
			for (int i = 0; i < game.getBoard().getLines(); i++) {
				for (int j = 0; j < game.getBoard().getColumns(); j++) {
					drawMaze(g2d, game.getBoard().getBoard(),i, j);

					if (game.getBoard().getBoard()[i][j]=='H' || game.getBoard().getBoard()[i][j]=='K' || game.getBoard().getBoard()[i][j]=='A'){					
						if(game.getHero().getArmedState())
							drawCharacter(g2d, armedHero, j, i);
						else
							drawCharacter(g2d, hero, j, i);

						if (gameState=="loser")
							drawCharacter(g2d, blood, j, i);
					}

					if (game.getBoard().getBoard()[i][j]=='G'){						

						drawCharacter(g2d, guard, j, i);
						if (gameState=="loser" && game.getHero().getLine()==i && game.getHero().getColumn()==j){
							if(game.getHero().getArmedState())
								drawCharacter(g2d, armedHero, j, i);
							else
								drawCharacter(g2d, hero, j, i);

							drawCharacter(g2d, blood, j, i);}
					}

					if (game.getBoard().getBoard()[i][j]=='g'){

						drawCharacter(g2d, drunken, j, i);

						if (gameState=="loser" && game.getHero().getLine()==i && game.getHero().getColumn()==j){							
							drawCharacter(g2d, blood, j, i);}
					}

					if (game.getBoard().getBoard()[i][j]=='c')
						drawCharacter(g2d, heroClub, j, i);

					if (game.getBoard().getBoard()[i][j]=='O'){

						drawCharacter(g2d, ogre, j, i);
						if (gameState=="loser" && game.getHero().getLine()==i && game.getHero().getColumn()==j){
							if(game.getHero().getArmedState())
								drawCharacter(g2d, armedHero, j, i);
							else
								drawCharacter(g2d, hero, j, i);

							drawCharacter(g2d, blood, j, i);}
					}


					if (game.getBoard().getBoard()[i][j]=='#'){
						if (game.getHeroClub().getLine()==i && game.getHeroClub().getColumn()==j)
							drawCharacter(g2d, heroClub, j, i);

						drawCharacter(g2d, ogreClub, j, i);
					}

					if (game.getBoard().getBoard()[i][j]=='$'){
						if (game.getHeroClub().getLine()==i && game.getHeroClub().getColumn()==j)
							drawCharacter(g2d, heroClub, j, i);

						drawCharacter(g2d, ogre, j, i);
					}

					if (game.getBoard().getBoard()[i][j]=='8'){
						if (gameState=="loser" && game.getHero().getLine()==i && game.getHero().getColumn()==j){
							if(game.getHero().getArmedState())
								drawCharacter(g2d, armedHero, j, i);
							else
								drawCharacter(g2d, hero, j, i);

							drawCharacter(g2d, blood, j, i);}

						drawCharacter(g2d, stunedOgre, j, i);
					}

					if (game.getBoard().getBoard()[i][j]=='*'){						
						if (gameState=="loser" && game.getHero().getLine()==i && game.getHero().getColumn()==j){
							if(game.getHero().getArmedState())
								drawCharacter(g2d, armedHero, j, i);
							else
								drawCharacter(g2d, hero, j, i);
							drawCharacter(g2d, blood, j, i);}

						drawCharacter(g2d, ogreClub, j, i);						
					}
				}
			}

		if (editingBoard){
			for (int i = 0; i < editLines; i++) {
				for (int j = 0; j < editColumns; j++) {
					drawMaze(g2d, temporaryBoard,i, j );
				}
			}
		}

	}

	public void initializeBoardEditing(int lines, int columns){		

		game=new DungeonKeep();
		game.setLevel(2);

		editLines=lines;
		editColumns=columns;

		temporaryBoard=new char[lines][columns];

		for (int i=0; i<lines; i++)
			for (int j=0; j<columns; j++){

				if (i==0 || i==lines-1 || j==0 || j==columns-1)
					temporaryBoard[i][j]='X';
				else temporaryBoard[i][j]=' ';				
			}		

	}



	private void drawCharacter(Graphics2D g2d, Image tile, int x, int y) {
		int dstX = x * tileWidth;
		int dstY = (int) (y * tileHeight - (15 * tileHeight / 131.0));
		int yCorrection = (int) (-50.0 * tileHeight / 131.0);
		dstY += y * yCorrection;

		// centering board
		dstX += (getWidth() - tileWidth * boardColumns) / 2.0;
		dstY += (getHeight() - (tileHeight - 0.37 * tileHeight)
				* boardLines) / 2.0;

		dstX += tileWidth / 6.0;
		dstY -= tileHeight / 50.0;

		g2d.drawImage(
				tile,
				dstX,
				dstY,
				(int) (dstX + 2.5 * tileWidth / 3.0),
				(int) (dstY + 2.5 * tileHeight / 3.0),
				0,
				0,
				tile.getWidth(null), tile.getHeight(null), null) ;

	}



	private void drawMaze(Graphics2D g2d, char[][] board, int i, int j) {

		setBoardLinesColumns();

		//		int lines = board.length;
		//		int columns = board[0].length;

		if(board[i][j]=='X' )	
			if(i<boardLines-1 && board[i+1][j]=='X')
				drawTile(g2d, wall2, j, i, boardLines, boardColumns );
			else drawTile(g2d, wall, j, i, boardLines, boardColumns);		
		else drawTile(g2d, floor, j, i, boardLines, boardColumns);

		if (board[i][j]=='I' )
			if((j>0 && board[i][j-1]=='X') || (j<boardColumns-1) && board[i][j+1]=='X')
				drawTile(g2d, doorfront, j, i);
			else drawCharacter(g2d, door, j, i);
		else if (board[i][j]=='k' )
			if (game.getLevel()==1)
				drawCharacter(g2d, lever, j, i);

			else drawCharacter(g2d, key, j, i);
		if ((board[i][j]=='$' || board[i][j]=='#') && game.getHeroClub().getColumn()!=j && game.getHeroClub().getLine()!=i)
			drawCharacter(g2d, key, j, i);
	}

	private void drawTile(Graphics2D g2d, Image tile, int x, int y, int lines, int columns) {

		// scaling tiles
		tileWidth = this.getWidth() / columns;
		tileHeight = this.getHeight()/lines;



		// correcting scaling
		int temp = (int) (81.0 * tileHeight / 131.0);
		if (this.getHeight() < lines) {
			tileHeight = this.getHeight() / lines;
			tileHeight += 81.0 * tileHeight / 131.0;
			tileWidth = (int) (tileHeight * 101.0 / 131.0);
		}

		int dstX = x * tileWidth;

		int dstY, yCorrection;

		if (tile == wall || tile == wall2)
			yCorrection = (int) (-11.0 * tileHeight / 131.0);
		else
			yCorrection = (int) (23.0 * tileHeight / 131.0);

		dstY = y * tileHeight + yCorrection;

		yCorrection = (int) (-50.0 * tileHeight / 131.0);
		dstY += y * yCorrection;

		// centering board
		dstX += (getWidth() - tileWidth * columns) / 2.0;
		dstY += (getHeight() - (tileHeight - 0.37 * tileHeight)
				*lines) / 2.0;

		g2d.drawImage(tile, dstX, dstY, dstX + tileWidth, dstY + tileHeight, 0,
				0, tile.getWidth(null), tile.getHeight(null), null);

	}

	public void setEditState(boolean state) {
		editingBoard=state;			
	}

	public void endGame() {



		//	game=null;



		initialized=false;
	}

	public class MyMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			if (!editingBoard)
				return;

			int x = (int) ((e.getX() - (getWidth() - tileWidth
					* boardColumns) / 2.0) / tileWidth);
			int y = (int) ((e.getY() - (getHeight() - (tileHeight - 0.37 * tileHeight)
					* boardLines) / 2.0) / (tileHeight - 0.37 * tileHeight));

			if (addingTile==TileType.WALL)
				temporaryBoard[mouseY][mouseX]='X';

			else if (addingTile==TileType.DOOR)
				temporaryBoard[mouseY][mouseX]='I';

			else if (addingTile==TileType.KEY)
				temporaryBoard[mouseY][mouseX]='k';

		}

		public void mouseMoved(MouseEvent e) {		

			if (!editingBoard)
				return;



			int x = (int) ((e.getX() - (getWidth() - tileWidth
					* boardColumns) / 2.0) / tileWidth);
			int y = (int) ((e.getY() - (getHeight() - (tileHeight - 0.37 * tileHeight)
					* boardLines) / 2.0) / (tileHeight - 0.37 * tileHeight));

			mouseX=x;
			mouseY=y;


			repaint();
		}

		public void mouseDragged(MouseEvent e) {
			if (!editingBoard)
				return;

			int x = (int) ((e.getX() - (getWidth() - tileWidth
					* boardColumns) / 2.0) / tileWidth);
			int y = (int) ((e.getY() - (getHeight() - (tileHeight - 0.37 * tileHeight)
					* boardLines) / 2.0) / (tileHeight - 0.37 * tileHeight));

			mouseX=x;
			mouseY=y;


			if (0 < mouseX
					&& mouseX < boardColumns - 1
					&& 0 < mouseY
					&& mouseY < boardLines - 1) {
				if (SwingUtilities.isLeftMouseButton(e))
					temporaryBoard[mouseY][mouseX] = ' ';
			} else if (SwingUtilities.isMiddleMouseButton(e))
				if (temporaryBoard[mouseY][mouseX] == 'X')
					temporaryBoard[mouseY][mouseX] = 'I';
			if (SwingUtilities.isRightMouseButton(e))
				temporaryBoard[mouseY][mouseX] = 'X';

			repaint();
		}
	}

	public void endEdition(boolean save) {

		if(save){
			editedBoard=temporaryBoard;
			boardEdited=true;
		}
		editingBoard=false;

		game.setLevel(1);		
	}

	private class MyKeyboardAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT)
				playTurn('d');			
			else if (key == KeyEvent.VK_DOWN)
				playTurn('s');
			else if (key == KeyEvent.VK_LEFT)
				playTurn('a');
			else if (key == KeyEvent.VK_UP)
				playTurn('w');			
		}		
	}

	public void setAddingTile(TileType tile) {

		addingTile=tile;			

	}



}