package main.java;

import main.java.creational.builder.Card;
import main.java.creational.factory.Payment;
import main.java.creational.factory.PaymentEnumType;
import main.java.creational.factory.PaymentFactory;
import main.java.creational.singleton.Person;

public class MainClass {

    public static void main(String[] args){
        testFactoryMethod(PaymentEnumType.DEBIT);
        testBuilder();
        testSingleton();
    }

    public static void testFactoryMethod(PaymentEnumType paymentEnumType){
        Payment payment = PaymentFactory.buildPayment(paymentEnumType);
        payment.doPayment();
    }

    public static void testBuilder(){
        Card card = new Card
                .Builder("debit", "0909 0212 3654 8989")
                .name("cristian")
                .expires(202507)
                .credit(false)
                .buildCard();
        System.out.println(card.toString());
    }

    public static void testSingleton(){
        Person.getINSTANCE().setFirtsName("cristian");
        System.out.println(Person.getINSTANCE().getFirtsName());
    }
}
