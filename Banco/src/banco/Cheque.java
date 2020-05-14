package banco;

import java.util.ArrayList;

/**
 *
 * @author Pacheco
 */
public class Cheque extends Cuenta{
    private ArrayList<Cuenta> cuenta = new ArrayList<Cuenta>();
    
    public Cheque(Integer cuenta,Double balance){
        super(cuenta,balance);
    }
    
    public void invertir(){
        
    }
    
    public void retirar(){
        
    }
    
    public boolean valiCantidad(Double cantidad){
        return cantidad >=0;
    }
    
    public boolean valiBalance(){
        return this.getBalance()>0;
    }
    
    public boolean valiRetirar(Double cantidad){
        return cantidad<=this.getBalance();
    }
}
