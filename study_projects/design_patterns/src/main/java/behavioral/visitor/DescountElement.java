package main.java.behavioral.visitor;

public interface DescountElement {
    void accept(CreditCardVisitor cardVisitor);
}
