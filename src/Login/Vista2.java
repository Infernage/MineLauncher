package Login;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import org.jvnet.substance.SubstanceLookAndFeel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Reed
 */
public class Vista2 extends javax.swing.JFrame {
    private JPopupMenu menu;
    private LogMine logeoMC;
    private LogShafter logeoMS;
    private LogOff logeoOFF;
    //private static Systray sys;
    public static Vista2 see;
    private boolean offline = false, connect = true;
    public static boolean defaultSkin = true;;
    /**
     * Creates new form Vista2
     */
    public Vista2() {
        try{
            System.out.print("Checking this minecraft.jar... ");
            File jar = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.Dirfiles + Sources.sep() + "minecraft.jar"));
            File mc = new File(Sources.path(Sources.DirMC));
            if (!jar.exists()){
                System.out.print("FAILED\nExporting new one now... ");
                InputStream in = getClass().getResourceAsStream("/Resources/minecraft.jar");
                OutputStream out = new FileOutputStream(jar);
                byte[] buffer = new byte[1024];
                int size;
                while((size = in.read(buffer)) > 0){
                    out.write(buffer, 0, size);
                }
                in.close();
                out.close();
            }
            System.out.println("OK");
            File mine = new File(Sources.path(Sources.DirMC + Sources.sep() + "minecraft.jar"));
            System.out.print("Checking MC minecraft.jar... ");
            if (!mine.exists() && mc.exists()){
                Sources.copy(jar, mine);
            } else if (mine.exists() && mc.exists()){
                if (!Installer.Worker.check(jar, mine)){
                    mine.delete();
                    Sources.copy(jar, mine);
                }
            }
            System.out.println("OK");
        } catch (Exception ex){
            Sources.exception(ex, "Error en la exportación del minecraft.jar");
        }
        System.out.print("Setting background... ");
        //Asignamos el fondo al Panel
        setContentPane(new Background());
        System.out.println("OK");
        System.out.print("Initialiting GUI... ");
        initComponents();
        System.out.println("OK");
        jProgressBar1.setVisible(false);
        inicializar();
        this.setLocationRelativeTo(null);
        jButton7.setFocusPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton8.setFocusPainted(false);
        jButton8.setContentAreaFilled(false);
        System.out.println("Checking login files... OK");
        File rem = new File (Sources.path(Sources.DirData() + Sources.sep() + Sources.rmb)), 
                base = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirNM));
        if(!base.exists()){
            base.mkdirs();
            System.out.println("Created base file");
        }
        System.out.print("Checking server status... ");
        if (!Sources.download(base.getAbsolutePath() + Sources.sep() + Sources.lsNM, Sources.lsNM)){
            System.out.println("FAILED\nEnabling offline option...");
            System.err.println("Connection failed. Syncronization won't start!");
            connect = false;
        } else{
            System.out.println("OK");
        }
        if (connect){
            System.out.print("Synchronizing all files with the server... ");
            Mainclass.synchAllFiles();
            System.out.println("OK");
        }
        //Controlamos las pestañas de recordar
        System.out.print("Checking remember files... ");
        File te = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.login));
        if (!te.exists()){
            try {
                te.createNewFile();
            } catch (IOException ex) {
                Sources.exception(ex, "Error al recordar nombres.");
            }
        }
        if (rem.exists()){
            try{
                BufferedReader bf = new BufferedReader (new FileReader(rem));
                String temp = bf.readLine();
                String temp2 = bf.readLine();
                AES t = new AES(Sources.pss);
                bf.close();
                if (temp.equals("true")){
                    //Si se seleccionó en un principio la pestaña, se deja seleccionada y se recuerda el usuario
                    jCheckBox1.setSelected(true);
                    //Si el fichero de datos existe, se recuerda el usuario
                    bf = new BufferedReader (new FileReader (te));
                    temp = bf.readLine();
                    bf.close();
                    if (temp != null){
                        String tmp = t.decryptData(temp);
                        jTextField1.setText(tmp);
                    }
                }
                if (temp2.equals("true")){
                    //Si se seleccionó en un principio la pestaña, se deja seleccionada y se recuerda la contraseña
                    jCheckBox2.setSelected(true);
                    bf = new BufferedReader (new FileReader (te));
                    bf.readLine();
                    temp2 = bf.readLine();
                    bf.close();
                    if (temp2 != null){
                        String tmp = t.decryptData(temp2);
                        jPasswordField1.setText(tmp);
                    }
                }
            } catch (Exception ex){
                Sources.exception(ex, "Error en la lectura del fichero.");
            }
        }
        System.out.println("OK");
        //Creamos el cliente del actualizador
        try{
            System.out.print("Initialiting updater... ");
            URL url = new URL("http://minechinchas.blogspot.com/2012/09/downloads.html");
            Cliente client = new Cliente(jLabel1, jLabel5, jButton5, jButton6, url, this);
            client.start();
            Mainclass.hilos.put("Cliente", client);
            System.out.println("OK");
        } catch(Exception e){
            jLabel1.setForeground(Color.red);
            jLabel1.setText("ERROR!");
            jLabel5.setForeground(Color.red);
            jLabel5.setText(e.getMessage());
            e.printStackTrace(Mainclass.err);
        }
        jTextArea1.getPreferredScrollableViewportSize().setSize(0, 0);
        Mainclass.init.exit();
    }
    /*public void res(){
        sys.salir();
    }*/
    /**
     * This method gets the file name crypted.
     * @param txt The username given.
     * @return The file name crypted.
     */
    public static String getFile(String txt){
        char[] A = txt.toCharArray();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < A.length; i++){
            int B = (int) A[i];
            str.append(B).append("_");
        }
        str.append(".dat");
        return str.toString();
    }
    private void inicializar(){
        System.out.print("Loading changelog... ");
        CHLG text = new CHLG(jTextArea1);
        text.start();
        Mainclass.hilos.put("ChangeLog", text);
        System.out.println("OK");
    }
    /**
     * This method adds a log to a file on the server.
     * @param text The username.
     */
    private void inputLog(String text){
        text = text.toLowerCase();
        File log = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirNM + Sources.sep()
                + getFile(text + "NM")));
        Calendar C = new GregorianCalendar();
        StringBuilder str = new StringBuilder("Connected at ");
        str.append(C.get(Calendar.DAY_OF_MONTH)).append("/")
                .append(C.get(Calendar.MONTH) + 1).append("/")
                .append(C.get(Calendar.YEAR)).append(" ")
                .append(C.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(C.get(Calendar.MINUTE)).append(":")
                .append(C.get(Calendar.SECOND))
                .append(" with name of ").append(text);
        try{
            PrintWriter pw = new PrintWriter (new FileWriter(log, true));
            pw.println(str);
            pw.close();
            Mainclass.synch(log);
        } catch (IOException ex){
            Sources.exception(ex, "Error al sincronizar log con el server.");
        }
    }
    private void Play(){
        File minecraft = new File(Sources.path(Sources.DirMC + Sources.sep() + "minecraft.jar"));
        File bin = new File(Sources.path(Sources.DirMC + Sources.sep() + "bin" + Sources.sep() + "minecraft.jar"));
        if (!bin.exists() || !minecraft.exists()){
            JOptionPane.showMessageDialog(null, "No se han encontrado instalaciones de Minecraft", "Minecraft not found!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String name = jTextField1.getText().toLowerCase();
        statusConn.setForeground(Color.white);
        statusConn.setText("");
        //Método del botón ¡Jugar!
        see = this;
        System.out.println("Setting connection");
        jButton5.setEnabled(false);
        statusConn.setForeground(Color.white);
        if (!offline){
            statusConn.setText("Connecting...");
        }
        boolean remember = jCheckBox1.isSelected(), rememberP = jCheckBox2.isSelected();
        //Comprobamos si el Recordar está activo y lo recordamos para un futuro
        File rem = new File (Sources.path(Sources.DirData() + Sources.sep() + Sources.rmb));
        File login = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.login));
        try{
            if (!login.exists()){
                login.createNewFile();
            }
            PrintWriter pw = new PrintWriter(login);
            if (remember){
                pw.print(jTextField1.getText());
            }
            if (rememberP){
                pw.println();
                pw.print(new String(jPasswordField1.getPassword()));
            }
            pw.close();
        } catch (IOException ex){
            Sources.exception(ex, "No se pudo crear archivo de recordatorio de datos.");
        }
        System.out.print("Checking remember file... ");
        try{
            if (!rem.exists()){
                rem.createNewFile();
            }
            PrintWriter pw = new PrintWriter(rem);
            pw.print(remember + "\n" + rememberP);
            pw.close();
        } catch (IOException ex){
            Sources.exception(ex, "Error al crear el fichero lastlogin.");
        }
        System.out.println("OK");
        PlayButton play = new PlayButton(this, name);
        play.start();
    }
    public void visible(){
        this.setVisible(true);
    }
    public void back(){
        jButton5.setText("Try again");
        jButton5.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton6 = new javax.swing.JButton();
        statusConn = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 489));
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(174, 108, 17));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre de usuario:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password:");

        jButton1.setBackground(new java.awt.Color(53, 46, 79));
        jButton1.setForeground(new java.awt.Color(51, 255, 255));
        jButton1.setText("Recordar contraseña");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(53, 46, 79));
        jButton2.setForeground(new java.awt.Color(51, 255, 255));
        jButton2.setText("Cambiar contraseña");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 153, 0));
        jButton5.setText("¡Jugar!");
        jButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Gulim", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 0, 255));
        jButton7.setText("FAQ");
        jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Gulim", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 255, 238));
        jButton8.setText("Acerca de...");
        jButton8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jPasswordField1.setBackground(new java.awt.Color(174, 108, 17));
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Recordar usuario");
        jCheckBox1.setContentAreaFilled(false);

        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Recordar contraseña");
        jCheckBox2.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setPreferredSize(new java.awt.Dimension(38, 15));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setPreferredSize(new java.awt.Dimension(38, 15));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        jProgressBar1.setBackground(new java.awt.Color(0, 0, 0));
        jProgressBar1.setForeground(new java.awt.Color(0, 204, 0));
        jProgressBar1.setOpaque(true);
        jProgressBar1.setString("");
        jProgressBar1.setStringPainted(true);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 204, 255));
        jButton6.setText("Instalador");
        jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        statusConn.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 51, 51));
        jButton3.setText("Enviar error");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setText("Registrarse");
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jLabel4))
                        .addComponent(jLabel2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(statusConn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton4)))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 183, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(261, 261, 261)
                .addComponent(jButton9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)))
                .addGap(235, 235, 235)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(statusConn, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox2)
                                    .addComponent(jLabel4))
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton8)
                                        .addGap(1, 1, 1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton4))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            String account = JOptionPane.showInputDialog("Introduzca el nombre de la cuenta:").toLowerCase();
            if (account != null){
                System.out.print("Checking account name... ");
                File A = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirNM + Sources.sep()
                        + getFile(account + "NM")));
                String system;
                if (!A.exists()){
                    if (!Sources.download(A.getAbsolutePath(), account + "NM.dat")){
                        System.out.println("FAILED");
                        JOptionPane.showMessageDialog(null, "La cuenta solicitada no existe o ha habido un error comprobando los datos.");
                        return;
                    }
                    BufferedReader bf = new BufferedReader(new FileReader(A));
                    system = bf.readLine();
                    String temp = bf.readLine();
                    if (temp.equals("DEL")){
                        System.out.println("FAILED");
                        JOptionPane.showMessageDialog(null, "La cuenta solicitada no existe o ha habido un error comprobando los datos.");
                        bf.close();
                        A.delete();
                        return;
                    }
                    bf.close();
                }
                BufferedReader bf = new BufferedReader(new FileReader(A));
                system = bf.readLine();
                String temp = bf.readLine();
                if (temp.equals("MC") || temp.equals("MS")){
                    System.out.println("OK");
                    System.out.println(system);
                    JOptionPane.showMessageDialog(null, "Los datos de la cuenta solo pueden ser accedidos mediante "
                            + "sus respectivos sitios de minecraft o mineshafter.");
                    bf.close();
                    return;
                } else if (temp.equals("OFF")){
                    System.out.println("OK");
                    System.out.println(system);
                    System.out.print("Checking secret word... ");
                    String word = JOptionPane.showInputDialog("Introduzca la palabra secreta:");
                    if (word != null){
                        AES cry = new AES(Sources.pss);
                        bf.readLine();
                        String P = bf.readLine();
                        String W = cry.decryptData(bf.readLine());
                        if (word.equals(W)){
                            System.out.println("OK");
                            JOptionPane.showMessageDialog(null, "Su contraseña es " + cry.decryptData(P));
                        } else{
                            System.out.println("FAILED");
                            JOptionPane.showMessageDialog(null, "Palabra secreta incorrecta");
                        }
                    }
                }
                bf.close();
            }
        } catch (IOException ex){
            Sources.exception(ex, "Ocurrió un error en la lectura de datos.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
            PassConfirm change = new PassConfirm(this, true);
            change.setTitle("Cambiar contraseña");
            change.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            change.setLocationRelativeTo(null);
            change.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //Botón de FAQ que crea la ventana de FAQ
        FAQ faq = new FAQ(this, true);
        faq.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        faq.setLocationRelativeTo(null);
        faq.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //Botón de Acerca de... que crea la ventana
        Acerca A = new Acerca(this, true);
        A.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        A.setLocationRelativeTo(null);
        A.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //Botón de jugar
        Play();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        //Escáner de Intro en el usuario
        if((evt.getKeyCode() == KeyEvent.VK_ENTER) && jButton5.isEnabled()){
            Play();
        } else if (evt.getKeyCode() == KeyEvent.VK_CONTROL){
            String a = jTextField1.getText();
            if (a.equals("ADMINCLOSE")){
                System.exit(0);
            } else if (a.equals("threads")){
                Mainclass.threads();
            }
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
        //Escáner de Intro en la contraseña
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && jButton5.isEnabled()){
            Play();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        //Botón instalador
        System.out.println("Changing to installer mode...............");
        this.setVisible(false);
        see = this;
        Installer.Vista vist = new Installer.Vista();
        vist.setLocationRelativeTo(null);
        vist.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Debug de = new Debug(this, true);
        de.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        de.setLocationRelativeTo(this);
        de.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.out.println("[Openning Register file]");
        this.setVisible(false);
        Vista vis = new Vista(Sources.path(Sources.DirData() + Sources.sep()), Sources.path(Sources.DirData() 
                + Sources.sep() + Sources.bool), true);
        vis.setNew(this);
        vis.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON3){
            if (menu == null){
                menu = new JPopupMenu();
                ActionListener black = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (!Vista2.defaultSkin) return;
                        Vista2.defaultSkin = false;
                        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenGraphiteGlassSkin");
                    }
                };
                ActionListener white = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (Vista2.defaultSkin) return;
                        Vista2.defaultSkin = true;
                        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.NebulaBrickWallSkin");
                    }
                };
                JMenuItem iBlack = new JMenuItem("Negro");
                iBlack.addActionListener(black);
                JMenuItem iWhite = new JMenuItem("Blanco");
                iWhite.addActionListener(white);
                menu.add(iBlack);
                menu.add(iWhite);
            }
            menu.setLocation(evt.getXOnScreen(), evt.getYOnScreen());
            menu.setInvoker(menu);
            menu.setVisible(true);
        }
    }//GEN-LAST:event_formMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(Vista2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Vista2().setVisible(true);
            }
        });
    }
    private class PlayButton extends Thread{
        private Vista2 gui;
        private String name;
        public PlayButton(Vista2 V, String name){
            gui = V;
            this.name = name;
        }
        private void initialite(){
            if (offline){
                System.out.println("Setting parameter: OFFLINE");
                inputLog(jTextField1.getText());
                logeoOFF = new LogOff(jTextField1.getText(), see, statusConn);
                logeoOFF.start();
                return;
            }
            try{
                File tmp = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirNM + Sources.sep()
                        + getFile(name + "NM")));
                BufferedReader bf = null;
                String temp = null;
                if (!tmp.exists()){
                    System.out.println("Local file not found! Looking for existing file at the server...");
                    if (!Sources.download(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirNM
                        + Sources.sep() + Sources.DirTMP + Sources.sep()
                        + name + "NM.dat"), name
                        + "NM.dat")){
                            System.out.println("Error downloading the file!");
                        try{
                                bf = new BufferedReader (new InputStreamReader(new URL("ftp://minechinchas_zxq:MC-1597328460@minechinchas.zxq.net/Base/"
                                    + name + "NM.dat;type=i").openConnection().getInputStream()));
                                System.out.println(bf.readLine());
                                temp = bf.readLine();
                        } catch (Exception ex){
                            if (ex.toString().contains("FileNotFound")){
                                statusConn.setText("");
                                JOptionPane.showMessageDialog(null, "La cuenta no existe.");
                                if (bf != null){
                                    bf.close();
                                }
                                back();
                                return;
                            } else{
                                statusConn.setText("");
                                Sources.exception(ex, "No se ha podido conectar con el servidor.");
                                jButton5.setText("Jugar offline");
                                offline = true;
                                if (bf != null){
                                    bf.close();
                                }
                                return;
                            }
                        }
                    } else{
                        File A = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirNM
                            + Sources.sep() + Sources.DirTMP + Sources.sep()
                            + name + "NM.dat"));
                        File B = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirNM
                            + Sources.sep() + Sources.DirTMP + Sources.sep()
                            + getFile(name + "NM")));
                        A.renameTo(B);
                        A = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.DirNM
                            + Sources.sep() + getFile(name + "NM")));
                        Sources.copy(B, A);
                        B.delete();
                        A = null;
                        B = null;
                        System.gc();
                        try{
                            bf = new BufferedReader(new FileReader(tmp));
                            System.out.println(bf.readLine());
                            temp = bf.readLine();
                        } catch (IOException ex){
                            Sources.exception(ex, "Error al comparar datos.");
                            if (bf != null){
                                bf.close();
                            }
                            return;
                        }
                    }
                } else{
                    try{
                        bf = new BufferedReader(new FileReader(tmp));
                        System.out.println(bf.readLine());
                        temp = bf.readLine();
                    } catch (IOException ex){
                        Sources.exception(ex, "Error al comparar datos.");
                        if (bf != null){
                            bf.close();
                        }
                        return;
                    }
                }
                if (temp.equals("OFF")){
                    String temp1 = bf.readLine(), temp2 = bf.readLine();
                    boolean open = false;
                    AES cry = new AES(Sources.pss);
                    String A = cry.decryptData(temp1);
                    String B = cry.decryptData(temp2);
                    if (A.equals(jTextField1.getText()) && B.equals(new String(jPasswordField1.getPassword()))){
                        open = true;
                    }
                    if (open){
                        if (bf != null){
                            bf.close();
                        }
                        inputLog(A);
                        logeoOFF = new LogOff(A, see, statusConn);
                        logeoOFF.start();
                    } else{
                        statusConn.setForeground(Color.red);
                        statusConn.setText("Login failed!");
                        back();
                    }
                } else if (temp.equals("MC")){
                    if (logeoMC != null){
                        offline = logeoMC.offline;
                        if (offline){
                            initialite();
                            return;
                        }
                    }
                    logeoMC = new LogMine(jTextField1.getText(), new String(jPasswordField1.getPassword()),
                            statusConn, jButton5, gui);
                    logeoMC.start();
                    Mainclass.hilos.put("LogMine", logeoMC);
                    inputLog(jTextField1.getText());
                } else if (temp.equals("MS")){
                    logeoMS = new LogShafter(jTextField1.getText(), new String(jPasswordField1.getPassword()));
                    logeoMS.start();
                    Mainclass.hilos.put("LogShafter", logeoMC);
                    inputLog(jTextField1.getText());
                } else if (temp.equals("DEL")){
                    JOptionPane.showMessageDialog(null, "La cuenta no existe.");
                    back();
                }
                if (bf != null){
                    bf.close();
                }
            } catch (Exception ex){
                Sources.fatalException(ex, "Error al comprobar los datos.", 2);
            }
        }
        @Override
        public void run(){
            initialite();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    public static javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel statusConn;
    // End of variables declaration//GEN-END:variables
}
