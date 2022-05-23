package main.java.behavioral.mediator;

/**
 * esta es la clase en la cual se sobreescriben los métodos abstractos de la clase abstracta de la cual
 * extiende, adicional a que en el constructor de la misma se le pasa como parámetro el Mediator concreto
 * que implementa la interfaz al súper de la clase abstracta (Colleage), tal cual el ejemplo
 *
 * En esta clase se observa como el mediador que llega en el constructor, es usado en el metodo send, el cual
 * se sobreescribe en esta implementación de los métodos abstractos de la clase super (Colleage)
 */
public class ConcreteColleageTwo extends Colleage{

    public ConcreteColleageTwo(Mediator mediator){
        super(mediator);
    }

    @Override
    public void send(String message) {
        mediator.sendMessage(message, this);
    }

    @Override
    public void messageReceived(String message) {
        System.out.println("the Colleage Two received a message: ".concat(message));
    }
}
