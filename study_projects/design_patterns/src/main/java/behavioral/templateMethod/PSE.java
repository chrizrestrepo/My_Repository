package main.java.behavioral.templateMethod;

public class PSE extends Payment{
    @Override
    void initialize() {
        System.out.println("inciando pago por medio del cliente PSE");
    }

    @Override
    void startPayment() {
        System.out.println("realizando pago PSE");
    }

    @Override
    void endPayment() {
        System.out.println("pago realizado correctamente a la cuenta con referencia **** con tu cuenta PSE");
    }
}
