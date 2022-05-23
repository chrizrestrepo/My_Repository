package main.java.behavioral.observer;

/**
 * debe existir una interfaz Observer la cual nos permite registrar los distintos cambios
 * o actualizaciones del estado de la clase Observada (Semáforo) --> Implements
 */
public interface Observer {
    void update(Semaforo semaforo);
}
