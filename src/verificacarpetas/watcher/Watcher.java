/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificacarpetas.watcher;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 *
 * @author NOE
 */
public class Watcher {
    public void doWath(String directory) throws IOException {

        System.out.println("WatchService in " + directory);
        // Obtenemos el directorio
        Path directoryToWatch = Paths.get(directory);
        if (directoryToWatch == null) {
            throw new UnsupportedOperationException("No se encuentra el Directorio");
        }

        // Solicitamos el servicio WatchService
        WatchService watchService = directoryToWatch.getFileSystem().newWatchService();
        // Registramos los eventos que queremos monitorear
        //directoryToWatch.register(watchService, new WatchEvent.Kind[] {ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY});
        directoryToWatch.register(watchService, new WatchEvent.Kind[] {ENTRY_CREATE, ENTRY_MODIFY});
        System.out.println("Started WatchService in " + directory);
        //se toma del directorio los último 4 dígitos
        int longitud =directory.length()-5;//SE SUPONE Q EN LOS ÚLTIMOS 4 DÍGITOS DEBE ESPECIFICARSE EL AÑO, considerar si se cuenta la barra invetida
        String Anio= directory.substring(longitud,longitud + 4);
        System.out.println(directory);
        System.out.println(longitud);
        System.out.println(Anio);
        try {
            // Esperamos que algo suceda con el directorio
            WatchKey key = watchService.take();
                     
            // Algo ocurrio en el directorio para los eventos registrados
            while (key != null) {
                for (WatchEvent event : key.pollEvents()) {
                    String eventKind = event.kind().toString();
                    System.out.println(eventKind);
                    String file = event.context().toString();
                    System.out.println(file);
                    //String archivoBorrar= directory+  "\\"  +file;
                    String archivoBorrar= directory+file;
                    System.out.println(archivoBorrar);
                    //Path archivoBorrar= (Path) event.context();
                    //Verificamos si el cambio se trata de un archivo o un directorio
                    File nivel = new File(archivoBorrar);
                    if(nivel.isFile())
                        {     
                        int encuentra=file.indexOf(Anio);
                        System.out.println(encuentra);
                        if (encuentra==-1){  
                            File fichero = new File(archivoBorrar);
                            System.out.println(archivoBorrar);
                            if (fichero.delete())
                            {System.out.println("El Archivo ha sido borrado satisfactoriamente");
                             JOptionPaneConTimeOut jo=new JOptionPaneConTimeOut( );
                             JOptionPaneConTimeOut.visualizaDialogo(null,"Se borró el Archivo por no corresponder al Año de la Carpeta!!!" + file,"Atención",10000);}
                            else
                            {System.out.println("El Archivo no puede ser borrado");}
                        }
                    }else if(nivel.isDirectory()){
                        //Watcher verificavarpetas = new Watcher();
                        //verificavarpetas.doWath(archivoBorrar);
                        //Se tendran que verificar todos los archivos de la carpeta y borrar los q no correspondan al año del Directorio Original que se esta monitoreando
                        File dir = new File(archivoBorrar);
                        String[] ficheros = dir.list();
                        for (String nameFile : ficheros) 
                            {
                            int encuentra=nameFile.indexOf(Anio);
                            System.out.println(encuentra);
                            if (encuentra==-1){  
                                    String archivoBorrarSub=archivoBorrar  +  "\\"  + nameFile;
                                    File fichero = new File(archivoBorrarSub);
                                    System.out.println(archivoBorrarSub);
                                    if (fichero.delete())
                                        {System.out.println("El Archivo ha sido borrado satisfactoriamente");
                                        JOptionPaneConTimeOut jo=new JOptionPaneConTimeOut( );
                                        JOptionPaneConTimeOut.visualizaDialogo(null,"Se borró el Archivo por no corresponder al Año de la Carpeta!!!" + nameFile,"Atención",10000);}
                                        //JOptionPane.showMessageDialog(null, nameFile,    
                                        //"Se borró el Archivo por no corresponder al Año de la Carpeta!!!", JOptionPane.CLOSED_OPTION);}
                                    else
                                        {System.out.println("El Archivo no puede ser borrado");}
                                        
                            }
                        }
                    }
                    
                    // Volvemos a escuchar. Lo mantenemos en un loop para escuchar indefinidamente.
                    key.reset();
                    key = watchService.take();
                }// CIERRRA EL FOR
            }// CIERRA EL WHILE
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
   }
}
