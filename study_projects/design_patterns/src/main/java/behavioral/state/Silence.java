package main.java.behavioral.state;

public class Silence implements PhoneAlertState{

    @Override
    public void alert(PhoneAlertStateContext stateContext) {
        System.out.println("silencio...silencio...");
    }
}
