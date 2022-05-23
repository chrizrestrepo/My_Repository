package main.java.behavioral.observer;

import static main.java.behavioral.observer.SemaforoStateEnumType.ROJO;
import static main.java.behavioral.observer.SemaforoStateEnumType.VERDE;

public class CarObserver implements Observer{
    @Override
    public void update(Semaforo semaforo) {
        if(semaforo.getStatus().equalsIgnoreCase(ROJO.getStatus())){
            System.out.println("SEMAFORO ROJO");
            System.out.println("the car drivers should stop and grant people walk the street");
        }else if(semaforo.getStatus().equalsIgnoreCase(VERDE.getStatus())){
            System.out.println("SEMAFORO VERDE");
            System.out.println("the car drivers can continue your journey wherever to go");
        }
    }
}
