package main.java.behavioral.chainOfResponsability;

/**
 * esta clase es el punto de entrada a la logica que orquesta la cadena de responsabilidad
 * de las diferentes implemnetaciones (siempre debe existir una clase que defina la logica de como
 * se ejecutara la cadena de responsabilidad), en dicho caso el metodo que recibe el request es quien define
 * como se implementara, dando un orden a la cadena de responsabilidad, por lo que el metodo setNext que se implementa
 * en las diferentes implementaciones es quien nos permite ir escalando entre la cadena de responsabilidades
 */
public class CreditCard implements ApproveLoanChain{

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
        GoldCard goldCard = new GoldCard();
        this.setNext(goldCard);

        PlatinumCard platinumCard = new PlatinumCard();
        goldCard.setNext(platinumCard);

        BlackCard blackCard = new BlackCard();
        blackCard.setNext(blackCard);

        next.CreditCardRequest(totalLoan);
    }

}
