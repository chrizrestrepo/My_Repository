package exercises.mapExercise;

import java.util.*;
import java.util.stream.Collectors;

public class Executor {

    public static void main(String[] args){
        List<Person> listaPersona = crearlistaPersona();
        Map<Integer, String> mapPersona = new HashMap<>();

        introducirListaAMapa(listaPersona, mapPersona);

        recorrerMapa(mapPersona);

        System.out.println(mapPersona.get(8));
        mapPersona.replace(8, "samantha");
        System.out.println(mapPersona.get(8));
        mapPersona.remove(8);
        System.out.println(mapPersona.get(8));

        System.out.println(mapPersona.size());

        List<Person> listP = new ArrayList<>();

        mapPersona.forEach((key, value) ->
                listP.add(new Person(key, value))
        );

        System.out.println("------------------------------------------------------");

        List<String> lista = listP
                .stream()
                .map(Person::getNombre)
                .collect(Collectors.toList());

        mapPersona
                .values()
                .stream()
                .forEach(System.out::println);


        Executor ejecucion = new Executor();
        System.out.println(ejecucion.crearOptional(null));
        System.out.println(ejecucion.crearOptional2("dayana"));


    }

    private static List<Person> crearlistaPersona() {
        return Arrays.asList(
                new Person(1,"cristian"),
                new Person(2,"daniel"),
                new Person(3,"veronica"),
                new Person(4,"hamilton"),
                new Person(5,"alonso"),
                new Person(6,"omaira"),
                new Person(7,"simon"),
                new Person(8,"santiago"),
                new Person(9,"camila"),
                new Person(20,"hamilton"));
    }

    private static void introducirListaAMapa(List<Person> listaPersona, Map<Integer, String> mapPersona) {
        listaPersona.forEach(e -> mapPersona.put(e.getId(), e.getNombre()));
    }

    public static void recorrerMapa(Map<Integer, String> mapPersona) {
        mapPersona.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public String crearOptional(String name){
        return Optional.ofNullable(name).orElse("camilo");
    }

    public String crearOptional2(String name){
        try{
            Optional.of(name);
        }catch(NullPointerException e){
            throw new IllegalStateException("el nombre no puede ser null");
        }
        return Optional.of(name).get();
    }
}
