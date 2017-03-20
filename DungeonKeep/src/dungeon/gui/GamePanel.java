package dungeon.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dungeon.cli.DungeonKeep;

public class GamePanel extends JPanel {
	
	private Image wall;
    private Image floor;
    private DungeonKeep game;
    
    public GamePanel() {
     
    	loadImages();
    	
    } 

    private void loadImages() {
		
    	ImageIcon icon;
    	icon= new ImageIcon(this.getClass().getResource("/wall_tile.png"));
		wall=icon.getImage();
		
		icon= new ImageIcon(this.getClass().getResource("/floor.png"));
		floor=icon.getImage();
		
	}
    
    
    
    
    
    
    
//
//	@Override
//    protected void paintComponent(Graphics g) {
//    	
//        super.paintComponent(g);
//        g.drawImage(image, 0, 0, this); //  drawImage(Image img, int x, int y, ImageObserver ob)         
//    }
//	
//	//Image img = new ImageIcon(this.getClass().getResource("wall_tile.png")).getImage();
//	
}
