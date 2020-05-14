package banco;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import administracion.Archivo;
import java.io.IOException;
/**
 *
 * @author Pacheco
 */
public class Cliente extends Persona{
    private ArrayList<Cheque> cheques= new ArrayList<Cheque>();
    private ArrayList<Credito> creditos= new ArrayList<Credito>();
    
    public Cliente(String rfc, String nombre, Date nacimiento){
        super(rfc,nombre,nacimiento);
    }
    
    public Cliente(String rfc, String nombre){
        super(rfc,nombre);
    }
    
    public Cliente(String rfc){
        super(rfc);
    }

    public void Historial(){
        
    }
    
    public ArrayList<Cheque> getCheques() {
        return cheques;
    }

    public void addCheques(Cheque cheques) {
        this.cheques.add(cheques);
    }
    
    public void elimCuentaCheques(Integer numCuenta){
        int v_cont=0;
        while(v_cont<cheques.size()){
            if(cheques.get(v_cont).getCuenta().equals(numCuenta)){
                cheques.remove(v_cont);
                v_cont=cheques.size();
            }
            else
                v_cont++;
        }
    }
    
    public ArrayList<Credito> getCredito(){
        return creditos;
    }
    
    public void addCredito(Credito credito){
        creditos.add(credito);
    }
    
    public void elimCuentaCredito(Integer numCuenta){
        int v_cont=0;
        while(v_cont<creditos.size()){
            if(creditos.get(v_cont).getCuenta().equals(numCuenta)){
                creditos.remove(v_cont);
                v_cont=creditos.size();
            }
            else
                v_cont++;
        }
    }
    
}