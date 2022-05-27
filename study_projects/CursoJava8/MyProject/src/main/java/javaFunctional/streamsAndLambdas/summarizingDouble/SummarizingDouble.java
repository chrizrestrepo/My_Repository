package javaFunctional.streamsAndLambdas.summarizingDouble;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

public class SummarizingDouble {

    public static void doSummarizingDouble(){
        System.out.println("---------------------- SummarizingDouble ----------------------");
        DoubleSummaryStatistics estadisticas = DataGenerator.generarLista()
                .stream()
                .collect(Collectors.summarizingDouble(Cliente::getIdentificacion));

        System.out.println("SUMMARIZING-DOUBLE Example: "
                .concat(String.valueOf(estadisticas.getMax()))
                .concat(" ")
                .concat(String.valueOf(estadisticas.getMin()))
                .concat(" ")
                .concat(String.valueOf(estadisticas.getAverage()))
                .concat(" ")
                .concat(String.valueOf(estadisticas.getCount()))
                .concat(" ")
                .concat(String.valueOf(estadisticas.getSum())));
    }

}
