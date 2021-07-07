/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificacarpetas.watcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author NOE
 */
public class obtieneRuta {
File archivo = null;   
FileReader fr = null;
BufferedReader br = null;
public String ruta;

public String retornaRuta ( ){
    ruta="";
    char caract ='@';
    try {
        // Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        //String folder =System.getProperty("user.dir"); 
        //System.out.println(folder);
        archivo = new File ( "D:/VerificaCarpetas/PARAMETROS.txt");
        //File archivo = new File ( folder.concat("/PARAMETROS.txt"));
        System.out.println(archivo);
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
 
        // Lectura del fichero
        String linea;
        int encuentra=0;
        while((linea=br.readLine())!=null)
        { //System.out.println(linea);
            encuentra=linea.indexOf("RUTA A VERIFICAR");
            if (encuentra!=-1){
                encuentra=linea.lastIndexOf(caract) ;
                ruta=linea.substring(encuentra +1 );
            }
        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(),
        "Verificar", JOptionPane.ERROR_MESSAGE);
        ruta="";
        //e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{
              if( null != fr ){
                 fr.close();
              }
           }catch (Exception e2){
              e2.printStackTrace();
           }
        }
    
    return ruta;
    }





    
}
