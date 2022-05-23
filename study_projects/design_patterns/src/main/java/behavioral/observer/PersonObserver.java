package main.java.behavioral.observer;

import static main.java.behavioral.observer.SemaforoStateEnumType.ROJO;
import static main.java.behavioral.observer.SemaforoStateEnumType.VERDE;

/**
 * las diferentes implementaciones de la Interfaz Observer ha de actualizar el estado
 * a los demás Observadores mediante la resolución del método update, adicional a que
 * cada una de sus implementaciones deberían comportarse diferente debido a que estas
 * resuelven sus lógicas de distinto modo según el estado de la clase Observada (Semáforo)
 * --> MessagePublisher
 */
public class PersonObserver implements Observer{

    @Override
    public void update(Semaforo semaforo) {
        if(semaforo.getStatus().equalsIgnoreCase(ROJO.getStatus())){
            System.out.println("SEMAFORO ROJO");
            System.out.println("the people can to cross the street without any problem");
        }else if(semaforo.getStatus().equalsIgnoreCase(VERDE.getStatus())){
            System.out.println("SEMAFORO VERDE");
            System.out.println("people should stay on sidewalk");
        }
    }
}
