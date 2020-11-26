package practica8;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(image != null){
            this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
            g.drawImage(image, 0, 0, null);
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    private BufferedImage image = null;
}
