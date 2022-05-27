package javaFunctional.streamsAndLambdas.sorted;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorted_ {

    public static void doSorted(){
        System.out.println("---------------------- Sorted ----------------------");

        List<Cliente> listaOrdenada = DataGenerator.generarLista()
                .stream()
                .sorted(Comparator.comparing(Cliente::getNombre))
                .collect(Collectors.toList());

        listaOrdenada
                .stream()
                .forEach(cliente -> System.out.println("SORTED Example: " + "ID: " + cliente.getIdentificacion() + " NAME: " + cliente.getNombre()));
    }
}
