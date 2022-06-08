package javaOthers.generics;

/**
 * las clases genericas hacen uso del operador diamante en donde se le indica al generic cual es el estereotipo
 * que se va a usar, existen algunos estereotipos ya definidos, aunque es posible estereotipar el generic como
 * se desee. los estereotipos comunes son los siguientes:
 * E -> Element
 * K -> Key
 * V -> Value
 * T -> Type
 * N -> Number
 *
 * el uso de genericos permite algo llamado type safety o seguridad en tipos, esto se usa principalmente para evitar
 * casteos o en su defecto tambien evita que ingresen tipos de datos invalidos en la clase o metodo que haga uso de
 * el generic.
 * @param <T>
 */
public class GenericClass<T> {

    private final T object;

    public GenericClass(T object) {
        this.object = object;
    }

    public void printObject(){
        System.out.println(object.getClass().getName());
    }
}
