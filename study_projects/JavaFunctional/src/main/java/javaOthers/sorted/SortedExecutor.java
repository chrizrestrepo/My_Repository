package javaOthers.sorted;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedExecutor {

    public static void main(String[] args){
        /**
         * la clase Collections permite ordenar listas por el orden natural de los objectos, osea de manera accedente
         * tiendo en cuenta quien viene primero, por ejemplo: [1-X] - [A-Z]
         */
        System.out.println("------------- SORTED COLLECTIONS CLASS --------------- ");
        List<String> NamesList = createNamesList();
        Collections.sort(NamesList);
        NamesList.forEach(System.out::println);

        System.out.println("------------- SORTED COMPARATOR --------------- ");
        List<Person> personList = createPersonList();
        Collections.sort(personList, new PersonComparator());
        personList.forEach(person -> System.out.println(person.toString()));

        /**
         * En el ejemplo se observa que la clase Collections ordena la lista de Objetos Person, esto es gracias a que
         * la clase implementa la interfaz Comparable, mas especificamente el metodo compareTo
         */
        System.out.println("------------- SORTED COMPARABLE --------------- ");
        List<Person> personListSortedByLastName = createPersonList();
        Collections.sort(personListSortedByLastName);
        personList.forEach(person -> System.out.println(person.toString()));
    }

    public static List<String> createNamesList(){
        return Arrays.asList("cristian", "veronica", "hamilton", "alonso", "omaira", "nancy", "simon");
    }

    public static List<Person> createPersonList(){
        return Arrays.asList(
                new Person("1036951284", "Cristian", "restrepo"),
                new Person("1035436124", "veronica", "giraldo"),
                new Person("1000124356", "hamilton", "restrepo"));
    }
}
