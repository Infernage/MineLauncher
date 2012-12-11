/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase para hacer soporte a versiones antiguas.
 * @author Reed
 */
public class Outdated {
    public static void ver4xx(){
        File opt = new File(Sources.path("opt.cfg"));
        if (opt.exists()){
            if (Sources.debug) System.out.println("[->Outdated options founded!<-]");
            if (!opt.delete()){
                opt.deleteOnExit();
            }
            File data = new File(Sources.Prop.getProperty("user.data"));
            Sources.IO.borrarFichero(data);
        }
        File copySystem = new File(System.getProperty("user.home") + File.separator + "Desktop" + 
                File.separator + "Copia Minecraft");
        if (copySystem.exists()){
            if (Sources.debug) System.out.println("[->Old copy system founded!<-]");
            System.out.print("Copy system founded! Exporting to the new location... ");
            try {
                Sources.IO.copyDirectory(copySystem, new File(Sources.Prop.getProperty("user.data") + 
                        File.separator + "Copia Minecraft"));
                Sources.IO.borrarFichero(copySystem);
                copySystem.delete();
                System.out.println("OK");
            } catch (IOException ex) {
                System.out.println("FAILED");
                Sources.exception(ex, "Failed to adapt the new copySystem!");
            }
        }
        File updateSystem = new File(Sources.path("Desktop" + File.separator + "Copia Minecraft"));
        if (updateSystem.exists()){
            try {
                Sources.IO.copyDirectory(updateSystem, new File(Sources.Prop.getProperty("user.data") 
                        + File.separator + "Copia Minecraft"));
            } catch (IOException ex) {
                Sources.exception(ex, "Failed to adapt the new copySystem!");
            }
            Sources.IO.borrarFichero(updateSystem);
            updateSystem.delete();
        }
        if (Sources.debug) System.out.println("[->Finalized version 4.x.x search engine for<-]");
    }
    public static void ver500(){
        if (Sources.debug) System.out.println("[->Searching files<-]");
        File bool = new File(Sources.Prop.getProperty("user.data") + File.separator + "boolean.txt");
        File names = new File(Sources.Prop.getProperty("user.data") + File.separator 
                + Sources.Directory.DirNM + File.separator + "ALLNM-MC.cfg");
        File base = new File(Sources.Prop.getProperty("user.data") + File.separator +
                Sources.Directory.DirNM);
        File login = new File(Sources.Files.login(true));
        File instance = new File(Sources.Files.Instance(true));
        if (bool.exists()){
            bool.delete();
            if (Sources.debug) System.out.println("[->Deleted old register file<-]");
        }
        if (names.exists()){
            names.delete();
            if (Sources.debug) System.out.println("[->Deleted old names file<-]");
        }
        if (base.exists()){
            Sources.IO.borrarFichero(base);
            base.delete();
            if (Sources.debug) System.out.println("[->Deleted old temporal directory<-]");
        }
        if (login.exists()){
            login.delete();
            if (Sources.debug) System.out.println("[->Deleted bugged remember file<-]");
        }
        if (!instance.exists()){
            if (Sources.debug) System.out.println("[->Adapting new installation system<-]");
            try{
                File mc = new File(Sources.path(Sources.Directory.DirMC));
                if (!mc.exists()){
                    instance.createNewFile();
                    return;
                }
                File[] files = new File(instance.getParent()).listFiles();
                int i = 0;
                boolean exit = false;
                while(i < files.length && !exit){
                    if (files[i].isDirectory()){
                        File[] tmp = files[i].listFiles();
                        if (tmp.length == 1){
                            exit = true;
                        }
                    }
                    if (!exit){
                        i++;
                    }
                }
                if (files.length > i){
                    File dst = new File(files[i].getAbsolutePath() + File.separator + ".minecraft");
                    dst.mkdirs();
                    Sources.IO.copyDirectory(mc, dst);
                    Sources.IO.borrarFichero(mc);
                    mc.delete();
                } else{
                    if (files.length > 0){
                        i = 0;
                    } else{
                        instance.createNewFile();
                       return;
                    }
                }
                instance.createNewFile();
                PrintWriter pw = new PrintWriter(instance);
                pw.print(files[i].getName());
                pw.close();
            } catch(Exception ex){
                Sources.exception(ex, ex.getMessage());
            }
        }
        if (Sources.debug) System.out.println("[->Finalized version 5.0.0 search engine for<-]");
    }
    public static void checkAll(){
        System.out.println("Actual version: " + Sources.Init.version);
        if (Sources.debug) System.out.println("[->Started version 4.x.x search engine for<-]");
        ver4xx();
        if (Sources.debug) System.out.println("[->Started version 5.0.0 search engine for<-]");
        ver500();
        if (Sources.debug) System.out.println("[->Finalizing<-]");
        System.gc();
    }
    /*private static class outECP{
        private String key = "My secret key";
        private Cipher ecipher;
        private Cipher dcipher;
        public outECP(){
            byte[] salt = {
                (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
                (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
            };
            int iterationCount = 19;
            try{
                KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, iterationCount);
                SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
                ecipher = Cipher.getInstance(key.getAlgorithm());
                dcipher = Cipher.getInstance(key.getAlgorithm());
                // Prepare the parameters to the cipther
                AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
                ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
                dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            } catch (Exception ex){
                ex.printStackTrace(Mainclass.err);
            }
        }
        public String encrypt (String msg){
            try{
                return new sun.misc.BASE64Encoder().encode(ecipher.doFinal(msg.getBytes("UTF8")));
            } catch (Exception ex){
                ex.printStackTrace(Mainclass.err);
                return null;
            }
        }
        public String decrypt (String msg){
            try{
                return new String(dcipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(msg)), "UTF8");
            } catch (Exception ex){
                ex.printStackTrace(Mainclass.err);
                return null;
            }
        }
    }*/
}
