package javaFunctional.streamsAndLambdas.minAndMax;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.Comparator;

public class MinAndMax {

    public static void doMinAndMax(){
        System.out.println("---------------------- Min And Max ----------------------");
        Cliente clientMin = DataGenerator.generarLista()
                .stream()
                .min(Comparator.comparing(Cliente::getIdentificacion))
                .orElse(null);

        Cliente clientMax = DataGenerator.generarLista()
                .stream()
                .max(Comparator.comparing(Cliente::getIdentificacion))
                .orElse(null);

        System.out.println("MIN Example: " + clientMin.getNombre());
        System.out.println("MAX Example: " + clientMax.getNombre());
    }
}
