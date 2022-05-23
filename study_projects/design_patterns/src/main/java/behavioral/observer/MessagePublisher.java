package main.java.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * este es la clase que permite notificar a los demás observadores sobre los cambios
 * registrados en la clase observada mediante el uso de una lista
 */
public class MessagePublisher implements Subject{

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void dettach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyUpdate(Semaforo semaforo) {
        observers.forEach(observer -> observer.update(semaforo));
    }
}
