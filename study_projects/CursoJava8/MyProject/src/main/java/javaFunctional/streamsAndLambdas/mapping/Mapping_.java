package javaFunctional.streamsAndLambdas.mapping;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Mapping_ {

    public static void doMapping(){
        System.out.println("---------------------- Mapping ----------------------");
        List<String> clientList = DataGenerator.generarLista()
                .stream()
                .collect(Collectors.mapping(Cliente::getNombre, Collectors.toList()));

        clientList
                .stream()
                .forEach(e -> System.out.println("MAPPING Example False: " + e));
    }
}
