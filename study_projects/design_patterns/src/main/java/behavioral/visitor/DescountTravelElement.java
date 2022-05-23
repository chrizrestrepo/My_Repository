package main.java.behavioral.visitor;

public class DescountTravelElement implements DescountElement{

    @Override
    public void accept(CreditCardVisitor cardVisitor) {
        cardVisitor.applyDescountOnTravels(this);
    }
}
