package practica8;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class DesktopFrame extends javax.swing.JFrame {

    public DesktopFrame() {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        initComponents();
        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                for (JInternalFrame internalFrame: desktop.getAllFrames()) {
                    Point frameLoc = internalFrame.getLocation();
                    if((desktop.getHeight() + desktop.getWidth()) < (frameLoc.x + frameLoc.y)){
                        internalFrame.setLocation(new Point(0,0));
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openOption = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        thresholdOption = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        quitOption = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Umbralización de imágenes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jMenu1.setText("Opciones");

        openOption.setText("abrir");
        openOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openOptionActionPerformed(evt);
            }
        });
        jMenu1.add(openOption);
        jMenu1.add(jSeparator1);

        thresholdOption.setText("umbralizar");
        thresholdOption.setEnabled(false);
        thresholdOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thresholdOptionActionPerformed(evt);
            }
        });
        jMenu1.add(thresholdOption);
        jMenu1.add(jSeparator2);

        quitOption.setText("salir");
        quitOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitOptionActionPerformed(evt);
            }
        });
        jMenu1.add(quitOption);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void openOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openOptionActionPerformed
        int res = fileChooser.showOpenDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            for (JInternalFrame internalFrame: desktop.getAllFrames()) {
                internalFrame.dispose();
            }
            File imageFile = fileChooser.getSelectedFile();
            imagePath = imageFile.getAbsolutePath();
            ImageIFrame internalFrame = new ImageIFrame(imageFile);
            desktop.add(internalFrame);
            internalFrame.setVisible(true);
            thresholdOption.setEnabled(true);
        }
    }//GEN-LAST:event_openOptionActionPerformed

    private void thresholdOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thresholdOptionActionPerformed
        String input = JOptionPane.showInputDialog(rootPane, "Introduzca umbral");
        if(input != null){
            try{
                int threshold = Integer.parseInt(input);
                if(threshold >= 0 && threshold <= 255){
                    Mat thresholdImage = thresholding(Imgcodecs.imread(imagePath), threshold);
                    ThresholdIFrame internalFrame = new ThresholdIFrame(threshold, 
                                                                        (BufferedImage) HighGui.toBufferedImage(thresholdImage));
                    desktop.add(internalFrame);
                    internalFrame.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(rootPane, "El umbral debe estar en el rango 0..255.",
                            "Error en umbral", JOptionPane.ERROR_MESSAGE);  
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(rootPane, "El umbral debe ser un entero.", "Error en umbral", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_thresholdOptionActionPerformed

    private void quitOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitOptionActionPerformed
        int confirm = JOptionPane.showConfirmDialog(rootPane, "¿Desea salir de la aplicación?", "Salir aplicación", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_quitOptionActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int confirm = JOptionPane.showConfirmDialog(rootPane, "¿Desea salir de la aplicación?", "Salir aplicación", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private Mat thresholding(Mat originalImage, Integer threshold){
        Mat greyImage = new Mat(originalImage.rows(), originalImage.cols(), CvType.CV_8U);
        Mat thresholdImage = new Mat(originalImage.rows(), originalImage.cols(), CvType.CV_8U);
        Imgproc.cvtColor(originalImage, greyImage, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(greyImage, thresholdImage, threshold, 255, Imgproc.THRESH_BINARY);
        return thresholdImage;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DesktopFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem openOption;
    private javax.swing.JMenuItem quitOption;
    private javax.swing.JMenuItem thresholdOption;
    // End of variables declaration//GEN-END:variables
    private JFileChooser fileChooser = new JFileChooser();
    private String imagePath = null;
}
