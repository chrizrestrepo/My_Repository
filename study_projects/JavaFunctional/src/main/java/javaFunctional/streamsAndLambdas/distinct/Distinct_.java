package javaFunctional.streamsAndLambdas.distinct;

import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Distinct_ {

    public static void doDistinct(){
        System.out.println("---------------------- DistinCt ----------------------");
        List<String> clientesUnicos = DataGenerator.generarLista()
                .stream()
                .map(cliente -> cliente.getNombre())
                .distinct()
                .collect(Collectors.toList());

        clientesUnicos
                .stream()
                .forEach(cliente -> System.out.println("DISTINT Example: " + "ID: " + cliente));
    }
}
