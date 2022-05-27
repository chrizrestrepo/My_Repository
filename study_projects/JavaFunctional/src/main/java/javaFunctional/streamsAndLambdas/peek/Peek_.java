package javaFunctional.streamsAndLambdas.peek;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Peek_ {

    public static void doPeek(){
        System.out.println("---------------------- Peek ----------------------");
        List<Cliente> listaDeClientes = DataGenerator.generarLista()
                .stream()
                .peek(cliente -> cliente.setNombre(cliente.getNombre() + " Apellidos"))
                .collect(Collectors.toList());

        listaDeClientes
                .stream()
                .forEach(cliente -> System.out.println("PEEK Example: " + cliente.getNombre()));
    }
}
