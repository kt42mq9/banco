package banco;

import administracion.Archivo;
import aplicacion.Menu;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import aplicacion.CreacionCliente;
/**
 *
 * @author Pacheco
 */
public class Banco {
    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException{
        Scanner teclado = new Scanner(System.in);
        Archivo file = new Archivo();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Menu menu = new Menu();
        String[] datos=file.findAll("clientes");
        for(int v_cont=0;v_cont<datos.length;v_cont++){
            String[] temporal=datos[v_cont].split("\\,");
            if(temporal.length==3){
                clientes.add(new Cliente(temporal[0],temporal[1],fecha.parse(temporal[2])));
            }
            else if(temporal.length==2){
                clientes.add(new Cliente(temporal[0],temporal[1]));
            }
            else if(temporal.length==1){
                clientes.add(new Cliente(temporal[0]));
            }
        }
        
        //Ciclo para agregar las cuentas por cliente
        for(Cliente usuario : clientes){
            //Agregar la/s cuenta/s correspondientes a cada cliente
            datos=file.findOne(usuario.rfc, "creditos");
            for(int v_cont=0;v_cont<datos.length;v_cont++){
                String[] temporal=datos[v_cont].split("\\,");
                usuario.addCredito(new Credito(Integer.parseInt(temporal[1]),Double.parseDouble(temporal[2]),Double.parseDouble(temporal[3])));
            }
            //Agregar la/s cuenta/s correspondientes a cada cliente
            datos=file.findOne(usuario.rfc, "cheques");
            for(int v_cont=0;v_cont<datos.length;v_cont++){
                String[] temporal=datos[v_cont].split("\\,");
                usuario.addCheques(new Cheque(Integer.parseInt(temporal[1]),Double.parseDouble(temporal[2])));
            }
        }
        
        //Proceso de la aplicacion.
        int opcion;
        boolean v_bandera;
        do{
            v_bandera=false;
            opcion=0;
            int actividad=0;
            String rfc="";
            int numCuenta=0;
            opcion=menu.menuPrincipal();
            switch(opcion){
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                case 1:
                    rfc=(menu.soliRfc());
                    for(Cliente usuario: clientes){
                        if(rfc.equals(usuario.rfc)){
                            switch(menu.menuActividad()){
                                case 1:
                                    switch (menu.tipoCuenta()){
                                        case 1:
                                                numCuenta=menu.numCuenta();
                                                for(Cheque cheque: usuario.getCheques())
                                                    if(numCuenta==cheque.getCuenta())
                                                        if(cheque.validacionBalance()){
                                                            cheque.eliminarCuenta("cheques");
                                                            v_bandera=true;
                                                        }
                                                        else
                                                            System.out.println("Error.\n"
                                                                    + "La cuenta aún cuenta con saldo disponible\n");
                                                if(v_bandera){
                                                    usuario.elimCuentaCheques(numCuenta);
                                                    System.out.println("Cuenta eliminada.");
                                                }
                                                break;
                                        case 2:
                                                numCuenta=menu.numCuenta();
                                                for(Credito credito: usuario.getCredito())
                                                    if(numCuenta==credito.getCuenta())
                                                        if(credito.validacionDeuda()){
                                                            credito.eliminarCuenta("creditos");
                                                            v_bandera=true;
                                                        }
                                                        else
                                                            System.out.println("Aún tienes una deuda pendiente.");
                                                if(v_bandera){
                                                    usuario.elimCuentaCredito(numCuenta);
                                                    System.out.println("Cuenta eliminada.");
                                                }
                                                break;
                                        default:
                                            System.out.println("No has elegido una opción valida. Vuelve a intentarlo.");
                                            break;
                                    }
                                break;
                                case 2:
                                    System.out.println("Introduce una cantidad");
                                    Double cantidad=teclado.nextDouble();
                                    for(Credito credito: usuario.getCredito()){
                                        System.out.println(credito.getDeuda());
                                        credito.prestar(cantidad);
                                        System.out.println(credito.getDeuda());
                                    }
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    CreacionCliente creacion = new CreacionCliente();
                    if(creacion.ingrDatos()){
                        clientes.add(new Cliente(creacion.getRfc(),creacion.getNombre(),creacion.getFecha()));
                        file.agregarLinea("clientes", creacion.getRfc()+","+creacion.getNombre()+","+creacion.getFecha());
                    }
                    break;
            }
        }while(opcion!=0);
    }
}