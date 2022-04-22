package main.java.creational.factory;

public class Credit implements Payment{

    @Override
    public void doPayment() {
        System.out.println("realizando pago con tarjeta credito");
    }
}
