package main.java.behavioral.command;

public class CreditCardDesactivateCommand implements Command{

    private final CreditCard creditCard;

    public CreditCardDesactivateCommand(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void execute() {
        creditCard.sendOtpToCustomer();
        creditCard.desactivateCard();
        creditCard.sendSMSToCustomerDesactivate();
    }
}
