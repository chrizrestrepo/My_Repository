package main.java.behavioral.chainOfResponsability;

/**
 * el patron de diseño Chain Of Responsability, requiere de los tres metodos sobreescritos
 * de la interfaz definida, lo que se busca es que desde el request se defina cual de las
 * implementaciones de la interfaz debe responder a la peticion mediante una validacion, si esta
 * no se cumple pues la logica será resuelta por otra implementacion de la misma, la cual es definida por
 * la clase que se defina como punto de entrada a la cadena de responsabilidad, que para el caso es la clase
 * CreditCard
 */
public class GoldCard implements ApproveLoanChain{

    private ApproveLoanChain next;

    @Override
    public void setNext(ApproveLoanChain loan) {
        this.next = loan;
    }

    @Override
    public ApproveLoanChain getNext() {
        return next;
    }

    @Override
    public void CreditCardRequest(int totalLoan) {
         if(totalLoan <= 10000){
             System.out.println("esta solicitud de tarjeta de crédito la maneja la tarjeta Gold");
         }
         next.CreditCardRequest(totalLoan);
    }
}
