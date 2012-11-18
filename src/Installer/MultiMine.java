/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Installer;

import Login.Mainclass;
import Login.Sources;
import Login.Updater;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Reed
 */
public class MultiMine extends javax.swing.JDialog {
    private JFrame vis;
    private String SP = "SSP.txt", MP = "SMP.txt";
    private TextThread text;
    private Awake initialite;
    /**
     * Creates new form MultiMine
     */
    public MultiMine(JFrame parent, boolean modal) {
        super(parent, modal);
        vis = parent;
        initComponents();
        System.out.print("Initialiting... ");
        initModel();
        System.out.println("OK");
        checkMC();
    }
    private void initModel(){
        DefaultListModel modelo = new DefaultListModel();
        modelo.addElement("SinglePlayer");
        modelo.addElement("MultiPlayer");
        modelo.addElement("------------------");
        jList1.setModel(modelo);
    }
    /**
     * Method to add an instance
     * @param file The directory of the instance
     * @param msg The title
     */
    public void addInstance(String msg){
        File dst = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance + Sources.sep()
                + msg));
        if (!dst.exists()){
            dst.mkdirs();
        } else{
            if (dst.listFiles().length > 1){
                if (msg.contains("_")){
                    String[] tmp = msg.split("_");
                    int i = Integer.parseInt(tmp[1]);
                    i++;
                    addInstance(tmp[0] + "_" + i);
                } else{
                    addInstance("Default_1");
                }
                return;
            }
        }
        try {
            File fich = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance
                    + Sources.sep() + msg + Sources.sep() + Sources.infoInst));
            fich.createNewFile();
        } catch (IOException ex) {
            Sources.exception(ex, "No se pudo añadir la instancia.");
        }
    }
    public int checkInstance(File[] mcs){
        int res = 0, i = 0;
        while(i < mcs.length){
            if (mcs[i].listFiles().length == 1){
                res = i;
                i = mcs.length;
            } else{
                i++;
            }
        }
        return res;
    }
    public void checkMC(){
        System.out.print("Checking instances... ");
        String tmp = Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance);
        File[] mcs = new File(tmp).listFiles();
        File mc = new File(Sources.path(Sources.DirMC));
        if (!mc.exists() || (mc.listFiles().length < 7)){
            JOptionPane.showMessageDialog(null, "No se han encontrado instalaciones de Minecraft.", "Not found", JOptionPane.WARNING_MESSAGE);
        } else{
            int i = 0;
            boolean temp = false;
            while (i < mcs.length){
                if (mcs[i].listFiles().length == 1){
                    temp = true;
                    i = mcs.length;
                }
                i++;
            }
            if ((mcs.length == 0) || !temp){
                addInstance("Default");
                mcs = new File(tmp).listFiles();
            }
        }
        System.out.println("OK");
        DefaultListModel modelo = (DefaultListModel) jList1.getModel();
        for (int i = 0; i < mcs.length; i++){
            String title = mcs[i].getName();
            modelo.addElement(title);
        }
        jList1.setModel(modelo);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        instalarB = new javax.swing.JButton();
        modificarB = new javax.swing.JButton();
        ejecutarB = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "SinglePlayer", "MultiPlayer", "------------------" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Informarción");

        instalarB.setText("Instalar");
        instalarB.setEnabled(false);
        instalarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instalarBActionPerformed(evt);
            }
        });

        modificarB.setText("Modificar info");
        modificarB.setEnabled(false);
        modificarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBActionPerformed(evt);
            }
        });

        ejecutarB.setText("Ejecutar");
        ejecutarB.setEnabled(false);
        ejecutarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarBActionPerformed(evt);
            }
        });

        jProgressBar1.setOpaque(true);
        jProgressBar1.setString("");
        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(instalarB)
                        .addGap(22, 22, 22)
                        .addComponent(modificarB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(ejecutarB)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(instalarB)
                            .addComponent(modificarB)
                            .addComponent(ejecutarB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        String element = (String) jList1.getSelectedValue();
        if (element == null){
            return;
        }
        if (evt.getClickCount() == 2){
            String newTitle = JOptionPane.showInputDialog("Introduce el nuevo título:");
            DefaultListModel model = (DefaultListModel) jList1.getModel();
            int i = 0, cont = 0;
            while(i < model.size()){
                if (element.equals(model.get(i))){
                    cont = i;
                    i = model.size();
                }
                i++;
            }
            File A = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance + Sources.sep()
                    + element));
            File B = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance
                    + Sources.sep() + newTitle));
            A.renameTo(B);
            model.set(cont, newTitle);
            jList1.setModel(model);
            return;
        }
        jTextArea1.setEditable(false);
        jTextArea1.setText("");
        modificarB.setText("Modificar info");
        if (!element.equals("------------------")){
            text = new TextThread("InfoInstances");
            text.element(element);
            text.start();
            jTextArea1.getPreferredScrollableViewportSize().setSize(0, 0);
            if (element.equals("SinglePlayer")){
                modificarB.setEnabled(false);
                instalarB.setEnabled(true);
                ejecutarB.setEnabled(false);
            } else if (element.equals("MultiPlayer")){
                modificarB.setEnabled(false);
                instalarB.setEnabled(true);
                ejecutarB.setEnabled(false);
            } else{
                modificarB.setEnabled(true);
                instalarB.setEnabled(false);
                ejecutarB.setEnabled(true);
                File info = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance
                        + Sources.sep() + element + Sources.sep() + Sources.infoInst));
                if (info.exists()){
                    try {
                        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(
                                info), "iso-8859-1"));
                        String tmp;
                        jTextArea1.setText("");
                        while((tmp = bf.readLine()) != null){
                            jTextArea1.append(tmp);
                        }
                        bf.close();
                    } catch (Exception ex) {
                        Sources.exception(ex, "Fallo al leer la configuración.");
                    }
                }
            }
        } else{
            jTextArea1.setText("");
            modificarB.setEnabled(false);
            instalarB.setEnabled(false);
            ejecutarB.setEnabled(false);
        }
        System.gc();
    }//GEN-LAST:event_jList1MouseClicked

    private void modificarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBActionPerformed
        // TODO add your handling code here:
        if (modificarB.getText().contains("info")){
            jTextArea1.setEditable(true);
            modificarB.setText("Terminar");
        } else{
            try{
                File info = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance
                        + Sources.sep() + (String) jList1.getSelectedValue() + Sources.sep() + Sources.infoInst));
                if (!info.exists()){
                    info.createNewFile();
                }
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(info), "iso-8859-1"));
                pw.print(jTextArea1.getText());
                pw.close();
                jTextArea1.setEditable(false);
                modificarB.setText("Modificar info");
            } catch (IOException ex){
                Sources.exception(ex, "Error al guardar la descripción.");
            }
        }
    }//GEN-LAST:event_modificarBActionPerformed

    private void ejecutarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarBActionPerformed
        // TODO add your handling code here:
        File[] mcs = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance)).listFiles();
        File mc = new File(Sources.path(Sources.DirMC));
        int i = checkInstance(mcs);
        File src = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance
                    + Sources.sep() + (String) jList1.getSelectedValue() + Sources.sep() + Sources.DirMC));
        if (mcs[i].getName().equals((String) jList1.getSelectedValue())){
            this.setVisible(false);
            vis.dispose();
            this.dispose();
            Login.Vista2.see.setVisible(true);
            return;
        }
        File dst = new File(mcs[i].getAbsolutePath() + Sources.sep() + Sources.DirMC);
        if (!dst.exists()){
            dst.mkdirs();
        }
        try {
            Sources.copyDirectory(mc, dst);
            Sources.borrarFichero(mc);
            Sources.copyDirectory(src, mc);
            Sources.borrarFichero(src);
            src.delete();
            this.setVisible(false);
            vis.dispose();
            this.dispose();
            Login.Vista2.see.setVisible(true);
        } catch (IOException ex) {
            Sources.exception(ex, "No se pudo iniciar la instancia.");
        }
    }//GEN-LAST:event_ejecutarBActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Mainclass.hilos.get("MultiMine").interrupt();
    }//GEN-LAST:event_formWindowClosing

    private void instalarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instalarBActionPerformed
        // TODO add your handling code here:
        String local = Sources.path(Sources.DirData() + Sources.sep());
        if (((String)jList1.getSelectedValue()).equals("SinglePlayer")){
            if (!Sources.downloadMC(local + SP, SP)){
                Sources.exception(new Exception("Error connection"), "No se ha podido conectar con el servidor.");
            } else{
                File tmp = new File(local + SP);
                String host = null;
                BufferedReader bf = null;
                try {
                    bf = new BufferedReader(new FileReader(tmp));
                    if (bf != null){
                        host = bf.readLine();
                        bf.close();
                    }
                } catch (Exception ex) {
                    Sources.exception(ex, "Error leyendo los datos.");
                    try {
                        bf.close();
                    } catch (IOException ex1) {
                    }
                }
                if (!tmp.delete()){
                    tmp.deleteOnExit();
                }
                jList1.setEnabled(false);
                jTextArea1.setEnabled(false);
                instalarB.setEnabled(false);
                ejecutarB.setEnabled(false);
                modificarB.setEnabled(false);
                File mc = new File(Sources.path(Sources.DirMC));
                if (mc.exists() && (mc.listFiles().length > 4)){
                    File[] mcs = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance)).listFiles();
                    int i = checkInstance(mcs);
                    File instance = new File(mcs[i].getAbsolutePath() + Sources.sep() + Sources.DirMC);
                    if (!instance.exists()){
                        instance.mkdirs();
                    }
                    try{
                        Sources.copyDirectory(mc, instance);
                        Sources.borrarFichero(mc);
                        mc.delete();
                    } catch (IOException ex){
                        Sources.exception(ex, "No se pudo cambiar la instancia.");
                    }
                } else if (mc.exists()){
                    mc.delete();
                }
                Updater update = new Updater(host, jProgressBar1);
                initialite = new Awake(update);
                update.start();
                initialite.start();
                Login.Mainclass.hilos.put("Updater", update);
            }
        } else if (((String)jList1.getSelectedValue()).equals("MultiPlayer")){
            if (!Sources.downloadMC(local + MP, MP)){
                Sources.exception(new Exception("Error connection"), "No se ha podido conectar con el servidor.");
            } else{
                File tmp = new File(local + MP);
                String host = null;
                BufferedReader bf = null;
                try {
                    bf = new BufferedReader(new FileReader(tmp));
                    if (bf != null){
                        host = bf.readLine();
                        bf.close();
                    }
                } catch (Exception ex) {
                    Sources.exception(ex, "Error leyendo los datos.");
                    try {
                        bf.close();
                    } catch (IOException ex1) {
                    }
                }
                if (!tmp.delete()){
                    tmp.deleteOnExit();
                }
                jList1.setEnabled(false);
                jTextArea1.setEnabled(false);
                instalarB.setEnabled(false);
                ejecutarB.setEnabled(false);
                modificarB.setEnabled(false);
                File mc = new File(Sources.path(Sources.DirMC));
                if (mc.exists() && (mc.listFiles().length > 4)){
                    File[] mcs = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirInstance)).listFiles();
                    int i = checkInstance(mcs);
                    File instance = new File(mcs[i].getAbsolutePath() + Sources.sep() + Sources.DirMC);
                    if (!instance.exists()){
                        instance.mkdirs();
                    }
                    try{
                        Sources.copyDirectory(mc, instance);
                        Sources.borrarFichero(mc);
                        mc.delete();
                    } catch (IOException ex){
                        Sources.exception(ex, "No se pudo cambiar la instancia.");
                    }
                } else if (mc.exists()){
                    mc.delete();
                }
                Updater update = new Updater(host, jProgressBar1);
                initialite = new Awake(update);
                update.start();
                initialite.start();
                Login.Mainclass.hilos.put("Updater", update);
            }
        }
    }//GEN-LAST:event_instalarBActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MultiMine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MultiMine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MultiMine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MultiMine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MultiMine dialog = new MultiMine(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    private class TextThread extends Thread{
        private String object;
        public TextThread(String title){
            super(title);
        }
        public void element(String element){
            object = element;
        }
        @Override
        public void run(){
            if (object.equals("SinglePlayer")){
                jTextArea1.setText("Instancia de Minecraft versión 1.4.4\nEsta instancia tiene los componentes"
                    + " pertenecientes al uso individual.\n\nLas mods incluidas en este pack son:\n");
            } else if (object.equals("MultiPlayer")){
                jTextArea1.setText("Instancia de Minecraft versión 1.2.5\nEsta instancia tiene los componentes"
                    + " pertenecientes al uso de multijugador.\nLas mods incluidas aquí están también"
                    + " en el servidor de juego.\nMods incluidas:\n\nAdditional Pipes 3.2.0.3\n"
                    + "Advanced solar panel 3.0.0\nBalkons Weapon Mod 8.6\nBuildcraft 3.1.6.25\n"
                    + "Computer Craft 1.4.1\nDungeon Pack v6\nEnder storage 1.1.3\nForestry 1.4.8.4\n"
                    + "IC2 1.103\nIC2Nuclear control 1.1.11\nIron chest 3.8.0.40\nLogistic pipes 0.4.5.67\n"
                    + "Modular Force Field System  2.0.6.1.0\nMystcraft 0.9.1.02\nNot Enough Items 1.2.2.4\n"
                    + "PortalGun 1.2.5v3\nRailcraft 5.4.7\nRedpower 2.0p5b2\nRei's Minimap\n"
                    + "Secret Rooms 4.0.3\nTrains mod 2.1.2b\n"
                    + "Wireless redstone (Addons 1.2.2.3 - Core 1.2.2.3 - Redpower 1.2.2.1)");
            }
        }
    }
    private class Awake extends Thread{
        private Updater updater;
        public Awake(Updater update){
            updater = update;
        }
        @Override
        public void run(){
            while(updater.isAlive()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    
                }
            }
            jList1.setEnabled(true);
            jTextArea1.setEnabled(true);
            instalarB.setEnabled(true);
            ejecutarB.setEnabled(true);
            modificarB.setEnabled(true);
            jProgressBar1.setValue(0);
            jProgressBar1.setString("");
            initModel();
            checkMC();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ejecutarB;
    private javax.swing.JButton instalarB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton modificarB;
    // End of variables declaration//GEN-END:variables
}
