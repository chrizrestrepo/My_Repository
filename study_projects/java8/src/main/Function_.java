package main;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Function_ {

    public static void main(String[] args) {
        //"-----------------------------function-----------------------------------"
        Person person = createP.apply("cristian");
        System.out.println(person.getName());

        List<String> person1 = createPerson()
                .andThen(obtainListName())
                .andThen(addLastName())
                .apply("cristian2");
        person1.forEach(System.out::println);
        //"--------------------------BiFunction-----------------------------------"
        Person person2 = createPersonWithLastName().apply("veronica", "giraldo");
        System.out.println(person2.getName());
    }
    //"-----------------------------function-----------------------------------"
    static java.util.function.Function<String, Person> createP = name -> new Person(name);

    public static java.util.function.Function<String, Person> createPerson(){
        return name -> new Person(name);
    }

    public static java.util.function.Function<Person, List<String>> obtainListName() {
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

}