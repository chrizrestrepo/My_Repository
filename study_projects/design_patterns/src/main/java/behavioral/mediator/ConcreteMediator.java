package main.java.behavioral.mediator;

/**
 * esta clase es la que orquesta toda la lógica de mediación o comunicación de las clases que requieran usar
 * (para este caso el ejemplo es una especie de chat, por lo que la comunicación es entre dos clases concretas,
 * aunque pueden ser X la cantidad), asi que el método sobreescrito mediante la validación del tipo de implementación
 * o clase concreta que resuelva los métodos abstractos de la clase súper, pues desencadena una lógica según sea el tipo,
 * para este caso simplemente se recibe el mensaje
 */
public class ConcreteMediator implements Mediator{

    private ConcreteColleageOne colleageOne;
    private ConcreteColleageTwo colleageTwo;

    @Override
    public void sendMessage(String message, Colleage colleage) {
        if(colleage.equals(colleageOne)){
            colleageTwo.messageReceived(message);
        }else if(colleage.equals(colleageTwo)){
            colleageOne.messageReceived(message);
        }
    }

    public void setColleageOne(ConcreteColleageOne colleageOne) {
        this.colleageOne = colleageOne;
    }

    public void setColleageTwo(ConcreteColleageTwo colleageTwo) {
        this.colleageTwo = colleageTwo;
    }
}
