package main.java.creational.factory;


/*
el patron factory permite crear una instancia de cierto tipo a partir de la
validacion de algun argumento para su creacion (en este caso es un enumerador),
el cual mediante la validacion del argumento crea una instancia de la
interfaz que se implemente segun el tipo
 */
public class PaymentFactory {

    public static Payment buildPayment(PaymentEnumType paymentEnumType){
        if(paymentEnumType.equals(PaymentEnumType.DEBIT)){
            return new Debit();
        }else if(paymentEnumType.equals(PaymentEnumType.CREDIT)){
            return new Credit();
        }
        return null;
    }
}
