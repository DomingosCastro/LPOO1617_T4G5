package dungeon.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage image;

    public GamePanel() {
       try {                
          image = ImageIO.read(new File("image name and path"));
       } catch (IOException ex) {
            // handle exception
       }
    } 

    @Override
    protected void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); //  drawImage(Image img, int x, int y, ImageObserver ob)         
    }
	
	Image img = new ImageIcon(this.getClass().getResource("wall_tile.png")).getImage();
	
}
