package main.java.behavioral.chainOfResponsability;

public interface ApproveLoanChain {

    void setNext(ApproveLoanChain loan);
    ApproveLoanChain getNext();
    void CreditCardRequest(int totalLoan);
}
