package javaFunctional.streamsAndLambdas.skipAndLimit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkipAndLimit_ {

    public static void doSkipAndLimit(){
        System.out.println("---------------------- Skip And Limit ----------------------");
        Integer[] numeros = {1,2,3,4,5,6,7,8,9,10};

        List<Integer> listaNumeros = Arrays
                .stream(numeros)
                .skip(3)
                .limit(2)
                .collect(Collectors.toList());

        listaNumeros
                .stream()
                .forEach(numero -> System.out.println("SKIP AND LIMIT Example: " + numero));
    }
}
