/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MLR.launcher;

import MLR.InnerApi;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JLabel;

/**
 *
 * @author Infernage
 */
public class Logger extends Thread{
    private String targetURL, urlParameters, userName, password;
    private JLabel label;
    private MLR.launcher.minecraft.Launcher launcher;
    public boolean offline = false, init = false, started = false, finish = false;;
    public Logger(){
        super("Logger");
        targetURL = "https://login.minecraft.net/";
    }
    public void init(String user, String pass, JLabel lab){
        if (InnerApi.debug) System.out.println("[->Initialized logger with MC account<-]");
        init = true;
        label = lab;
        userName = user;
        password = pass;
    }
    public void init(String user, JLabel lab){
        if (InnerApi.debug) System.out.println("[->Initialized logger with OFF account<-]");
        init(user, null, lab);
        offline = true;
    }
    private String logMine (String targetURL, String urlParameters){
        if (InnerApi.debug) System.out.println("[->Retrieving data<-]");
        HttpsURLConnection connection = null;
        try {
            URL url = new URL(targetURL);
            connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            Certificate[] certs = connection.getServerCertificates();
            byte[] bytes = new byte[294];
            DataInputStream dis = new DataInputStream(Logger.class.getResourceAsStream("/MLR/resources/minecraft.key"));
            dis.readFully(bytes);
            dis.close();
            Certificate c = certs[0];
            PublicKey pk = c.getPublicKey();
            byte[] data = pk.getEncoded();
            for (int i = 0; i < data.length; i++) {
                if (data[i] == bytes[i]) continue; throw new RuntimeException("Public key mismatch");
            }
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            String str1 = response.toString();
            return str1;
        } catch (Exception e) {
            InnerApi.exception(e, "Error conectándose a la página web.");
            return null;
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
    private void launch(String arg){
        try {
            InnerApi.Init.mainGUI.setVisible(false);
            System.out.println("Initializing minecraft...");
            if (!offline){
                launcher = new MLR.launcher.minecraft.Launcher(arg);
                launcher.init();
            } else{
                launcher = new MLR.launcher.minecraft.Launcher();
                launcher.offline(userName);
                launcher.init();
            }
        } catch (Exception ex) {
            InnerApi.fatalException(ex, "Error inicializando minecraft.", 1);
        }
    }
    @Override
    public void run(){
        try {
            started = true;
            String res = "";
            if (offline){
                if (InnerApi.debug) System.out.println("[->Starting in OFFLINE MODE<-]");
                launch(null);
                return;
            }
            if (InnerApi.debug) System.out.println("[->Adding parameters<-]");
            urlParameters = "user=" + URLEncoder.encode(userName, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8") + "&version=" + 13;
            res = this.logMine(targetURL, urlParameters);
            if (res == null){
                if (InnerApi.debug) System.out.println("[->Connection failed<-]");
                label.setForeground(Color.red);
                label.setText("Can't connect to minecraft.net");
                InnerApi.Init.mainGUI.tries--;
                if (InnerApi.Init.mainGUI.tries == 0){
                    InnerApi.Init.mainGUI.offline();
                } else{
                    InnerApi.Init.mainGUI.retry();
                }
            } else if (!res.contains(":")){
                if (InnerApi.debug) System.out.println("[->Connection failed<-]");
                if (res.trim().equals("Bad login")){
                    label.setForeground(Color.red);
                    label.setText("Login failed!");
                    InnerApi.Init.mainGUI.retry();
                } else if (res.trim().equals("Old version")){
                    label.setForeground(Color.red);
                    label.setText("Outdated minecraft launcher!");
                } else{
                    System.err.println(res);
                }
                InnerApi.Init.mainGUI.tries--;
                if (InnerApi.Init.mainGUI.tries == 0){
                    InnerApi.Init.mainGUI.offline();
                } else{
                    InnerApi.Init.mainGUI.retry();
                }
            } else if (res.contains(":")){
                if (InnerApi.debug) System.out.println("[->Starting launcher in ONLINE MODE<-]");
                launch(res);
            }
        } catch (Exception ex) {
            InnerApi.fatalException(ex, "Error inicializando minecraft.", 1);
        }
    }
}
    /*public void play(String user, String pass){
        try {
            String[] args = new String[]{ user, pass };
            URL u = new File(InnerApi.path(InnerApi.Directory.DirMC + File.separator + "minecraft.jar")).toURI().toURL();
            URLClassLoader cl = new URLClassLoader(new URL[]{u});
            Class launcherFrame = cl.loadClass("net.minecraft.LauncherFrame");
            Method[] test = launcherFrame.getMethods();
            int i = 0;
            while(i < test.length){
                if (test[i].getName().equals("main")){
                    if (InnerApi.Init.consola.isVisible()){
                        InnerApi.Init.consola.exit();
                    }
                    Gui.see.dispose();
                    Method loader = test[i];
                    loader.setAccessible(true);
                    Gui.see.setVisible(false);
                    loader.invoke(launcherFrame, new Object[]{ args });
                    i = test.length;
                } else{
                    i++;
                }
            }
        } catch (Exception ex) {
            InnerApi.fatalException(ex, "Error al inicializar minecraft.", 1);
        }
    }*/
