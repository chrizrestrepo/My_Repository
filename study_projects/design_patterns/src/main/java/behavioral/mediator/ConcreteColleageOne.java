package main.java.behavioral.mediator;

/**
 * esta es la clase en la cual se sobreescriben los métodos abstractos de la clase abstracta de la cual
 * extiende, adicional a que en el constructor de la misma se le pasa como parámetro el Mediator concreto
 * que implementa la interfaz al súper de la clase abstracta (Colleage), tal cual el ejemplo
 *
 * En esta clase se observa como el mediador (el mediator es quien comunica las clases entre sí, mediante la
 * implementación de los métodos de la interfaz definida, que para el caso es el método sendMessage) que llega
 * en el constructor es usado en el método send, el cual se sobreescribe en esta implementación de los métodos
 * abstractos de la clase súper (Colleage)
 */
public class ConcreteColleageOne extends Colleage {

    public ConcreteColleageOne(Mediator mediator){
        super(mediator);
    }

    @Override
    public void send(String message) {
        mediator.sendMessage(message, this);
    }

    @Override
    public void messageReceived(String message) {
        System.out.println("the Colleage One received a message: ".concat(message));
    }
}
