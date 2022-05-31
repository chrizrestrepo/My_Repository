package javaFunctional.highOrderFunctions.function;

import javaFunctional.highOrderFunctions.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Function_ {

    public static void main(String[] args) {
        System.out.println("-----------------------------function-----------------------------------");

        Person person = createPerson
                .apply("cristian");
        System.out.println(person.getName());

        /**
         * cuando usamos el metodo anThen de la interfaz Function, esta nos permite ejecutar una
         * funciona luego de retornar la funcion principal, por lo que primeramente se ejecuta el
         * metodo apply y luego las funciones declaradas en el metodo .andThen()
         */
        List<String> person1 = createPerson()
                .andThen(obtainListName())
                .andThen(addLastName())
                .apply("cristian2");
        person1.forEach(System.out::println);
    }

    static  Function<String, Person> createPerson = name -> new Person(name);

    public static  Function<String, Person> createPerson(){
        return name -> new Person(name);
    }

    public static Function<Person, List<String>> obtainListName() {
        return person -> Arrays.asList(person.getName());
    }

    public static Function<List<String>, List<String>> addLastName(){
        return listName -> listName
                .stream()
                .map(name -> name
                        .concat(" ")
                        .concat("restrepo"))
                .collect(Collectors.toList());
    }

}