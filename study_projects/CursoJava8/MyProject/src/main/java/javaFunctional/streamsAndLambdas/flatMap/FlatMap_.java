package javaFunctional.streamsAndLambdas.flatMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap_ {

    public static void doFlatMap(){
        System.out.println("---------------------- FlatMap ----------------------");
        List<List<String>> listaNombres = new ArrayList<List<String>>(
                Arrays.asList(
                new ArrayList<String>(Arrays.asList("camilo", "anderson", "andres", "santiago")),
                new ArrayList<String>(Arrays.asList("daniela", "camila", "maria", "monica"))
                )
        );

        List<String> listaUnica = listaNombres
                .stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        listaUnica
                .stream()
                .forEach(l -> System.out.println("FLATMAP Example: " + l));
    }
}
