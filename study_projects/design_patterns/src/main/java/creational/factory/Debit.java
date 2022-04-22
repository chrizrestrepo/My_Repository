package main.java.creational.factory;

public class Debit implements Payment{

    @Override
    public void doPayment() {
        System.out.println("realizando pago con tarjeta debito");
    }
}
