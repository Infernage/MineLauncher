package elr.gui;

import com.google.gson.Gson;
import elr.core.Loader;
import elr.core.util.Directory;
import elr.core.util.MessageControl;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * GUI used to report an error.
 * @author Infernage
 */
public class Debug extends javax.swing.JDialog{
    private static final String TICKET_URL = "http://www.minechinchas.bugs3.com/error_ticket";
    private final String end = "-----------------------------\n\n";
    private StringBuilder builder;
    
    /**
     * Creates new form Debug.
     */
    public Debug(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        builder = new StringBuilder("JVM properties and PC info from user ")
                .append(System.getProperty("user.name")).append("\n\n");
        for (Object obj : System.getProperties().keySet()){
            builder.append("Key: ").append(obj).append(" Value: ").append(System.getProperty((String) obj)).append("\n");
        }
        info.setText(builder.toString() + end);
    }
    
    private String libraryPost(URL url, Object obj) throws IOException, URISyntaxException{
        String json = new Gson().toJson(obj);
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost(url.toURI());
            List<BasicNameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("json", json));
            UrlEncodedFormEntity params = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(params);
            HttpResponse response = client.execute(post);
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()))){
                String lane = reader.readLine();
                while(lane != null && !lane.equals("ENDTICKET")){
                    builder.append(lane).append('\n');
                    lane = reader.readLine();
                }
            }
        } finally{
            client.getConnectionManager().shutdown();
        }
        return builder.toString();
    }
    
    private String no_libraryPost(URL url, Object obj, String content) throws IOException{
        String json = new Gson().toJson(obj);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", content);
        con.setRequestProperty("charset", "UTF-8");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        byte[] parameters = json.getBytes("UTF-8");
        try (DataOutputStream writer = new DataOutputStream(con.getOutputStream())) {
            writer.write(parameters);
            writer.flush();
        }
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))){
            String lane = reader.readLine();
            while(lane != null && !lane.equals("ENDTICKET")){
                builder.append(lane).append('\n');
                lane = reader.readLine();
            }
        }
        return builder.toString();
    }
    
    private class DebugClass{
        private String nickname;
        private String information;
        private String description;
        private List<elr.modules.console.Error> errors;
        
        public DebugClass(String user, String info, String desc, List<elr.modules.console.Error> list){
            nickname = user;
            description = desc;
            information = info;
            errors = list;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        info = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nickname = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Error description");

        description.setColumns(20);
        description.setLineWrap(true);
        description.setRows(5);
        description.setWrapStyleWord(true);
        description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descriptionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(description);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        info.setEditable(false);
        info.setColumns(20);
        info.setLineWrap(true);
        info.setRows(5);
        info.setWrapStyleWord(true);
        jScrollPane2.setViewportView(info);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Information to send");

        jLabel1.setText("Input your nickname:");

        nickname.setText("Optional");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(217, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(217, 217, 217))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nickname))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<elr.modules.console.Error> errorList = Loader.getMainGui().getConsoleTab().getErrorList();
        String name = nickname.getText().equals("Optional") || nickname.getText().equals("") ? 
                System.getProperty("user.name") : nickname.getText();
        String information = builder.toString();
        DebugClass ticket = new DebugClass(name, information, description.getText(), errorList);
        try {
            String response;
            try {
                response = libraryPost(new URL(TICKET_URL), ticket);
            } catch (Exception e) {
                Loader.getMainGui().getConsoleTab().printErr(e, "LibraryPost method failed", 2, 
                        this.getClass());
                response = no_libraryPost(new URL(TICKET_URL),
                        ticket, "application/json");
            }
            MessageControl.showInfoMessage(response, "Server response");
            jButton1.setText("Sent");
            jButton1.setEnabled(false);
        } catch (Exception e) {
            MessageControl.showExceptionMessage(2, e, "The message couldn't be sent."
                    + "\nDumping error into a file...");
            Calendar C = new GregorianCalendar();
            File dump = new File(Directory.currentPath(), "EscorbutoLauncher_Error_" + C.get(Calendar.YEAR)
                    + "-" + C.get(Calendar.MONTH) + "-" + C.get(Calendar.DAY_OF_MONTH) + "_" + 
                    C.get(Calendar.HOUR_OF_DAY) + "-" + C.get(Calendar.MINUTE) + "-" + 
                    C.get(Calendar.SECOND) + ".elrdump");
            try {
                if (!dump.exists()) dump.createNewFile();
                try (PrintWriter pw = new PrintWriter(dump)) {
                    pw.print(new Gson().toJson(ticket));
                }
                MessageControl.showInfoMessage("Error file has been saved in the current launcher path", 
                        null);
            } catch (Exception ex) {
                //Well... we attempted.
                MessageControl.showExceptionMessage(2, ex, "Failed to dump it");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void descriptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() != KeyEvent.VK_BACK_SPACE && evt.getKeyChar() != KeyEvent.VK_ESCAPE){
            info.append(evt.getKeyChar() + "");
        } else if (evt.getKeyChar() == KeyEvent.VK_ENTER){
            info.append("\n");
        } else {
            if (description.getText().length() != 0){
                info.setText(info.getText().substring(0, info.getText().length() - 1));
            } else{
                info.setText(builder.toString() + end);
            }
        }
    }//GEN-LAST:event_descriptionKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea description;
    private javax.swing.JTextArea info;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nickname;
    // End of variables declaration//GEN-END:variables
}
