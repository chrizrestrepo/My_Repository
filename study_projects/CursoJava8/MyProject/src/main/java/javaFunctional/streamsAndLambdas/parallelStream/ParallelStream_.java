package javaFunctional.streamsAndLambdas.parallelStream;

import javaFunctional.streamsAndLambdas.DataGenerator;

public class ParallelStream_ {

    public static void doParallelStream(){
        System.out.println("---------------------- ParallelStream ----------------------");
        long tiempo1 = System.currentTimeMillis();
        DataGenerator.generarLista()
                .stream()
                .forEach(e -> DataGenerator.convertirAmayusculas(e.getNombre()));
        long tiempo2 = System.currentTimeMillis();
        System.out.println("tiempo Stream normal: " + (tiempo1-tiempo2));

        tiempo1 = System.currentTimeMillis();
        DataGenerator.generarLista()
                .parallelStream()
                .forEach(e -> DataGenerator.convertirAmayusculas(e.getNombre()));
        tiempo2 = System.currentTimeMillis();
        System.out.println("tiempo Stream paralelo: " + (tiempo1-tiempo2));
    }
}
