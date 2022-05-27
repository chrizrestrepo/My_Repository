package ejercicioBean2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejecucion {
    public static void main(String args[]){
        new Ejecucion().prueba();
    }

    public void prueba(){
        List<Persona> lista = crearListaPersona();
        List<Persona> listap = lista.stream().
                flatMap(person-> splitPersonas(person)).
                collect(Collectors.toList());

        Map<Object, List<Persona>> mapaListap  = listap.stream().collect(Collectors.groupingBy(e->e.getId(), Collectors.toList()));
        mapaListap.get("123").forEach(e->System.out.println(e.getNombre()));

        Set<String> setPersonas = listap.stream().
                filter(e-> e.getId().length()>=4).
                map(Persona::getNombre).
                collect(Collectors.toSet());

        setPersonas.stream().
                forEach(e->System.out.println(e));

        System.out.println(listap.stream().
                map(Persona::getNombre).
                filter(e-> e.contains("a")).
                collect(Collectors.joining(" : ")));

        Map<String, String> mapaP = listap.stream().
                collect(Collectors.toMap(persona->persona.getId(), persona->persona.getNombre()));

        System.out.println(mapaP.get("564"));
    }

    private Stream<Persona> splitPersonas(Persona persona) {

        Arrays.stream(new Persona[]{persona}).
                map(e->{
                    if(e.getId()==null){
                        return Optional.ofNullable(e.getId()).orElse("000");
                    }
                    return e.getId();
                }).collect(Collectors.toList());

        if(persona.getId().contains("-")){
            return Arrays.stream(persona.getId().split("-"))
                    .map(id-> new Persona(id,persona.getNombre()));
        }else{
            return Arrays.stream( new Persona[]{persona});
        }
    }

    public static List<Persona> crearListaPersona(){
        List<Persona> listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona("123-478", "anderson"));
        listaPersonas.add(new Persona("564-2", "camilo"));
        listaPersonas.add(new Persona("26526", "daniela"));
        listaPersonas.add(new Persona("54-58", "hamilton"));
        listaPersonas.add(new Persona("548", "santiago"));
        listaPersonas.add(new Persona("2145-58975", "heidy"));
        listaPersonas.add(new Persona("215-587", "simon"));
        listaPersonas.add(new Persona("1234-5", "mateo"));
//        listaPersonas.add(new Persona(null, "diego"));

        return listaPersonas;
    }
}
