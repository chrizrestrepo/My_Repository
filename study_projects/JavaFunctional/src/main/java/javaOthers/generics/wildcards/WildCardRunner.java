package javaOthers.generics.wildcards;

import java.util.Arrays;
import java.util.List;

public class WildCardRunner {

    public static void main(String[] args){
        System.out.println("-------- GENERICS UPPERBOUNDED ---------");
        printList(createStudentList());
        System.out.println("----------------------------------------");
        printList(createTeacherList());
    }

    /**
     * los wildcards tal cual como traduce la palabra son un comodin, el cual permite que dentro del operador <> donde se
     * define el generic pueda ingresar cualquier tipo de clase usando el esteriotipo <?>... haciendo lo siguiente pues le
     * decimos a java que dentro del generic puede ingresar cualquier clase que herede de Object, lo cual en generics se define
     * como UNBOUNDED, lo que seria igual a definir el generic de la siguiente manera <? extends Object>, por el contrario
     * existe otra forma definida como UPPERBOUNDED en donde le decimos al generic por medio del wildcard que vamos a recibir
     * unicamente clases que hereden un tipo de clase concreto de la siguiente manera <? extends Persona>
     * @param list
     * @return
     */
    public static void printList(List<? extends Person> list){
        list.stream()
                .map(Person::toString)
                .forEach(System.out::println);
    }

    public static List<Teacher> createTeacherList(){
        return Arrays.asList(
                new Teacher("cristian", "restrepo", 1036951284L),
                new Teacher("veronica", "giraldo", 1035436126L));
    }

    public static List<Student> createStudentList(){
        return Arrays.asList(
                new Student("hamilton", "restrepo", 1026951284L),
                new Student("alonso", "restrepo", 70115432L));
    }
}
