package javaFunctional.streamsAndLambdas.filter;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Filter_ {

    public static void doFilter(){
        System.out.println("---------------------- Filter ----------------------");
        List<Cliente> nombres = DataGenerator.generarLista()
                .stream()
                .filter(cl -> cl.getNombre().equals("sage"))
                .collect(Collectors.toList());
        nombres.stream()
                .forEach(cliente -> System.out.println("FILTER Example: " + "ID: " + cliente.getIdentificacion() + " NAME: " + cliente.getNombre()));
    }
}
