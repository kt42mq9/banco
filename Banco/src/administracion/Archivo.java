package administracion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
/**
 *
 * @author Pacheco
 */
public class Archivo{
    private String ruta;
    private String[] partes;
    private Integer cuentas;
    
    public Archivo(){
        partes=null;
        ruta="C:\\Users\\Pacheco\\Documents\\NetBeansProjects\\Banco\\txt\\";
    }
    
    public void setRuta(String ruta){
        this.ruta=ruta;
    }
    
    public String getRuta(){
        return this.ruta;
    }
 
    public String[] findOne(String buscar, String donde) throws FileNotFoundException, IOException{
        String cadena;
        ArrayList<String> datos = new ArrayList<String>();
        FileReader fr = new FileReader(this.ruta+donde+".txt");
        BufferedReader br = new BufferedReader(fr);
        while((cadena = br.readLine())!=null)
            if(cadena.split("\\,")[0].equals(buscar))
                datos.add(cadena);
        br.close();
        return datos.toArray(new String[datos.size()]);
    }
    
    public void guardar(String donde, String cadena) throws IOException{
        File file = new File(this.ruta+donde+".txt");
        if(!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(cadena);
        bw.close();
    }
    
    public String[] findAll(String donde) throws FileNotFoundException, IOException{
        String texto;
        ArrayList<String> datos = new ArrayList<String>();
        FileReader fr = new FileReader(this.ruta+donde+".txt");
        BufferedReader br = new BufferedReader(fr);
        while((texto = br.readLine())!=null)
            datos.add(texto);
        br.close();
        return datos.toArray(new String[datos.size()]);
    }
    
    public void agregarLinea(String donde, String cadena) throws FileNotFoundException, IOException{
        Writer add;
        add = new BufferedWriter(new FileWriter(this.ruta+donde+".txt"));  //clears file every time
        add.append("New Line!");
        add.close();
    }
}