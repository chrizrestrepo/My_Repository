package main.java.behavioral.state;

public class Vibration implements PhoneAlertState{

    @Override
    public void alert(PhoneAlertStateContext stateContext) {
        System.out.println("vibrando...vibrando...");
    }
}
