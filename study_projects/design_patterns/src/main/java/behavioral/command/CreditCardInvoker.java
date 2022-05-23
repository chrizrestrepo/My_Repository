package main.java.behavioral.command;

import java.util.concurrent.ConcurrentMap;

public class CreditCardInvoker {

    private Command command;
    public CreditCardInvoker(){}

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run(){
        command.execute();
    }
}
