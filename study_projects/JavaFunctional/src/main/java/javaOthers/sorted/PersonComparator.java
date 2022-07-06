package javaOthers.sorted;

import java.util.Comparator;

/**
 * la interfaz comparator permite crear nuestra propia implementacion de comparacion de Clases personalizadas,
 * asi que por medio del generic le decimos que tipo de clases va a comparar
 */
public class PersonComparator implements Comparator<Person> {

    /**
     * el metodo compareTo() permite comparar dos objetos y asi determinar si son iguales o si uno es mayor o menor
     * que el otro, esto se hace de la siguiente manera:
     * Positiva = object1 > object2
     * 0 = object1 = object2
     * Negativa = object1 < object2
     *
     * NOTA: si deseamos implementar el metodo compare con enteros podemos hacer una resta, la cual si el resultado es 0,
     * significa que son iguales, si es numero mayor a 0 significa que el numero antrante es mayor lo cual es lo mismo decir que
     * es positivo y si la resta da un numero menor a 0 es que es negativo
     *
     * NOTA: el metodo compareTo() es un metodo definido en la interfaz Comparable, el cual para el ejemplo
     * la clase String ya implementa por si misma
     */
    @Override
    public int compare(Person object1, Person object2) {
        return object1.getIdentification().compareTo(object2.getIdentification());
    }
}
