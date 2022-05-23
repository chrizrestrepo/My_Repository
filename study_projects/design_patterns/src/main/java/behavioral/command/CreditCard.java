package main.java.behavioral.command;

public class CreditCard {

    public void sendOtpToCustomer(){
        System.out.println("otp enviado al usuario");
    }

    public void activateCard(){
        System.out.println("se ha activado la tarjeta");
    }

    public void sendSMSToCustomerActivate(){
        System.out.println("SMS enviado al cliente informando que su tarjeta ha sido ACTIVADA");
    }

    public void desactivateCard(){
        System.out.println("se ha desactivado la tarjeta");
    }

    public void sendSMSToCustomerDesactivate(){
        System.out.println("SMS enviado al cliente informando que su tarjeta ha sido DESACTIVADA");    }

}
