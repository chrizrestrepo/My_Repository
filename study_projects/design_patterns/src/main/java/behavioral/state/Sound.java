package main.java.behavioral.state;

public class Sound implements PhoneAlertState{

    @Override
    public void alert(PhoneAlertStateContext stateContext) {
        System.out.println("sonando...sonando...");
    }
}
