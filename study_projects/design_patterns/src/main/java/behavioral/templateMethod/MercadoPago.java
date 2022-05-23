package main.java.behavioral.templateMethod;

public class MercadoPago extends Payment{
    @Override
    void initialize() {
        System.out.println("inciando pago por medio del cliente MercadoPago");
    }

    @Override
    void startPayment() {
        System.out.println("realizando pago MercadoPago");
    }

    @Override
    void endPayment() {
        System.out.println("pago realizado correctamente a la cuenta con referencia **** con tu cuenta MercadoPago");
    }
}
