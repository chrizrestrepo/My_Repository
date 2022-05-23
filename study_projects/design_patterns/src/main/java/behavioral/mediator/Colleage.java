package main.java.behavioral.mediator;

/**
 * para implementar el patron de diseño mediator, es necesaria la creación de la clase abstracta
 * que contenga el Mediator (Interfaz) de las clases que ha de comunicarse entre sí; adicional
 * se deben implementar los métodos abstractos de la clase mediante herencia, ya que las clases
 * abstractas no sé instancia
 */
public abstract class Colleage {

    protected Mediator mediator;

    public Colleage(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void send(String message);
    public abstract void messageReceived(String message);
}
