package administracion;
/**
 *
 * @author Pacheco
 */
public class Validacion {
    
    public Integer numero(String texto){
        try{
            int valor = Integer.valueOf(texto);
            return valor;
        }catch(Exception v_error){
            return -1;
        }
    }
    
    
}
