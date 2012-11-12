package Login;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.*;
/**
 *
 * @author Reed
 */
public class Updater extends Thread{
    private boolean data;
    private String link;
    private String path;
    private String name;
    public Installer.Worker work;
    //Creamos el actualizador con el link de la nueva versión como parámetro
    public Updater (String host, boolean isData){
        super("Updater");
        data = isData;
        link = host;
        path = Sources.path(Sources.DirData() + Sources.sep() + "Update");
    }
    private String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }
    //Método de descarga
    private void descargar(){
        RandomAccessFile file = null;
        InputStream stream = null;
        try {
            Thread.sleep(1000);
            System.out.print("Transfering...");
            URL url = new URL(link);
            // Abrimos la conexión a la URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.print("...");
            // Especificamos la porción que queremos descargar
            connection.setRequestProperty("Range", "bytes=" + 0 + "-");
            System.out.print("...");
            // Conectamos al servidor
            connection.connect();
            name = "Update.zip";
            String path = Sources.path(Sources.DirData() + Sources.sep() + name);
            System.out.print("...");
            // Abrimos el archivo
            file = new RandomAccessFile(path, "rw");
            file.seek(0);
            System.out.print("...");
            //Obtenemos el stream de la URL
            stream = connection.getInputStream();
            int size = connection.getContentLength();
            Vista2.jProgressBar1.setMaximum(size);
            //Creamos un array de bytes
            byte buffer[] = new byte[size];
            //Indicamos cuantos se van a leer cada vez
            int read = stream.read(buffer);
            int offset = 0;
            System.out.print("...");
            while (read > 0) {
                offset += read;
                Vista2.per(size, offset);
                Vista2.jProgressBar1.setValue(offset);
                // Escribimos los bytes en el fichero
                file.write(buffer, 0, read);
                read = stream.read(buffer);
            }
            //Cerramos los sockets
            stream.close();
            file.close();
            System.out.println("... OK");
        } catch (Exception e) {
            System.out.println("... FAILED");
            Sources.fatalException(e, "Download crashed!", 4);
        }
    }
    //Método de descompresión
    private void descomprimir(){
        System.out.print("Decompressing...");
        //Creamos la carpeta donde van a ir los archivos
        String zipper = Sources.path(Sources.DirData() + Sources.sep() + name);
        File mine = new File(path);
        if (mine.exists()){
            Sources.borrarFichero(mine);
        }
        System.out.print("...");
        mine.mkdirs();
        try {
            //Abrimos el comprimido
            ZipInputStream zip = new ZipInputStream(new FileInputStream(new File(zipper)));
            ZipEntry entrada;
            System.out.print("...");
            //Vamos cogiendo cada vez la siguiente entrada
            while ((entrada = zip.getNextEntry()) != null){
                System.out.print("...");
                boolean direc = false;//Comprobamos si es un directorio o no
                //Separamos los nombres por el separador "/"
                StringTokenizer token = new StringTokenizer(entrada.getName(), "/");
                //Creamos una lista con todo el path
                List<String> lista = new ArrayList<String>();
                //Separamos el path y lo añadimos a la lista
                while (token.hasMoreTokens()){
                    String A = token.nextToken();
                    if (A != null){
                        lista.add(A);
                    }
                }
                //Comprobamos si es un fichero o un directorio
                if (entrada.getName().endsWith("/")){
                    direc = true;
                }
                //Cambiamos el tipo de separación de carpetas
                StringBuilder build = new StringBuilder(path);
                for (int i = 0; i < lista.size(); i++){
                    build.append(Sources.sep()).append(lista.get(i));
                }
                String filero = build.toString();
                File fich = new File(filero);
                //Si es un directorio, creamos la carpeta
                if (direc){
                    fich.mkdirs();
                } else{//Sino, traspasamos el archivo a su destino
                    FileOutputStream salida = new FileOutputStream(fich);
                    int leido;
                    byte [] buffer = new byte[4096];
                    while ((leido = zip.read(buffer)) > 0){
                        salida.write(buffer, 0, leido);
                    }
                    //Cerramos todos los escuchadores
                    salida.close();
                }
                zip.closeEntry();
            }
            zip.close();
            System.out.println("... OK");
        } catch (Exception ex) {
            System.out.println("... FAILED");
            Sources.fatalException(ex, "Error al desencriptar la actualización.", 4);
        }
        new File(zipper).delete();
        Sources.borrarFichero(mine);
    }
    //Método de ejecución de Main Instalador
    private void exec(){
        System.out.print("Openning new filesystem... ");
            //Por último ejecutamos el nuevo login
        if (!data){
            File old = new File(Sources.path(Sources.DirData() + Sources.sep() + "Update" + Sources.sep() 
                    + Sources.jar));
            File oldlib = new File(Sources.path(Sources.DirData() + Sources.sep() + "Update" + Sources.sep()
                    + Sources.Dirlibs));
            File next = new File(Sources.path(Sources.DirMC + Sources.sep() + Sources.jar));
            File nextlib = new File(Sources.path(Sources.DirMC + Sources.sep() + Sources.Dirlibs));
            File minejar = new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.Dirfiles
                    + Sources.sep() + "minecraft.jar"));
            minejar.delete();
            if (next.exists()){
                next.delete();
            }
            if (nextlib.exists()){
                Sources.borrarFichero(nextlib);
            }
            try{
                Sources.copy(old, next);
                Sources.copyDirectory(oldlib, nextlib);
                old.delete();
                Sources.borrarFichero(oldlib);
            } catch (Exception ex){
                ex.printStackTrace(Mainclass.err);
            }
            System.out.println("OK");
            temp();
            return;
        }
        System.out.println("OK");
        Vista2.jProgressBar1.setVisible(true);
        Vista2.jProgressBar1.setString("Aplicando actualización...");
        Vista2.jProgressBar1.setMaximum(100);
        Vista2.jProgressBar1.setMinimum(0);
        Vista2.jProgressBar1.setValue(0);
        System.out.println("Applying update...............");
        work = new Installer.Worker(Vista2.jProgressBar1);
        work.execute();
        File dst = new File(Sources.path(Sources.DirMC));
        Executer exe = new Executer(dst);
        exe.setDaemon(true);
        Mainclass.hilos.put("Installer", exe);
        while(!work.isDone() && !work.isCancelled()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace(Mainclass.err);
            }
        }
        exe.out();
        if (!work.isCancelled()){
            this.temp();
        }
    }
    public void temp(){
        try {
            System.out.println("[>Executing new process and exiting<]");
            Desktop d = Desktop.getDesktop();
            d.open(new File(Sources.path(Sources.DirData() + Sources.sep() + Sources.Dirfiles + Sources.sep()
                    + "Temporal.jar")));
        } catch (IOException ex) {
            ex.printStackTrace(Mainclass.err);
        } finally{
            System.exit(0);
        }
    }
    //Método de ejecución
    @Override
    public void run(){
        descargar();//Descargamos los archivos necesarios
        descomprimir();//Los descomprimimos
        exec();//Ejecutamos el main
    }
}