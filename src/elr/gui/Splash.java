package elr.gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;

/**
 * GUI used to look the state of the load.
 * @author Infernage
 */
public class Splash extends javax.swing.JFrame {
    private static Splash splash = null;
    
    /**
     * Creates new form Splash
     */
    public Splash() {
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setContentPane(new BackgroundSplash());
        initComponents();
    }
    
    /**
     * Initializes the Splash. Can be initialized only one time.
     */
    public static void init(){
        if (splash != null) throw new RuntimeException("Splash already created");
        splash = new Splash();
        splash.setIconImage(new ImageIcon(Splash.class
                .getResource("/elr/resources/5547.png")).getImage());
        splash.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        splash.setLocationRelativeTo(null);
        new Counter().start();
        splash.setVisible(true);
    }
    
    /**
     * Sets the progressbar a value.
     * @param value The value to set.
     */
    public static void set(int value){
        if(splash != null) splash.jProgressBar1.setValue(value);
    }
    
    /**
     * Displays an information message.
     * @param msg The message to display.
     */
    public static void displayMsg(String msg){
        if (splash != null) splash.jLabel2.setText(msg);
    }
    
    /**
     * Private class used to close the GUI automatically.
     * @author Infernage
     */
    private static class Counter extends Thread{
        @Override
        public void run(){
            while(splash.jProgressBar1.getValue() < 100){
                Thread.yield();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    //Ignore
                }
            }
            splash.setVisible(false);
        }
    }
    
    /**
     * Private class to set a background to the splash.
     * @author Infernage
     */
    private static class BackgroundSplash extends JComponent{
        private Image icon;
        
        public BackgroundSplash(){
            icon = new ImageIcon(getClass().getResource("/elr/resources/background.png")).getImage();
        }
        
        @Override
        public void paintComponent(Graphics g){
            g.drawImage(icon, 0, 0, getWidth(), getHeight(), null);
            setOpaque(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/elr/resources/escorbutonetwork.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel1)
                .addContainerGap(163, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
