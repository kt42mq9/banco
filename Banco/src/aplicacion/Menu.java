package aplicacion;
import java.util.Scanner;
import administracion.Validacion;
/**
 *
 * @author Pacheco
 */
public class Menu {
    private Scanner teclado;
    private String menu;
    private Validacion validar;
    
    public Menu(){
        teclado = new Scanner(System.in);
        validar = new Validacion();
    }
    
    public int menuPrincipal(){
        menu = "1. Buscar cliente\n"
                + "2. Crear cuenta\n"
                + "Opcion: ";
        System.out.print(menu);
        return validar.numero(teclado.nextLine());
    }
    
    public String soliRfc(){
        System.out.print("Introduce el rfc: ");
        return teclado.nextLine();
    }
    
    public int menuActividad(){
        menu="¿Qué deseas hacer?\n"
                + "1. Eliminar cuenta\n"
                + "2. Agregar cuenta"
                + "3. Actualizar cuenta"
                + "Opcion: ";
        System.out.print(menu);
        return validar.numero(teclado.nextLine());
    }
    
    public int tipoCuenta(){
        menu = "1. Cuenta de Cheque.\n"
                + "2. Cuenta de Credito.\n"
                + "Opcion: ";
        System.out.print(menu);
        return validar.numero(teclado.nextLine());
    }
    
    public int numCuenta(){
        menu = "Introduce el numero de cuenta: ";
        System.out.print(menu);
        return validar.numero(teclado.nextLine());
    }
    
}