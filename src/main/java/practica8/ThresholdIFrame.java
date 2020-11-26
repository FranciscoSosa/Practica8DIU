package practica8;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class ThresholdIFrame extends javax.swing.JInternalFrame {

    public ThresholdIFrame(int threshold, BufferedImage image) {
        initComponents();
        this.setPreferredSize(new Dimension(100, 100));
        this.setMaximizable(true);
        this.setClosable(true);
        this.setResizable(true);
        this.setTitle("umbral = " + String.valueOf(threshold));
        iPanel.setImage(image);
        iPanel.repaint();
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
            .addComponent(iPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
