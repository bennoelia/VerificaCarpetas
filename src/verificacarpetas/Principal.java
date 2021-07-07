package verificacarpetas;

import verificacarpetas.watcher.Watcher;
import java.io.IOException;
import verificacarpetas.watcher.obtieneRuta;

public class Principal {
   
    
    
/*public void monitorear (String dir) throws IOException{
    Watcher verificavarpetas = new Watcher();
    verificavarpetas.doWath(dir);
}*/
    
         
    
    
    public static void main(String[] args) throws IOException {
        obtieneRuta ruta=new obtieneRuta();
        ruta.retornaRuta();
        String verificarRuta=ruta.ruta;
        Watcher verificavarpetas = new Watcher();
        //verificavarpetas.doWath("\\\\serverprod\\Produccion2\\OPERADORES\\Padron_Web_XML\\2020\\");
        verificavarpetas.doWath(verificarRuta);
       
    }

}