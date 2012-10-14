package Login;



import java.io.*;
import javax.swing.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Reed
 */
public class Vista extends javax.swing.JFrame {
    private File fichero;
    private File error;
    private String pass;
    private File booleano;
    /**
     * Creates new form Vista
     */
    public Vista(String fich, String pss, String boo) {
        initComponents();
        Mainclass.init.exit();
        fichero = new File(fich);
        error = new File (fich.replaceFirst("data.cfg", "LogEr.txt"));
        pass = pss;
        booleano = new File (boo);
        this.setTitle("Registro Minecraft 1.2.5");
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
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jSeparator3 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Bienvenido!");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("¿Es tu primera vez y no sabes cómo ponerte el nombre?");

        jButton1.setText("Exacto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Registro local");

        jLabel4.setText("Introduce tu nombre de cuenta:");

        jButton2.setText("Aceptar");
        jButton2.setPreferredSize(new java.awt.Dimension(72, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Introduce tu contraseña:");

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 204, 0));
        jButton3.setText("Tengo cuenta de Minecraft");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setPreferredSize(new java.awt.Dimension(184, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 204));
        jButton4.setText("Tengo Mineshafter");
        jButton4.setPreferredSize(new java.awt.Dimension(140, 23));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setText("Palabra secreta:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel7.setText("(3 caracteres mínimo)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1)
                                    .addComponent(jPasswordField1))
                                .addGap(10, 10, 10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField2)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(152, 152, 152))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(147, 147, 147))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Pequeña guía rápida de registro
        Ventanita ventanita = new Ventanita(this, true);
        ventanita.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ventanita.setVisible(true);
        ventanita.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Botón de registro, se inicializan las variables @exito controla si se registra o no
        boolean exito = true;
        StringECP crypt = new StringECP(pass);
        String encrypted = null;
        String encryptedPass = null;
        String secretW = null;
        //Se controla si todos los campos están bien rellenados
        if (jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debes meter un nombre de usuario.");
            exito = false;
        } else{
            encrypted = crypt.encrypt(jTextField1.getText());
        }
        if (new String (jPasswordField1.getPassword()).equals("")){
            JOptionPane.showMessageDialog(null, "Debes introducir una contraseña.");
            exito = false;
        } else{
            String te = new String (jPasswordField1.getPassword());
            if (te.length() < 3){
                JOptionPane.showMessageDialog(null, "La contraseña es demasiado corta.");
                exito = false;
            } else{
                encryptedPass = crypt.encrypt(new String (jPasswordField1.getPassword()));
            }
        }
        if (jTextField2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debes meter una palabra secreta.");
            exito = false;
        } else{
            secretW = crypt.encrypt(jTextField2.getText());
        }
        //Si todo está correcto, @exito = true
        if (exito){
           try{
               //Creamos el fichero con los datos del registro
            fichero.createNewFile();
            PrintWriter pw = new PrintWriter (fichero);
            pw.print(encrypted);
            pw.println();
            pw.print(encryptedPass);
            pw.println();
            pw.print(secretW);
            pw.close();
            this.setVisible(false);
            //Escribimos en el fichero booleano la palabra true para indicar que el registro ha sido exitoso
            PrintWriter pw1 = new PrintWriter (booleano);
            pw1.print("true");
            pw1.close();
            //Abrimos Vista2
            Vista2 ven = new Vista2(pass);
            ven.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ven.pack();
            ven.setLocationRelativeTo(null);
            ven.setTitle(Mainclass.title + " " + Mainclass.version);
            ven.setVisible(true);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error en el registro");
            e.printStackTrace(Mainclass.err);
            System.exit(0);
        }
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        /*Botón para indicar que se tiene cuenta de Minecraft Oficial
         */
        int i = JOptionPane.showConfirmDialog(null, "Con esto saltarás el registro y pasarás al login.\n¿Estás seguro de continuar?");
        if (i == 0){
            try {
                //Creamos el fichero de datos vacío
                fichero.createNewFile();
                //Creamos el fichero para saber que la cuenta es Oficial
                File MinecraftUser = null;
                if (Mainclass.OS.equals("windows")){
                    MinecraftUser = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Data\\MOS.bon");
                } else if (Mainclass.OS.equals("linux")){
                    MinecraftUser = new File(System.getProperty("user.home") + "/.Data/MOS.bon");
                }
                MinecraftUser.createNewFile();
                PrintWriter p = new PrintWriter(MinecraftUser);
                p.print("said_/&/JT&^*$/&(/*Ç_said");
                p.close();
                //Escribimos en el fichero booleano true, indicando que no hay que hacer registro
                PrintWriter pw = new PrintWriter(this.booleano);
                pw.print("true");
                pw.close();
                //Abrimos Vista2
                Vista2 ven = new Vista2(pass);
                ven.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ven.pack();
                ven.setLocationRelativeTo(null);
                ven.setTitle(Mainclass.title + " " + Mainclass.version);
                ven.setVisible(true);
            } catch (IOException e){
                e.printStackTrace(Mainclass.err);
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //Lo mismo que el ActionPerformed anterior, pero con Mineshafter
        int i = JOptionPane.showConfirmDialog(null, "Con esto saltarás el registro y pasarás al login.\n¿Estás seguro de continuar?");
        if (i == 0){
            try {
                //Creamos el fichero de datos vacío
                fichero.createNewFile();
                //Creamos el fichero para saber que la cuenta es Oficial
                File MineshafterUser = null;
                if (Mainclass.OS.equals("windows")){
                    MineshafterUser = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Data\\MSOS.bon");
                } else if (Mainclass.OS.equals("linux")){
                    MineshafterUser = new File(System.getProperty("user.home") + "/.Data/MSOS.bon");
                }
                MineshafterUser.createNewFile();
                PrintWriter p = new PrintWriter(MineshafterUser);
                p.print("said_/HT&)$^)/%(¨¨Ç_said");
                p.close();
                //Escribimos en el fichero booleano true, indicando que no hay que hacer registro
                PrintWriter pw = new PrintWriter(this.booleano);
                pw.print("true");
                pw.close();
                //Abrimos Vista2
                Vista2 ven = new Vista2(pass);
                ven.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ven.pack();
                ven.setLocationRelativeTo(null);
                ven.setTitle(Mainclass.title + " " + Mainclass.version);
                ven.setVisible(true);
            } catch (IOException e){
                e.printStackTrace(Mainclass.err);
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(final String args, final String pass, final String boo) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Vista(args, pass, boo).setVisible(true);
            }
        });
    }
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
