package main.java.behavioral.visitor;

public interface CreditCardVisitor {
    void applyDescountOnTravels(DescountTravelElement element);
    void applyDescountOnGasoline(DescountGasolineElement element);
}
