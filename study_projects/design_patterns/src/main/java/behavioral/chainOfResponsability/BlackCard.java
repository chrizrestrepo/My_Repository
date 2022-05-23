package main.java.behavioral.chainOfResponsability;

public class BlackCard implements ApproveLoanChain{

    private ApproveLoanChain next;

    @Override
    public void setNext(ApproveLoanChain loan) {
        this.next = loan;
    }

    @Override
    public ApproveLoanChain getNext() {
        return next;
    }

    @Override
    public void CreditCardRequest(int totalLoan) {
        if(totalLoan > 50000){
            System.out.println("esta solicitud de tarjeta de crédito la maneja la tarjeta Black");
        }
        next.CreditCardRequest(totalLoan);
    }
}
