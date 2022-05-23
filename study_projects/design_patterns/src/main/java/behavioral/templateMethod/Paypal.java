package main.java.behavioral.templateMethod;

public class Paypal extends Payment{
    @Override
    void initialize() {
        System.out.println("inciando pago por medio del cliente PayPAl");
    }

    @Override
    void startPayment() {
        System.out.println("realizando pago PayPAl");
    }

    @Override
    void endPayment() {
        System.out.println("pago realizado correctamente a la cuenta con referencia **** con tu cuenta PayPAl");
    }
}
