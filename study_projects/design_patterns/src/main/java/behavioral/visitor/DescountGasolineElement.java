package main.java.behavioral.visitor;

public class DescountGasolineElement implements DescountElement{

    @Override
    public void accept(CreditCardVisitor cardVisitor) {
        cardVisitor.applyDescountOnGasoline(this);
    }
}
