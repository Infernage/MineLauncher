package Login;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class RAM extends JDialog{
    private JButton jButton1;
    private JLabel jLabel1;
    private JList jList1;
    private JScrollPane jScrollPane1;
    private boolean exit = false;
    
    public RAM(Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                exit = true;
            }
        });
    }
    public int devolver() {
        int i = jList1.getSelectedIndex();
        if (exit){
            i = -1;
        }
        return i;
    }
    
    private void initComponents(){
        this.jLabel1 = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.jList1 = new JList();
        this.jButton1 = new JButton();
        
        setDefaultCloseOperation(2);
        
        this.jLabel1.setFont(new Font("Tahoma", 1, 14));
        this.jLabel1.setText("Memoria RAM a asignar a Minecraft.");
        
        this.jList1.setModel(new AbstractListModel() {
            String[] strings = { "512Mb RAM", "1Gb RAM", "2Gb RAM (Recomendado)", "4Gb RAM" };
            
            public int getSize() { return this.strings.length; } 
            public Object getElementAt(int i) { return this.strings[i];
            }
        });
        this.jList1.setSelectionMode(0);
        this.jScrollPane1.setViewportView(this.jList1);
        
        this.jButton1.setBackground(new Color(0, 204, 204));
        this.jButton1.setText("Ejecutar");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RAM.this.jButton1ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1)).addGroup(layout.createSequentialGroup().addGap(96, 96, 96).addComponent(this.jLabel1).addGap(0, 93, 32767))).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton1).addGap(182, 182, 182)));
        
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addGap(18, 18, 18).addComponent(this.jScrollPane1, -2, 89, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton1).addContainerGap(-1, 32767)));
        
        pack();
    }
    
    private void jButton1ActionPerformed(ActionEvent evt){
        devolver();
        dispose();
    }
    
    public static void main(String[] args){
        try{
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(RAM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                RAM dialog = new RAM(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}
/* Location:           C:\Archivos\Minecraft\Login\inst - copia(Funciona)\.minecraft\RUN.jar
 * Qualified Name:     RAM
 * JD-Core Version:    0.6.0
 */