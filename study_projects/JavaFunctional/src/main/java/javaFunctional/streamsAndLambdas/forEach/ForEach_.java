package javaFunctional.streamsAndLambdas.forEach;

import javaFunctional.streamsAndLambdas.DataGenerator;

public class ForEach_ {

    public static void doForEach(){
        System.out.println("---------------------- ForEach ----------------------");
        DataGenerator.generarLista()
                .stream()
                .forEach(cl -> System.out.println("FOREACH Example: " +  "ID: " + cl.getIdentificacion() + " NAME: " + cl.getNombre()));
    }
}
