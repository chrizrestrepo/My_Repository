
import java.util.*;
import java.util.stream.Collectors;

public class Ejecucion {
    public static void main(String[] args){
        List<Persona> listaPersona = crearlistaPersona();

        Map<Integer, String> mapPersona = new HashMap<Integer, String>();

        introducirListaAMapa(listaPersona, mapPersona);

        recorrerMapa(mapPersona);

        System.out.println(mapPersona.get(8));
        mapPersona.replace(8, "samantha");
        System.out.println(mapPersona.get(8));
        mapPersona.remove(8);
        System.out.println(mapPersona.get(8));

        System.out.println(mapPersona.size());

        List<Persona> listP = new ArrayList<>();

        mapPersona.forEach((key, value) ->
                listP.add(new Persona(key, value))
                );

        System.out.println("------------------------------------------------------");

        List lista = listP.stream().map(Persona::getNombre).collect(Collectors.toList());
      // lista.forEach(e -> System.out.println(e));


       mapPersona.values().stream().forEach(e -> System.out.println(e));


       Ejecucion ejecucion = new Ejecucion();
       System.out.println(ejecucion.crearOptional(null));
       System.out.println(ejecucion.crearOptional2("dayana"));


    }

    private static List<Persona> crearlistaPersona() {
        List<Persona> listaPersona = List.of(
                new Persona(1,"cristian"),
                new Persona(2,"daniel"),
                new Persona(3,"veronica"),
                new Persona(4,"hamilton"),
                new Persona(5,"alonso"),
                new Persona(6,"omaira"),
                new Persona(7,"simon"),
                new Persona(8,"santiago"),
                new Persona(9,"camila"),
                new Persona(20,"hamilton"));
        return listaPersona;
    }

    private static void introducirListaAMapa(List<Persona> listaPersona, Map<Integer, String> mapPersona) {
        listaPersona.forEach(e ->
                mapPersona.put(e.getId(), e.getNombre())
                );
    }

    public static void recorrerMapa(Map<Integer, String> mapPersona) {
        mapPersona.forEach((key, value) ->
                System.out.println(key + value)
        );
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