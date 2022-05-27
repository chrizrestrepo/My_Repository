package javaFunctional.streamsAndLambdas.count;

import javaFunctional.streamsAndLambdas.DataGenerator;

public class Count_ {

    public static void doCount(){
        System.out.println("---------------------- Count ----------------------");
        long cantidadShanes = DataGenerator.generarLista()
                .stream()
                .filter(cliente -> cliente.getNombre().equals("shane"))
                .count();
        System.out.println("COUNT Example: " + cantidadShanes);
    }
}
