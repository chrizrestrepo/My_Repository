package javaFunctional.highOrderFunctions;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Function_ {

    public static void main(String[] args) {
        System.out.println("-----------------------------function-----------------------------------");

        Person person = createP.apply("cristian");
        System.out.println(person.getName());

        List<String> person1 = createPerson()
                .andThen(obtainListName())
                .andThen(addLastName())
                .apply("cristian2");
        person1.forEach(System.out::println);

        System.out.println("--------------------------BiFunction-----------------------------------");

        Person person2 = createPersonWithLastName()
                .apply("veronica", "giraldo");
        System.out.println(person2.getName());

        createPersonWithStringList().apply(createListPerson(), createListLastName()).forEach(p -> System.out.println(p.getName()));
    }
    //"-----------------------------function-----------------------------------"
    static  Function<String, Person> createP = name -> new Person(name);

    public static  Function<String, Person> createPerson(){
        return name -> new Person(name);
    }

    public static Function<Person, List<String>> obtainListName() {
        return person -> Arrays.asList(person.getName());
    }

    public static java.util.function.Function<List<String>, List<String>> addLastName(){
        return listName -> listName.stream()
                .map(name -> name.concat(" ").concat("restrepo"))
                .collect(Collectors.toList());
    }
    //"--------------------------BiFunction-----------------------------------"
    public static BiFunction<String, String, Person> createPersonWithLastName(){
        return (name, lastName) -> new Person(name.concat(" ").concat(lastName));
    }

    public static BiFunction<List<Person>, List<String>, List<Person>> createPersonWithStringList(){
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