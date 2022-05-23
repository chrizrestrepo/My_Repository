package main.java.behavioral.strategy;

/**
 * el patron de diseño strategy permite definir la forma en que se puede resolver un mismo asunto de distintas formas,
 * para lograrlo se es necesario definir una interfaz que defina los metodos a implementar dentro de la estrategia,
 * los cuales serán resueltos por las diferentes estrategias quen este caso serán las implementaciones.
 *
 * la clase que resuelve la implementacion de la estrategia, debe contar con un parametro de la interfaz startegy,
 * por lo tanto a la hora crear la clase por medio del constructor (el cual debe recibir la estrategia), pues resuelve
 * la estrategia por medio del uso de los metodos implementados en el misma
 */
public class TextFormatter {

    private TextFormatterStrategy textFormatterStrategy;

    public TextFormatter(TextFormatterStrategy strategy){
        this.textFormatterStrategy = strategy;
    }

    public void formatText(String textToFormat){
        textFormatterStrategy.formatInputString(textToFormat);
    }

}
