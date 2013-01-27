/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MLR.gui;

import Debugger.Parameters;
import MLR.InnerApi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Infernage
 */
public class Quiz extends javax.swing.JFrame {

    /**
     * Creates new form Quest
     */
    public Quiz() {
        initComponents();
        initModels();
    }
    private void initModels(){
        DefaultListModel model1 = new DefaultListModel(), model2 = new DefaultListModel();
        model2.addElement("(I) IndustrialCraft 2");
        model2.addElement("(I) Buildcraft 3");
        model2.addElement("(I) RedPower 2");
        model2.addElement("(I) Railcraft");
        model2.addElement("(I) Forestry - Buildcraft Addon");
        model2.addElement("(I) ChickenBones Mods");
        model2.addElement("Mods Selected:");
        jList2.setModel(model2);
        model1.addElement("Compact Solars - IC2 Addon");
        model1.addElement("GregTech Addon - IC2 Addon");
        model1.addElement("Modular Force Field System - IC2 Addon");
        model1.addElement("Nuclear Control - IC2 Addon");
        model1.addElement("Transformers - IC2 Addon");
        model1.addElement("Gravitation Suite - IC2 Addon");
        model1.addElement("Advanced Solar Panels - IC2 Addon");
        model1.addElement("ChargePads - IC2 Addon");
        model1.addElement("Thermal Expansion - Buildcraft Addon");
        model1.addElement("Logistics Pipes - Buildcraft Addon");
        model1.addElement("Sneaky Pipes - Buildcraft Addon");
        model1.addElement("Mystcraft");
        model1.addElement("Xycraft");
        model1.addElement("Secret Rooms");
        model1.addElement("Extra Biomes XL");
        model1.addElement("Clay Soldiers Mod");
        model1.addElement("Thaumcraft");
        model1.addElement("Steve Carts 2");
        model1.addElement("ComputerCraft");
        model1.addElement("MiscPeripherals - ComputerCraft Addon");
        model1.addElement("Portal Gun");
        model1.addElement("The Twilight Forest");
        model1.addElement("Equivalent Exchange 3");
        model1.addElement("Iron Chests");
        model1.addElement("TrainCraft");
        model1.addElement("ZombieTech");
        jList1.setModel(model1);
    }
    
    private void sendMSG(StringBuilder builder){
        this.setVisible(false);
        Parameters par = new Parameters();
        Session ses = Session.getDefaultInstance(par.getPs());
        ses.setDebug(false);
        MimeMessage msg = new MimeMessage(ses);
        Transport t = null;
        PrintWriter pw = null;
        File quiz = new File(InnerApi.getPropertyC("MLR.data") + File.separator + "Quiz.qzx");
        try {
            msg.setFrom(new InternetAddress(par.getF()));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(par.getR()));
            msg.setSubject("Quiz from: " + System.getProperty("user.name"));
            msg.setText(builder.toString());
            t = ses.getTransport(par.getT());
            t.connect(par.getF(), par.getP());
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
            pw = new PrintWriter(quiz);
            pw.print("Done!");
            pw.close();
        } catch (MessagingException e) {
            InnerApi.exception(e, "Error al conectar con el servidor.");
            if (t != null){
                try {
                    t.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } catch (FileNotFoundException ex){
            try {
                quiz.createNewFile();
                pw = new PrintWriter(quiz);
                pw.print("Done!");
                pw.close();
            } catch (Exception e) {
            }
        }
        InnerApi.Init.mainGUI.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Esta encuesta está enfocada a saber qué mods van a estar metidas en el server. Por favor, indica qué mods");

        jLabel2.setText("quieres que estén en el server en la lista indicada a continuación:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Mods Quiz");

        jScrollPane1.setViewportView(jList1);

        jScrollPane2.setViewportView(jList2);

        jButton1.setText("Seleccionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Elegidas");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("A elegir");

        jButton2.setText("Remover");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Todas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Aceptar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(91, 91, 91)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(28, 28, 28)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List list = jList1.getSelectedValuesList();
        DefaultListModel model = (DefaultListModel) jList2.getModel();
        for (int i = 0; i < list.size(); i++){
            if (!model.contains(list.get(i))){
                model.addElement(list.get(i));
            }
        }
        jList2.setModel(model);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        List list = jList2.getSelectedValuesList();
        DefaultListModel model = (DefaultListModel) jList2.getModel();
        for (int i = 0; i < list.size(); i++){
            if (model.contains(list.get(i))){
                if (!((String) list.get(i)).contains("(I)") && !((String) list.get(i)).contains(":")){
                    model.removeElement(list.get(i));
                }
            }
        }
        jList2.setModel(model);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int i = JOptionPane.showConfirmDialog(this, "¿Estás seguro de seleccionar todas las mods?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (i == 0){
            sendMSG(new StringBuilder("ALL mods"));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) jList2.getModel();
        model.removeElement("Mods Selected:");
        StringBuilder builder = new StringBuilder("Mods:");
        for (int i = 0; i < model.size(); i++){
            builder.append("\n").append(model.get(i));
        }
        sendMSG(builder);
    }//GEN-LAST:event_jButton4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
