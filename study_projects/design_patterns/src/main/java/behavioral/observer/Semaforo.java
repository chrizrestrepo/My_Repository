package main.java.behavioral.observer;

/**
 * para la implementación del patron Observer es necesario que primero haya una clase
 * que se va a Observar, por lo que este patron sirve para notificar a las diferentes clases
 * interesadas sobre el cambio del estado de la clase a observar que para el ejemplo es un
 * semáforo --> Observer
 */
public class Semaforo {

    private String status;

    public Semaforo(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
