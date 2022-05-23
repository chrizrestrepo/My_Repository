package main.java.behavioral.command;

/**
 * para implemntar el patron de diseño command, primero debemos definir una interfaz de tipo command
 * que contenga el metodo execute(), adicional se debe crear una clase quien implemente el metodo por medio
 * de una clase la cual contendra la logica de la linea de comandos que vamos a ejecutar, la cual para el caso es
 * la clase CreditCard, en donde estan expuestos todos los metodos a usar
 *
 * la clase Invoker será quien por medio del metodo run() ejecutara la linea de comandos a partir de la interfaz
 * definida, osea desde la implementacion que se le envie al Invoker
 */
public class CreditCardActivateCommand implements Command{

    private final CreditCard creditCard;

    public CreditCardActivateCommand(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void execute() {
        creditCard.sendOtpToCustomer();
        creditCard.activateCard();
        creditCard.sendSMSToCustomerActivate();
    }
}
