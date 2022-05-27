package javaFunctional.streamsAndLambdas.toSet;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.Set;
import java.util.stream.Collectors;

public class ToSet_ {

    public static void doToSet(){
        System.out.println("---------------------- ToSet ----------------------");

        Set<String> setNombres = DataGenerator.generarLista()
                .stream()
                .map(Cliente::getNombre)
                .collect(Collectors.toSet());

        setNombres
                .stream()
                .forEach(nombre -> System.out.println("TOSET Example: " + nombre));
    }
}
