package banco;
import administracion.Archivo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/* 
 * 
 * @author Pacheco
 */
public abstract class Cuenta {
    private Integer cuenta;
    private Double balance;
    
    public Cuenta(Integer cuenta,Double balance){
        this.cuenta=cuenta;
        this.balance=balance;
    }
    
    public Cuenta(Integer cuenta){
        this.cuenta=cuenta;
    }
    
    public Integer getCuenta(){
        return cuenta;
    }
    
    public void setCuenta(Integer Cuenta){
        this.cuenta=cuenta;
    }
    
    public Double getBalance(){
        return balance;
    }
    
    public void setBalance(Double balance){
        this.balance=balance;
    }
    
    public void eliminarCuenta(String donde) throws IOException{
        Archivo file = new Archivo();
        String cadena="";
        String[] cuentas=file.findAll(donde);
        if(cuentas.length==0)
            System.out.println("No se ha encontrado ninguna cuenta");
        else{
            int v_cont=0;
            do{
                if(cuentas[v_cont].split("\\,")[1].equals(this.cuenta+""))
                    cadena+="";
                else
                    cadena+=cuentas[v_cont]+"\n";
                v_cont++;
            }while(v_cont<cuentas.length);
        }
        file.guardar(donde, cadena);
    }
    
    public boolean validacionBalance(){
        return this.getBalance()==0.0;
    }
    
    public void depositar(Double cantidad){
        this.balance+=cantidad; 
    }
}