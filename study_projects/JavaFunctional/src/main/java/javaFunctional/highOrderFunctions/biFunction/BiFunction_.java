package javaFunctional.highOrderFunctions.biFunction;

import javaFunctional.highOrderFunctions.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BiFunction_ {

    public static void main(String[] args){
        Person person2 = createPersonWithLastName()
                .apply("veronica", "giraldo");
        System.out.println(person2.getName());

        createPersonWithStringList()
                .apply(createListPerson(), createListLastName())
                .forEach(p -> System.out.println(p.getName()));

    }
    public static java.util.function.BiFunction<String, String, Person> createPersonWithLastName(){
        return (name, lastName) -> new Person(name.concat(" ").concat(lastName));
    }

    public static java.util.function.BiFunction<List<Person>, List<String>, List<Person>> createPersonWithStringList(){
        return (listPerson, listString) -> listPerson.stream()
                .flatMap(person -> listString.stream()
                        .map(text -> new Person(person.getName().concat(" ").concat(text)))
                        .collect(Collectors.toList())
                        .stream()
                ).collect(Collectors.toList());
    }

    public static List<Person> createListPerson(){
        return Arrays.asList(
                new Person("Cristian"),
                new Person("Veronica"),
                new Person("Hamilton"));
    }

    public static List<String> createListLastName(){
        return Arrays.asList("Restrepo", "Giraldo", "Escobar");
    }
}
