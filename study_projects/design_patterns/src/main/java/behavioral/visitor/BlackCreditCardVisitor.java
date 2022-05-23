package main.java.behavioral.visitor;

public class BlackCreditCardVisitor implements CreditCardVisitor{

    @Override
    public void applyDescountOnTravels(DescountTravelElement element) {
        System.out.println("recibes un 10% de descuento en tu proxima compra de tiquetes de viaje");
    }

    @Override
    public void applyDescountOnGasoline(DescountGasolineElement element) {
        System.out.println("8% de descuento en gasolina con tu tarjeta Classic");
    }
}
