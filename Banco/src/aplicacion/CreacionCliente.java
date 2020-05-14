/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import administracion.Archivo;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import administracion.Validacion;
import banco.Cliente;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author Pacheco
 */
public class CreacionCliente {
    private Date fecha;
    private String nombre;
    private String rfc;
    
    public Date getFecha(){
        return this.fecha;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getRfc(){
        return this.rfc;
    }
    
    public boolean valiFecha(Date fecha){
        LocalDate convFecha = Instant.ofEpochMilli(fecha.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(convFecha, ahora);
        System.out.println(fecha.getTime());
        if(periodo.getYears()<17)
            System.out.println("Debes de tener al menos 18 años.");
        else if(periodo.getYears()>99)
            System.out.println("Debes de tener menos de 100 años");
        else
            this.fecha=fecha;
        return periodo.getYears()>17 && periodo.getYears()<100;
    }
    
    public boolean formatoFecha(String[] fecha) throws ParseException{
        Validacion vali = new Validacion();
        if(vali.numero(fecha[1])==-1)
            System.out.println("No has ingresado un número");
        else if(vali.numero(fecha[2])==-1)
            System.out.println("No has ingresado un número");
        else if(vali.numero(fecha[0])==-1)
            System.out.println("No has ingresado un número");
        else{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            if(valiFecha(df.parse(fecha[2]+"/"+fecha[1]+"/"+fecha[0])))
                return true;
        }
        return false;
    }
    
    public boolean ingrDatos() throws IOException, ParseException{
        Scanner teclado = new Scanner(System.in);
        do{
            System.out.println("Ingresa el RFC: ");
            this.rfc=teclado.nextLine();
        }while(this.rfc.length()<1);
        do{
            System.out.println("Ingresa el nombre: ");
            this.nombre=teclado.nextLine();
        }while(this.nombre.length()<1);
        System.out.println("Ahora ingresaras la fecha de nacimiento(Números).");
        String[] fecha = new String[3];
        System.out.println("Ingresa el año: ");
        fecha[0]=teclado.nextLine();
        System.out.println("Ingresa el mes:");
        fecha[1]=teclado.nextLine();
        System.out.println("Ingresa el dia: ");
        fecha[2]=teclado.nextLine();
        if(valiLongRFC(rfc))
            if(valiUsuario(rfc))
                if(formatoFecha(fecha))
                    return true;
            else
                System.out.println("El usuario ya está registrado");
        return false;
    }
    
    public boolean valiUsuario(String rfc) throws IOException{
        Archivo usuario = new Archivo();
        return usuario.findOne(rfc, "clientes")[0].equals(rfc);
    }
    
    public boolean valiLongRFC(String rfc){
        if(rfc.length()<14)
            System.out.println("tiene que contener 13 caracteres.");
        return rfc.length()<14 && rfc.length()>0;
    }
    
    public Cliente addCliente(){
        Cliente cliente = new Cliente(this.rfc,this.nombre,this.fecha);
        return cliente;
    }
}
