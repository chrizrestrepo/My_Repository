package javaFunctional.streamsAndLambdas.joining;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.stream.Collectors;

public class Joining_ {

    public static void doJoining(){
        System.out.println("---------------------- Joining ----------------------");

        String cadenaNombres = DataGenerator.generarLista()
                .stream()
                .map(Cliente::getNombre)
                .collect(Collectors.joining(" - "))
                .toUpperCase();

        System.out.println("JOINING Example: " + cadenaNombres);
    }
}
