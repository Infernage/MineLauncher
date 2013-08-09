package elr.gui;

import elr.core.interfaces.Listener;
import elr.core.interfaces.Notifier;
import elr.gui.tabs.*;
import elr.modules.threadsystem.DownloadJob;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author Infernage
 */
public class MainGui extends javax.swing.JPanel implements Notifier{
    private final JFrame frame;
    private final Changelog changeTab;
    private final Console consoleTab;
    private final MinecraftPage pageTab;
    private final Options optionsTab;
    private final Profiler profileTab;
    private List<DownloadJob> jobList = Collections.synchronizedList(new ArrayList<DownloadJob>());
    private boolean ready = true;
    private List<Listener> listeners = new ArrayList<>();
    private Image icon = new ImageIcon(getClass().getResource("/elr/resources/background1.png"))
            .getImage();
    private volatile int selectedIndex = 0;

    /**
     * Creates new form MainGui
     */
    public MainGui(JFrame f) {
        frame = f;
        initComponents();
        statusLabel.setText("");
        jProgressBar1.setVisible(false);
        pageTab = new MinecraftPage(f);
        changeTab = new Changelog(f);
        consoleTab = new Console(f);
        optionsTab = new Options(f);
        profileTab = new Profiler(f);
        listeners.add(optionsTab);
        listeners.add(profileTab);
        tabPanel.removeAll();
        tabPanel.add("Changelog", changeTab);
        tabPanel.add("MC Blog", pageTab);
        tabPanel.add("Profiler", profileTab);
        tabPanel.add("Console", consoleTab);
        tabPanel.add("Options", optionsTab);
        tabPanel.setSelectedIndex(0);
        setOpaque(false);
    }
    
    public Changelog getChangelogTab(){ return changeTab; }
    
    public Console getConsoleTab(){ return consoleTab; }
    
    public Options getOptionsTab(){ return optionsTab; }
    
    public Profiler getProfilerTab(){ return profileTab; }
    
    public JProgressBar getProgressBar(){ return jProgressBar1; }
    
    public JFrame getParentFrame(){ return frame; }
    
    public void setSelectedTab(int index){ tabPanel.setSelectedIndex(index); }
    
    public synchronized void addJob(DownloadJob job){
        if (jobList.contains(job)) return;
        jobList.add(job);
    }
    
    public synchronized boolean isReady(){
        return ready;
    }
    
    public synchronized void getReady(String msg){
        ready = false;
        statusLabel.setText(msg);
        jProgressBar1.setVisible(true);
    }
    
    public synchronized void endReady(DownloadJob job){
        jobList.remove(job);
        statusLabel.setText("");
        jProgressBar1.setVisible(false);
        jProgressBar1.setValue(0);
        jProgressBar1.setString("");
        ready = true;
    }
    
    public synchronized boolean hasMoreJobs(){
        for (DownloadJob job : jobList) {
            if (!job.isDone()) return true;
        }
        return false;
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(icon, 0, 0, null);
        super.paint(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jProgressBar1 = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(636, 344));

        tabPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPanelMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/elr/resources/escorbutonetwork.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jProgressBar1.setBackground(new java.awt.Color(0, 0, 0));
        jProgressBar1.setForeground(new java.awt.Color(0, 204, 0));
        jProgressBar1.setOpaque(true);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 17));
        jProgressBar1.setString("");
        jProgressBar1.setStringPainted(true);

        statusLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Initializing launcher");
        statusLabel.setMaximumSize(new java.awt.Dimension(177, 15));
        statusLabel.setMinimumSize(new java.awt.Dimension(177, 15));
        statusLabel.setPreferredSize(new java.awt.Dimension(177, 15));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(tabPanel)
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPanelMouseClicked
        // TODO add your handling code here:
        if (selectedIndex == tabPanel.getSelectedIndex()) return;
        for (Listener listener : listeners) {
            listener.changeTab();
        }
    }//GEN-LAST:event_tabPanelMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        About form = new About(frame, false);
        form.setLocationRelativeTo(frame);
        form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        form.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTabbedPane tabPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void notifyListeners() {
        for (Listener listener : listeners) {
            listener.changeEvent();
        }
    }
}
