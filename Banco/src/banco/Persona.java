package banco;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Pacheco
 */
public abstract class  Persona {
    protected String rfc;
    protected String nombre;
    protected Date nacimiento;
    
    public Persona(String rfc, String nombre, Date nacimiento){
        this.rfc=rfc;
        this.nombre=nombre;
        this.nacimiento=nacimiento;
    }
    public Persona(String rfc, String nombre){
        this.rfc=rfc;
        this.nombre=nombre;
    }
    public Persona(String rfc){
        this.rfc=rfc;
    }
    
    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    
    public Integer edad(){
        LocalDate convertNacimiento = Instant.ofEpochMilli(this.nacimiento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(convertNacimiento, ahora);
        return periodo.getYears();
    }
    
}
