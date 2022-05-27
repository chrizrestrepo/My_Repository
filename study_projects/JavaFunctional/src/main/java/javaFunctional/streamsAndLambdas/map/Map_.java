package javaFunctional.streamsAndLambdas.map;

import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Map_ {

    public static void doMap(){
        System.out.println("---------------------- Map ----------------------");
        List<Object> lista = DataGenerator.generarLista()
                .stream()
                .map(cliente -> cliente.getNombre())
                .collect(Collectors.toList());
        lista.stream().forEach(cl -> System.out.println("MAPA Example: " + cl));
    }

}
