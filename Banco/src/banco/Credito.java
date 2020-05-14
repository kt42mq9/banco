package banco;

/**
 *
 * @author Pacheco
 */
public class Credito extends Cuenta{
    private Double limite;
    private Double deuda;
    
    public Credito(Integer cuenta, Double deuda,Double limite){
        super(cuenta);
        this.limite=limite;
        this.deuda=deuda;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Double getDeuda() {
        return deuda;
    }

    public void setDeuda(Double deuda) {
        this.deuda = deuda;
    }
    
    public boolean validacionDeuda(){
        return this.deuda==0.0;
    }
    
    public void prestar(Double deuda){
        if(valiLimite())
            if(valiCantidad(deuda))
                this.deuda+=deuda;
            else
                System.out.println("La cantidad a prestar supera tu limite.");
        else
            System.out.println("Has superado tu limite de prestamo.");
    }
    
    public boolean valiLimite(){
        return this.deuda<this.limite;
    }
    
    public boolean valiCantidad(Double cantidad){
        return (this.deuda+cantidad)<=this.limite;
    }
}
