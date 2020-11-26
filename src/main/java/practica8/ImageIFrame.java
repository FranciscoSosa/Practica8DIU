package practica8;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JInternalFrame;

public class ImageIFrame extends JInternalFrame {

    public ImageIFrame(File image) {
        initComponents();
        this.setPreferredSize(new Dimension(100, 100));
        this.setMaximizable(true);
        this.setClosable(true);
        this.setResizable(true);
        try {
            iPanel.setImage(ImageIO.read(image));
            iPanel.repaint();
            this.setTitle(image.getName());
        } catch (IOException ex) {
            Logger.getLogger(ImageIFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iPanel = new practica8.ImagePanel();

        javax.swing.GroupLayout iPanelLayout = new javax.swing.GroupLayout(iPanel);
        iPanel.setLayout(iPanelLayout);
        iPanelLayout.setHorizontalGroup(
            iPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        iPanelLayout.setVerticalGroup(
            iPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private practica8.ImagePanel iPanel;
    // End of variables declaration//GEN-END:variables
    
}
