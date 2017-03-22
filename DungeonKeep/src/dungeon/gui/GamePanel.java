package dungeon.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dungeon.cli.DungeonKeep;

public class GamePanel extends JPanel {




	private Image wall, floor, door, key, lever, hero, guard, drunken, heroClub, ogreClub , ogre, armedHero, stunedOgre;
	private DungeonKeep game;
	private String gameState;
	private int tileWidth, tileHeight;
	private boolean initialized=false;

	public GamePanel() {

		loadImages();

	} 

	public void startGame(int guard, int ogres){
		
		game = new DungeonKeep();
		game.setEnemys(guard, ogres);
		game.initializeLevel();
		gameState="normal";
		initialized=true;
		
	}

	public void playTurn(char direction ){
		if(gameState=="normal")
			gameState=game.playTurn(direction);
		repaint();
	}
	
	public String getGameState(){
		return gameState;
	}
	
	public void setGameState(String state){
		gameState=state;
	}
	

	
	private void loadImages() {

		
		wall= new ImageIcon(this.getClass().getResource("/wall.png")).getImage();		

		door= new ImageIcon(this.getClass().getResource("/door.png")).getImage();		
		
		floor= new ImageIcon(this.getClass().getResource("/floor.png")).getImage();
		
		lever= new ImageIcon(this.getClass().getResource("/lever.png")).getImage();
				
		key= new ImageIcon(this.getClass().getResource("/key.png")).getImage();
				
		hero= new ImageIcon(this.getClass().getResource("/hero.png")).getImage();
			
		guard= new ImageIcon(this.getClass().getResource("/guard.png")).getImage();
				
		ogre= new ImageIcon(this.getClass().getResource("/ogre.png")).getImage();
		
		ogreClub= new ImageIcon(this.getClass().getResource("/ogreClub.png")).getImage();
		
		ogre= new ImageIcon(this.getClass().getResource("/ogre.png")).getImage();
		
		armedHero=new ImageIcon(this.getClass().getResource("/armedHero.png")).getImage();
		
		heroClub = new ImageIcon(this.getClass().getResource("/heroClub.png")).getImage();
		
		stunedOgre = new ImageIcon(this.getClass().getResource("/stunedOgre.png")).getImage();
		
		drunken = new ImageIcon(this.getClass().getResource("/drunk.png")).getImage();
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
					drawMaze(g2d, i, j);
					
					if (game.getBoard().getBoard()[i][j]=='H'){
						 drawCharacter(g2d, hero, j, i);
					}
					
					if (game.getBoard().getBoard()[i][j]=='A' || (game.getBoard().getBoard()[i][j]=='K' && game.getHero().getArmedState()))
						drawCharacter(g2d, armedHero, j, i);
						
					if (game.getBoard().getBoard()[i][j]=='G'){
						drawCharacter(g2d, guard, j, i);
					}
					
					if (game.getBoard().getBoard()[i][j]=='g'){
						drawCharacter(g2d, drunken, j, i);
					}
					
					if (game.getBoard().getBoard()[i][j]=='c')
						drawCharacter(g2d, ogreClub, j, i);
					
					if (game.getBoard().getBoard()[i][j]=='O'){
						drawCharacter(g2d, ogre, j, i);
					}
					
					if (game.getBoard().getBoard()[i][j]=='8'){
						drawCharacter(g2d, stunedOgre, j, i);
					}
					
					if (game.getBoard().getBoard()[i][j]=='*'){
						drawCharacter(g2d, ogreClub, j, i);
					}
				
				}
			}
		else g2d.drawImage(hero, 0, 0, null);
			

	}

		private void drawCharacter(Graphics2D g2d, Image tile, int x, int y) {
			int dstX = x * tileWidth;
			int dstY = (int) (y * tileHeight - (15 * tileHeight / 131.0));
			int yCorrection = (int) (-50.0 * tileHeight / 131.0);
			dstY += y * yCorrection;

			// centering board
			dstX += (getWidth() - tileWidth * game.getBoard().getColumns()) / 2.0;
			dstY += (getHeight() - (tileHeight - 0.37 * tileHeight)
					* game.getBoard().getLines()) / 2.0;

			dstX += tileWidth / 6.0;
			dstY += tileHeight / 6.0;

			g2d.drawImage(
					tile,
					dstX,
					dstY,
					(int) (dstX + 2.0 * tileWidth / 3.0),
					(int) (dstY + 2.0 * tileHeight / 3.0),
					0,
					0,
					tile.getWidth(null), tile.getHeight(null), null) ;
			
	}

	private void drawMaze(Graphics2D g2d, int i, int j) {

			char[][] boardTiles=game.getBoard().getBoard();
		if(boardTiles[i][j]=='X' )	
			drawTile(g2d, wall, j, i);		
		else drawTile(g2d, floor, j, i);
		
		if (boardTiles[i][j]=='I' )
			drawTile(g2d, door, j, i);
		else if (boardTiles[i][j]=='k' )
			if (game.getLevel()==1)
				drawTile(g2d, lever, j, i);
		
			else drawTile(g2d, key, j, i);
		if (boardTiles[i][j]=='$' )
			drawTile(g2d, key, j, i);
	}

	
	private void drawTile(Graphics2D g2d, Image tile, int x, int y) {

		// scaling tiles
		tileWidth = this.getWidth() / game.getBoard().getColumns();
		tileHeight = this.getHeight()/game.getBoard().getLines();



		// correcting scaling
		int temp = (int) (81.0 * tileHeight / 131.0);
		if (this.getHeight() < game.getBoard().getLines()) {
			tileHeight = this.getHeight() / game.getBoard().getLines();
			tileHeight += 81.0 * tileHeight / 131.0;
			tileWidth = (int) (tileHeight * 101.0 / 131.0);
		}

		int dstX = x * tileWidth;

		int dstY, yCorrection;
		//				if (tile == wall || (tile == exit && !game.exitIsOpen()))
		if (tile == wall )
			yCorrection = (int) (-11.0 * tileHeight / 131.0);
		else
			yCorrection = (int) (23.0 * tileHeight / 131.0);

		dstY = y * tileHeight + yCorrection;

		yCorrection = (int) (-50.0 * tileHeight / 131.0);
		dstY += y * yCorrection;

		// centering board
		dstX += (getWidth() - tileWidth * game.getBoard().getColumns()) / 2.0;
		dstY += (getHeight() - (tileHeight - 0.37 * tileHeight)
				*game.getBoard().getLines()) / 2.0;

		g2d.drawImage(tile, dstX, dstY, dstX + tileWidth, dstY + tileHeight, 0,
				0, tile.getWidth(null), tile.getHeight(null), null);

	}

}