package main.java;

import main.java.behavioral.command.CreditCardActivateCommand;
import main.java.behavioral.command.CreditCardDesactivateCommand;
import main.java.behavioral.command.CreditCardInvoker;
import main.java.behavioral.mediator.ConcreteColleageOne;
import main.java.behavioral.mediator.ConcreteColleageTwo;
import main.java.behavioral.mediator.ConcreteMediator;
import main.java.behavioral.observer.*;
import main.java.behavioral.state.PhoneAlertState;
import main.java.behavioral.state.PhoneAlertStateContext;
import main.java.behavioral.state.Silence;
import main.java.behavioral.state.Vibration;
import main.java.behavioral.strategy.LowerCaseStrategy;
import main.java.behavioral.strategy.TextFormatter;
import main.java.behavioral.strategy.UpperCaseStrategy;
import main.java.behavioral.templateMethod.MercadoPago;
import main.java.behavioral.templateMethod.PSE;
import main.java.behavioral.templateMethod.Paypal;
import main.java.creational.builder.Card;
import main.java.creational.factory.Payment;
import main.java.creational.factory.PaymentEnumType;
import main.java.creational.factory.PaymentFactory;
import main.java.creational.singleton.Person;
import main.java.behavioral.chainOfResponsability.CreditCard;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        //CREACIONALES
        //testFactoryMethod(PaymentEnumType.DEBIT);
        //testBuilder();
        //testSingleton();

        //ESTRUCTURALES
        //testChainOfResponsability();
        //testCommand();
        //testMediator();
        //testObserver();
        //testState();
        //testStrategy();
        testTemplateMethod();

    }

    public static void testFactoryMethod(PaymentEnumType paymentEnumType) {
        Payment payment = PaymentFactory.buildPayment(paymentEnumType);
        payment.doPayment();
    }

    public static void testBuilder() {
        Card card = new Card
                .Builder("debit", "0909 0212 3654 8989")
                .name("cristian")
                .expires(202507)
                .credit(false)
                .buildCard();
        System.out.println(card.toString());
    }

    public static void testSingleton() {
        Person.getINSTANCE().setFirtsName("cristian");
        System.out.println(Person.getINSTANCE().getFirtsName());
    }

    public static void testChainOfResponsability() {
        //validar null
        CreditCard creditCard = new CreditCard();
        creditCard.CreditCardRequest(20000);
    }

    public static void testCommand() {
        main.java.behavioral.command.CreditCard creditCard = new main.java.behavioral.command.CreditCard();

        CreditCardInvoker creditCardInvoker = new CreditCardInvoker();
        creditCardInvoker.setCommand(new CreditCardActivateCommand(creditCard));
        creditCardInvoker.run();
        System.out.println("--------------------------------------------");
        creditCardInvoker.setCommand(new CreditCardDesactivateCommand(creditCard));
        creditCardInvoker.run();
    }

    public static void testMediator() {
        ConcreteMediator mediator = new ConcreteMediator();
        ConcreteColleageOne colleageOne = new ConcreteColleageOne(mediator);
        ConcreteColleageTwo colleageTwo = new ConcreteColleageTwo(mediator);

        mediator.setColleageOne(colleageOne);
        mediator.setColleageTwo(colleageTwo);

        colleageOne.send("hola soy el user number 1");
        colleageTwo.send("hola que tal?? soy el user number 2");
    }

    public static void testObserver() throws InterruptedException {
        MessagePublisher publisher = new MessagePublisher();
        CarObserver car = new CarObserver();
        PersonObserver person = new PersonObserver();

        publisher.attach(car);
        publisher.attach(person);

        publisher.notifyUpdate(new Semaforo(SemaforoStateEnumType.ROJO.getStatus()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------");
        publisher.notifyUpdate(new Semaforo(SemaforoStateEnumType.VERDE.getStatus()));
    }

    public static void testState() {
        PhoneAlertStateContext phoneAlertState = new PhoneAlertStateContext();
        phoneAlertState.alert();
        System.out.println("----------------------");
        phoneAlertState.setState(new Silence());
        phoneAlertState.alert();
        System.out.println("----------------------");
        phoneAlertState.setState(new Vibration());
        phoneAlertState.alert();
    }

    public static void testStrategy() {
        TextFormatter formatter = new TextFormatter(new LowerCaseStrategy());
        formatter.formatText("mi nombre es criTIAN yair RESTREPO ESCOBAR");

        formatter = new TextFormatter(new UpperCaseStrategy());
        formatter.formatText("mi nombre es criTIAN yair RESTREPO ESCOBAR");
    }

    public static void testTemplateMethod(){
        main.java.behavioral.templateMethod.Payment payment = new PSE();
        payment.doPayment();

        System.out.println("------------------");
        payment = new MercadoPago();
        payment.doPayment();

        System.out.println("------------------");
        payment = new Paypal();
        payment.doPayment();
    }
}
