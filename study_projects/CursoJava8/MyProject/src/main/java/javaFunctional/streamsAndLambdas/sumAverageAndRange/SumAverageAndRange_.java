package javaFunctional.streamsAndLambdas.sumAverageAndRange;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.stream.IntStream;

public class SumAverageAndRange_ {

    public static void doSumAverageAndRange(){
        System.out.println("---------------------- Sum, Average And Range ----------------------");
        double promedio = DataGenerator.generarLista()
                .stream()
                .mapToInt(Cliente::getIdentificacion)
                .average()
                .orElse(0);
        System.out.println("AVERAGE Example: " + promedio);

        int sumatoria = DataGenerator.generarLista()
                .stream()
                .mapToInt(Cliente::getIdentificacion)
                .sum();
        System.out.println("SUM Example: " + sumatoria);

        System.out.println("RANGE Example: " + IntStream.range(0,1000).sum());
    }

}
