package main.java.behavioral.templateMethod;

/**
 * el patron de diseño templateMethod permite crear un metodo por defecto o como su nombre lo indica una
 * plantilla de una metodo que nunca cambia, este patron permite mediante la herencia pues resolver la implementacion
 * de los metodos abstractos que pueden ser usados a modo de secuencia de pasos dentro de los diferentes metodos por
 * defecto o plantillas que se definan
 */
public abstract class Payment {

    abstract void initialize();
    abstract void startPayment();
    abstract void endPayment();

    public final void doPayment(){
       initialize();
       startPayment();
       endPayment();
    }
}
